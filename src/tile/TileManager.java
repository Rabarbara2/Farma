package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    KeyHandler keyH;
    Player player;
    public Tile[] tiles;
    public int[][] randomNumber;
    public int[][] mapTiles ; // domyślnie Field?
    public GameField[][] mapFields;


    public TileManager(GamePanel gp, KeyHandler keyH, Player player) {
        this.gp = gp;
        this.player = player;
        this.keyH = keyH;
        tiles = new Tile[10];
        randomNumber = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];
        mapTiles = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];
        mapFields = new GameField[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];
        getTileImage();
        generateRandomTextures();
        loadMap();
    }

    public void update() {

        if(keyH.spacePressed && player.tool == 0) //hoe
        {
            mapFields[player.x/gp.TILE_SIZE][player.y/ gp.TILE_SIZE].hoeing = true;
            keyH.spacePressed = false;
        }
        if(keyH.spacePressed && player.tool == 1) //delete
        {
            mapFields[player.x/gp.TILE_SIZE][player.y/ gp.TILE_SIZE].deleting = true;
            keyH.spacePressed = false;
        }

        for(int i = 1; i<gp.MAX_SCREEN_COLUMNS; i++){
            for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].update();
                }
            }

    }


    public void loadMap(){

            for(int i = 1; i<gp.MAX_SCREEN_COLUMNS; i++){
            // w pierwszej kolumnie bd narzędzia i klienci i idk opcje rozne :>
                for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++){

                    mapFields[i][j] = new GameField(i*gp.TILE_SIZE, j*gp.TILE_SIZE, keyH, this, player , gp);
                }
            }

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
        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS; j++) {
                randomNumber[i][j] = random.nextInt(5);
            }
        }
    }


    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            //maluj pierwszy rząd Fieldów
        }
        for (int i = 1; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].draw(g2);
                }}


    }
}
