package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int[][] randomNumber = new int[16][10];


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        getTileImage();
        generateRandomTextures();
    }

    @SuppressWarnings({"DataFlowIssue", "CallToPrintStackTrace"})
    public void getTileImage() {

        try{
            //grass
            tiles[0] = new Tile();
            tiles[0].image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/Grass1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass5.png")),
            };

            //Field
            tiles[1] = new Tile();
            tiles[1].image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/FieldDry.png")),
                    ImageIO.read(getClass().getResourceAsStream("/FieldWet.png")),
            };
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void generateRandomTextures() {
        //losowa tekstura trawy (placeholder)
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                randomNumber[i][j] = random.nextInt(5);
            }
        }
    }


    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS-1; j++) {
                g2.drawImage(tiles[0].image[randomNumber[i][j]], i*gp.TILE_SIZE, j*gp.TILE_SIZE,gp.TILE_SIZE ,gp.TILE_SIZE , null );
            }}
        for (int i = 0; i < 16; i++) {
            g2.drawImage(tiles[1].image[i/8], i*gp.TILE_SIZE, 9*gp.TILE_SIZE,gp.TILE_SIZE ,gp.TILE_SIZE , null );
        }


    }
}
