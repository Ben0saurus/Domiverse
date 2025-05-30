package UI;

import main.GamePanel;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }


    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, 600, 10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 650, 50);

        //Playtime
        playTime += (double) 1/60;
        g2.drawString(dFormat.format(playTime), 650, gp.tileSize*11);

        //Message
        if (messageOn == true) {

            if (message.length() <= 30) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*10);
            } else {
                g2.setFont(g2.getFont().deriveFont(27F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*10);
            }

            messageCounter++;

            if (messageCounter >= 100) {
                messageCounter = 0;
                messageOn = false;
            }

        }

    }

}
