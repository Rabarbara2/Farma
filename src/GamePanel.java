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

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

    }
}
