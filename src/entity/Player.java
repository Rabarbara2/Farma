package entity;

import main.GamePanel;
import main.GameState;
import main.KeyHandler;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;


public final class Player extends Entity implements Serializable
    {
        public static Player instance;

        transient int speed;
        transient public int tool;
        transient public Tile toolImages;
        public Integer points;
        GameState gameState = GameState.getInstance();

        private Player(GamePanel gp, KeyHandler keyH)
        {

            this.gp = gp;
            this.keyH = keyH;

            setDefaultValues();
            getPlayerImage();

        }

        public static Player getInstance(GamePanel gp, KeyHandler keyH)
        {
            if (instance == null)
            {
                instance = new Player( gp,  keyH);
            }
            return instance;
        }

        @SuppressWarnings({"DataFlowIssue"})
        public void setDefaultValues() {
            x = gameState.getPlayerX();
            y = gameState.getPlayerY();
            tool = -1;
            points = gameState.getPoints();
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
            gameState.setPlayerState(x, y, points);
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

        public int getPoints() {return points;}
        public void setPoints(int points) {this.points = points;}

    }

