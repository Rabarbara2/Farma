package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {


    int speed;
    public int tool;
    public Tile toolImages;

    public Integer points;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }
    @SuppressWarnings({"DataFlowIssue"})
    public void setDefaultValues() {
        x = 0;
        y = 0;
        tool = -1;
        points = 0;
        speed = gp.TILE_SIZE;
        toolImages = new Tile();
        try {
            toolImages.image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/hoe.png")),
                    ImageIO.read(getClass().getResourceAsStream("/can.png")),
                    ImageIO.read(getClass().getResourceAsStream("/kosa.png")),
                    ImageIO.read(getClass().getResourceAsStream("/grassSeeds.png")),
                    ImageIO.read(getClass().getResourceAsStream("/seeds.png")),
                    ImageIO.read(getClass().getResourceAsStream("/seeds2.png")),
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        super.draw(g2);

        if(tool!=-1) { // maluj tool
            g2.drawImage(toolImages.image[tool], x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
        Font font = new Font("Verdana", Font.BOLD,14);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.drawString("punkty: " + points.toString(), (gp.MAX_SCREEN_COLUMNS-2) * gp.TILE_SIZE, gp.TILE_SIZE/2);
    }
}