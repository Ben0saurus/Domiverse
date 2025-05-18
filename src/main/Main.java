package main;

import sound.SoundPlayer;

import javax.swing.*;

public class Main {

    public static JFrame window;
    public static GamePanel gamePanel;
    public static SoundPlayer music;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        // Stop previous music if restarting
        if (music != null) {
            music.stop();
        }

        // Start music
        SoundPlayer music = new SoundPlayer("/sounds/Soundtrack2.wav");
        music.loop();

        // Window setup
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Domiverse");

        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    public static void restartGame() {
        if (music != null) {
            music.stop();
            music = null;
        }
        window.dispose();
        startGame();
    }
}
