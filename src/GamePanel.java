import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 32;
    final int SCALE = 2;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final int MAX_SCREEN_COLUMNS = 16;
    final int MAX_SCREEN_ROWS = 10;
    final int SCREEN_WIDTH = MAX_SCREEN_COLUMNS * TILE_SIZE;
    final int SCREEN_HEIGHT = MAX_SCREEN_ROWS * TILE_SIZE;

    int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    //player default position
    int playerX = 128;
    int playerY = 128;
    int playerSpeed = TILE_SIZE;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        if(keyH.upPressed)
        {
            playerY -= playerSpeed;
            keyH.upPressed = false;
        }
        if(keyH.downPressed)
        {
            playerY += playerSpeed;
            keyH.downPressed = false;
        }
        if(keyH.leftPressed)
        {
            playerX -= playerSpeed;
            keyH.leftPressed = false;
        }
        if(keyH.rightPressed)
        {
            playerX += playerSpeed;
            keyH.rightPressed = false;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
        g2.dispose();
    }
}
