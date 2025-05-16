package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.io.Serializable;


public class GameField extends Field implements Serializable {

    transient entity.Plant plant;

    public GameField(int x, int y, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp){
        this.x = x;
        this.y = y;
        this.state = -1;
        this.isField = false;
        this.keyH = keyH;
        this.gp = gp;
        this.tileM = tileM;
        this.player = player;
        this.type = 0;
        currentImage = tileM.tileList.get(0).image[tileM.randomNumber[x/gp.TILE_SIZE][y/gp.TILE_SIZE]];

    }

    void update(){

        if(plant!=null){
            plant.update();
        }

        if(hoeing){
            if(!isField) {
                isField = true;
                state = 0;
                currentImage = tileM.tileList.get(1).image[0];
                gp.playSoundEffect(1);
            }
            hoeing = false;
        }

        if(deleting){
            if(isField) {
                isField = false;
                state = -1;
                currentImage = tileM.tileList.get(0).image[tileM.randomNumber[x / gp.TILE_SIZE][y / gp.TILE_SIZE]];
                plant = null;
                gp.playSoundEffect(1);

            }
            deleting = false;
        }
        if(watering){
            if(isField && state == 1){
                state ++;
                currentImage = tileM.tileList.get(1).image[1];
                gp.playSoundEffect(4);
            }
            watering = false;
        }
        if(sowing ){
            if(isField && state == 0){
                state ++;
                if(player.tool == 4)
                    plant = new entity.Ciecierzyca(x,y,tileM,this);
                if(player.tool == 5)
                    plant = new entity.Bratek(x,y,tileM,this);
                gp.playSoundEffect(2);
            }

            sowing = false;
        }
        if(harvesting ){
            if(isField && state == 4){
                state = 0;
                currentImage = tileM.tileList.get(1).image[0];
                player.points += plant.pointValue;
                plant = null;
                gp.playSoundEffect(0);
            }
            harvesting = false;
        }
    }

    void draw(Graphics2D g2){

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        if(plant!=null) {
            g2.drawImage(plant.image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
    }
}
