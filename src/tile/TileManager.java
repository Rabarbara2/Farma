package tile;

import entity.Player;
import main.FieldState;
import main.GamePanel;
import main.GameState;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    KeyHandler keyH;
    Player player;
    public int[][] randomNumber;

    public int[][] mapTiles ;
    public ArrayList<Tile> tileList;

    public Field[][] mapFields;
    GameState gameState = GameState.getInstance();

    public TileManager(GamePanel gp, KeyHandler keyH, Player player) {
        this.gp = gp;
        this.player = player;
        this.keyH = keyH;

        tileList = new ArrayList<Tile>();
        randomNumber = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];
        mapFields = new Field[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];
        getTileImage();
        generateRandomTextures();
        loadMap();
    }

    public void update() {

        //pick up tool
        if (keyH.spacePressed && mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].type == 1)
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].pickingUp = true;
            keyH.spacePressed = false;
        }
        if (keyH.spacePressed && player.tool == 0) //hoe
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].hoeing = true;
            keyH.spacePressed = false;
        }
        if (keyH.spacePressed && player.tool == 1) //water
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].watering = true;
            keyH.spacePressed = false;
        }
        if (keyH.spacePressed && player.tool == 2) //harvest
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].harvesting = true;
            keyH.spacePressed = false;
        }

        if (keyH.spacePressed && player.tool == 3) //delete
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].deleting = true;
            keyH.spacePressed = false;

        }

        if (keyH.spacePressed && player.tool >= 4) //sow
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].sowing = true;
            keyH.spacePressed = false;
        }

        //pozostale interakcje
        if (keyH.spacePressed)
        {
            mapFields[player.x / gp.TILE_SIZE][player.y / gp.TILE_SIZE].interaction = true;
            keyH.spacePressed = false;
        }

        for(int i = 0; i<gp.MAX_SCREEN_COLUMNS; i++){
            for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].update();
                }
            }
    }


    public void loadMap(){
        mapFields[0][0] = new KittyField(0, 0, 0, 0, keyH, this, player , gp);
        for(int j = 1; j<gp.MAX_SCREEN_ROWS; j++){
            if(j<=6)
                mapFields[0][j] = new ToolField(0, j*gp.TILE_SIZE, 0, j, keyH, this, player , gp,(j-1)%6);
            else
                mapFields[0][j] = new PathField(0, j*gp.TILE_SIZE, 0 , j, keyH, this, player , gp);
        }

            for(int i = 1; i<gp.MAX_SCREEN_COLUMNS; i++){
                for(int j = 0; j<gp.MAX_SCREEN_ROWS; j++){

                    GameField gameField = new GameField(i*gp.TILE_SIZE, j*gp.TILE_SIZE, i, j, keyH, this, player , gp);
                    FieldState element = gameState.fieldStateArray[i][j];
                    if(null != element) {

                        gameField.name = element.name;
                        gameField.state = element.fieldState;
                        gameField.setImage(element.fieldState);
                        gameField.isField = true;
                        mapFields[i][j] = gameField;

                        if (element.name.equals("Bratek")) {
                            gameField.plantBratek(element.state);
                            gameField.plant.state = element.state;
                            gameField.plant.setImage(5, element.state >= 2 ? element.variety : element.state);
                            gameField.plant.setImage(5, element.state);
                        } else if (element.name.equals("Ciecierzyca")) {
                            gameField.plantCiecierzyca(element.state);
                            gameField.plant.state = element.state;
                            gameField.plant.setImage(4, element.state);
                        }
                    } else {
                        mapFields[i][j] = gameField;
                    }

                }
            }
    }

    @SuppressWarnings({"DataFlowIssue", "CallToPrintStackTrace"})
    public void getTileImage() {

        try{
            //grass
            tileList.add(new Tile());
            tileList.get(0).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/Grass1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/Grass5.png")),
            };

            //Field
            tileList.add(new Tile());
            tileList.get(1).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/FieldDry.png")),
                    ImageIO.read(getClass().getResourceAsStream("/FieldWet.png")),
            };

            //Path
            tileList.add(new Tile());
            tileList.get(2).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/path.png")),

            };
            //kot
            tileList.add(new Tile());
            tileList.get(3).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/kot.png")),

            };
            //plants
            // cieciorka
            tileList.add(new Tile());
            tileList.get(4).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/roslinka.png")),
                    ImageIO.read(getClass().getResourceAsStream("/roslinka2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/roslinka3.png")),
            };
            //bratki
            tileList.add(new Tile());
            tileList.get(5).image = new BufferedImage[] {
                    ImageIO.read(getClass().getResourceAsStream("/bratek1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/bratek2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/bratek3a.png")),
                    ImageIO.read(getClass().getResourceAsStream("/bratek3b.png")),
                    ImageIO.read(getClass().getResourceAsStream("/bratek3c.png")),
            };


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void generateRandomTextures() {
        Random random = new Random();
        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS; j++) {
                randomNumber[i][j] = random.nextInt(5);
            }
        }
    }


    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.MAX_SCREEN_COLUMNS; i++) {
            for (int j = 0; j < gp.MAX_SCREEN_ROWS; j++) {
                mapFields[i][j].draw(g2);
                }}



    }
}
