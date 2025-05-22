package entity;

import main.GamePanel;
import main.KeyHandler;
import sound.SoundPlayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int lifes = 3;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        hitbox.width = 32;
        hitbox.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    //Screen Update
    public void update() {


        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);


            if (!collisionOn) {

                switch (direction) {

                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;


                }

            }


            //Animation
            spriteCounter++;
            if (spriteCounter > 16) {

                if (spriteNum == 1) {

                    spriteNum = 2;

                } else if (spriteNum == 2) {

                    spriteNum = 1;

                }
                spriteCounter = 0;
            }

        }


    }


    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    SoundPlayer keyPlayer = new SoundPlayer("/sounds/keySound.wav");
                    keyPlayer.play();
                    gp.obj[i] = null;
                    gp.ui.showMessage("You picked up a Key!");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        SoundPlayer doorPlayer = new SoundPlayer("/sounds/doorSound.wav");
                        doorPlayer.play();
                        gp.ui.showMessage("You just opened a door!");
                        gp.obj[i] = null;
                        hasKey--;
                    } else {
                        gp.ui.showMessage("You need a Key to open this Door!");
                    }
                    break;
                case "Chest":
                    if(lifes < 3) {
                        lifes++;
                        gp.ui.showMessage("You opened a chest and got a heart back!");
                    } else {
                        lifes--;
                        gp.ui.showMessage("You opened a chest and lost a heart!");
                    }
                    gp.getHealthbar().setHealth(lifes);
                    SoundPlayer chestPlayer = new SoundPlayer("/sounds/chestSound.wav");
                    chestPlayer.play();
                    gp.obj[i] = null;
                    break;
                case "Cigarette":
                    lifes--;
                    gp.ui.showMessage("You just smoked a Cigarette! Congrats!");
                    if (lifes != 0) {
                        SoundPlayer cigarettePlayer = new SoundPlayer("/sounds/damageSound.wav");
                        cigarettePlayer.play();
                        speed += 2;
                    }
                    gp.getHealthbar().setHealth(lifes);
                    gp.obj[i] = null;
                    break;
                case "Vape":
                    lifes--;
                    gp.ui.showMessage("You just smoked a Vape! Congrats!");
                    if (lifes != 0) {
                        SoundPlayer vapePlayer = new SoundPlayer("/sounds/damageSound.wav");
                        vapePlayer.play();
                        speed += 2;
                    }
                    gp.getHealthbar().setHealth(lifes);
                    gp.obj[i] = null;
                    break;
                case "Coke":
                    lifes--;
                    gp.ui.showMessage("You just consumed some Cocaine! Congrats!");
                    if (lifes != 0) {
                        SoundPlayer cokePlayer = new SoundPlayer("/sounds/damageSound.wav");
                        cokePlayer.play();
                        speed += 2;
                    }
                    gp.getHealthbar().setHealth(lifes);
                    gp.obj[i] = null;
                    break;
                case "Heroine":
                    lifes--;
                    gp.ui.showMessage("You just consumed some Heroine! Congrats!");
                    if (lifes != 0) {
                        SoundPlayer heroinePlayer = new SoundPlayer("/sounds/damageSound.wav");
                        heroinePlayer.play();
                        speed += 2;
                    }
                    gp.getHealthbar().setHealth(lifes);
                    gp.obj[i] = null;
                    break;

            }

        }

    }


    //Visuals
    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
