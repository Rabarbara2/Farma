package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 32;
    final int SCALE = 2;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public int MAX_SCREEN_COLUMNS = 16;
    public int MAX_SCREEN_ROWS = 10;
    final int SCREEN_WIDTH = MAX_SCREEN_COLUMNS * TILE_SIZE;
    final int SCREEN_HEIGHT = MAX_SCREEN_ROWS * TILE_SIZE;

    int FPS = 30;



    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this, keyH, player);



    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();


    }





    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            // Update
            update();

            //Draw
            repaint();



            try {
                double deltaTime = ( nextDrawTime - System.nanoTime() ) / 1000000;
                if (deltaTime < 0) {
                    deltaTime = 0;
                }
                //noinspection BusyWait
                Thread.sleep((long)deltaTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }
        }

    }
    public void update() {

        if (!this.hasFocus()) {
            System.out.println("GamePanel stracił focus, próbuję przywrócić...");
            this.requestFocusInWindow();
        }
        player.update();
        tileM.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);


        g2.dispose();
    }


}
