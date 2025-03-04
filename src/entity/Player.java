package entity;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    int speed;
    public int tool;
    // 0-nic
    // 1-motyka
    // 2-podlewaczka
    // 3-kosa
    // 4-trawa


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();



    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        tool = 0;
        speed = gp.TILE_SIZE;

    }

    @SuppressWarnings({"CallToPrintStackTrace", "DataFlowIssue"})
    public void getPlayerImage() {
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Farmer.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update() {

        if(keyH.upPressed)
        {

            if(y!=0 ) {
                y -= speed;
                keyH.upPressed = false;
            }
        }
        if(keyH.downPressed)
        {
            if(y!=gp.TILE_SIZE*(gp.MAX_SCREEN_ROWS-1)) {
                y += speed;
                keyH.downPressed = false;
            }
        }
        if(keyH.leftPressed)
        {
            if(x!=0) {
                x -= speed;
                keyH.leftPressed = false;
            }
        }
        if(keyH.rightPressed)
        {
            if(x!=gp.TILE_SIZE*(gp.MAX_SCREEN_COLUMNS-1)) {
            x += speed;
            keyH.rightPressed = false;
            }
        }

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

    }
}
