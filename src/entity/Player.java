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

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();



    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = gp.TILE_SIZE;

    }

    @SuppressWarnings({"CallToPrintStackTrace", "DataFlowIssue"})
    public void getPlayerImage() {
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Farmer.png"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("sie rozjebal");
        }

    }

    public void update() {

        if(keyH.upPressed)
        {
            y -= speed;
            keyH.upPressed = false;
        }
        if(keyH.downPressed)
        {
            y += speed;
            keyH.downPressed = false;
        }
        if(keyH.leftPressed)
        {
            x -= speed;
            keyH.leftPressed = false;
        }
        if(keyH.rightPressed)
        {
            x += speed;
            keyH.rightPressed = false;
        }

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

    }
}
