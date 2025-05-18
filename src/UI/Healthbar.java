package UI;

import sound.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Healthbar {

    BufferedImage image;
    int health;

    public Healthbar(int defaultHealth) {

        health = defaultHealth;
        image = getHealthbarImage();

    }

    public void setHealth(int health) {
        this.health = health;
        image = getHealthbarImage();

        if (health <= 0) {
            SoundPlayer soundPlayer = new SoundPlayer("/sounds/deathSound.wav");
            soundPlayer.play();
            SwingUtilities.invokeLater(() -> {
                JFrame currentWindow = main.Main.window;
                currentWindow.dispose(); // Close the game window
                new DeathScreen(); // Open DeathScreen
            });
        }
    }


    public void draw(Graphics2D g2) {
        if (image != null) {
            g2.drawImage(image, 0, 0, 240, 80, null);
        } else {
            System.out.println("Healthbar image is null — drawing placeholder.");
            g2.setColor(Color.RED);
            g2.fillRect(0, 0, 40, 40);
        }
    }

    public BufferedImage getHealthbarImage() {

        try {

            if (health == 3) {

                image = ImageIO.read(getClass().getResourceAsStream("/UI/Healthbar3.png"));

            } else if (health == 2) {

                image = ImageIO.read(getClass().getResourceAsStream("/UI/Healthbar2.png"));

            } else if (health == 1) {

                image = ImageIO.read(getClass().getResourceAsStream("/UI/Healthbar1.png"));

            } else if (health == 0) {

                image = ImageIO.read(getClass().getResourceAsStream("/UI/Healthbar0.png"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            System.out.println("Failed to load healthbar image for health = " + health);
        }

        return image;

    }

}
