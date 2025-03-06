package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    //zmiana mapy (placeholder)
    public void update() {

        // TODO Update wszystkie Fields
        if(keyH.spacePressed)
        {
            mapFields[player.x/gp.TILE_SIZE][player.y/ gp.TILE_SIZE].hoeing = true;
            keyH.spacePressed = false;
        }
        if(keyH.backPressed)
        {
            mapFields[player.x/gp.TILE_SIZE][player.y/ gp.TILE_SIZE].deleting = true;
            keyH.backPressed = false;
        }

        for(int i = 0; i<gp.MAX_SCREEN_COLUMNS; i++){
            for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].update();
                }
            }

    }
    @SuppressWarnings({"DataFlowIssue", "CallToPrintStackTrace"})
    public void loadMap2(){
        try{
            InputStream is = getClass().getResourceAsStream("/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int i = 0; i<gp.MAX_SCREEN_ROWS; i++){

                String line = br.readLine();

                for(int j = 0; j<gp.MAX_SCREEN_COLUMNS; j++){

                    String[] split = line.split(" ");
                    mapTiles[j][i] = Integer.parseInt(split[j]);
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
            // większa obsługa wyjątków.
        }
    }

    public void loadMap(){
//        try{
//            InputStream is = getClass().getResourceAsStream("/map/map.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for(int i = 0; i<gp.MAX_SCREEN_COLUMNS; i++){
//                String line = br.readLine();
                for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++){
//                    String[] split = line.split(" ");
                    mapFields[i][j] = new GameField(i*gp.TILE_SIZE, j*gp.TILE_SIZE, keyH, this, player , gp);
                }
            }
//            br.close();

//        }catch (Exception e){
//            e.printStackTrace();
//        }
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

        // TODO draw wszystkie Fields

        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].draw(g2);
//                if (mapTiles[i][j] == 0) {
//                    g2.drawImage(tiles[0].image[randomNumber[i][j]], i * gp.TILE_SIZE, j * gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
//                }
//                if (mapTiles[i][j] == 1) {
//                    g2.drawImage(tiles[1].image[0], i*gp.TILE_SIZE, j*gp.TILE_SIZE,gp.TILE_SIZE ,gp.TILE_SIZE , null );
//                }
                }}


    }
}
