package main;

import UI.Healthbar;
import UI.UI;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Healthbar healthbar = new Healthbar(3);


    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 Feld
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x49 Felder
    public int maxScreenCol = 16; //Anzahl and Feldern pro Reihe
    public int maxScreenRow = 12; //Anzahl der Reihen
    public int screenWidth = tileSize * maxScreenCol; //Breite des Bildschirms (768 px)
    public int screenHeight = tileSize * maxScreenRow; //Höhe des Bildschirms (576px)

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //Maximale FPS
    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[20];


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {

        aSetter.setObject();

    }


    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps; // 0.01666 sekunden
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

                drawCount = 0;
                timer = 0;

            }
        }

    }

    public void update() {
        player.update();
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileM.draw(g2);

        //Object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        //Healthbar
        healthbar.draw(g2);

        //Keys
        ui.draw(g2);

        g2.dispose();

    }

}
