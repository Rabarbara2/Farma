package tile;

import entity.Plant;
import entity.Player;
import main.GamePanel;
import main.GameState;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;


public class GameField extends Field implements Serializable {

    transient entity.Plant plant;
    GameState gameState = GameState.getInstance();
    String name = "gameField";

    public GameField(int x, int y, int latitude, int longitude, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = -1;
        this.isField = false;
        this.keyH = keyH;
        this.gp = gp;
        this.tileM = tileM;
        this.player = player;
        this.type = 0;
        setImage("field");
    }

    void update(){

        if(plant!=null){
            plant.update();
        }

        if(hoeing){
            if(!isField) {
                isField = true;
                state = 0;
                setImage("hoeing");
                gp.playSoundEffect(1);
                gameState.setFieldState(this.latitude, this.longitude, name, state);
            }
            hoeing = false;
        }

        if(deleting){
            if(isField) {
                isField = false;
                state = -1;
                setImage("deleting");
                plant = null;
                gp.playSoundEffect(1);
                name = "";
                gameState.setFieldState(this.latitude, this.longitude, name, state);
            }
            deleting = false;
        }
        if(watering){
            if(isField && state == 1){
                state ++;
                setImage("watering");
                gp.playSoundEffect(4);
                gameState.setFieldState(this.latitude, this.longitude, state);
            }
            watering = false;
        }
        if(sowing){
            if(isField && state == 0){
                state ++;
                if(player.tool == 4)
                    plantCiecierzyca(0);
                if(player.tool == 5)
                    plantBratek(0);
                gp.playSoundEffect(2);
                gameState.setFieldState(this.latitude, this.longitude, state);
            }

            sowing = false;
        }
        if(harvesting){
            if(isField && state == 4){
                state = 0;
                setImage("harvesting");
                player.points += plant.pointValue;
                plant = null;
                gp.playSoundEffect(0);
                name = "";
                gameState.setFieldState(this.latitude, this.longitude, name, state);
            }
            harvesting = false;
        }
    }

    public void plantBratek(int state) {
        plant = new entity.Bratek(x, y, this.latitude, this.longitude, tileM, this, state);
    }

    public void plantCiecierzyca(int state) {
        plant = new entity.Ciecierzyca(x,y, this.latitude, this.longitude, tileM,this, state);
    }

    public void setImage(String image) {

        if(image.equals("hoeing")) {
            currentImage =  tileM.tileList.get(1).image[0];
        } else if(image.equals("deleting")) {
            currentImage =  tileM.tileList.get(0).image[tileM.randomNumber[x / gp.TILE_SIZE][y / gp.TILE_SIZE]];
        } else if(image.equals("watering")) {
            currentImage =  tileM.tileList.get(1).image[1];
        } else if(image.equals("harvesting")) {
            currentImage =  tileM.tileList.get(1).image[0];
        } else {
            currentImage = tileM.tileList.get(0).image[tileM.randomNumber[x/gp.TILE_SIZE][y/gp.TILE_SIZE]];
        }
    }

    public void setImage(int state) {
        if(state == -1) {
            setImage("");
        } else if (state == 0) {
            setImage("hoeing");
        } else if (state == 1) {
            setImage("hoeing");
        } else if(state >= 2) {
            setImage("watering");
        } else {
            setImage("");
        }
    }

    void draw(Graphics2D g2){

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        if(plant!=null) {
            g2.drawImage(plant.image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
    }
}
