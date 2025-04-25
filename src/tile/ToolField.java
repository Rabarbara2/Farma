package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class ToolField extends Field{

    public ToolField(int x, int y, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp, int tool){
        this.x = x;
        this.y = y;
        this.keyH = keyH;
        this.gp = gp;
        this.tileM = tileM;
        this.player = player;
        this.type = 1;
        this.tool = tool;
        currentImage = tileM.tiles[2].image[0];
    }
    void update(){

        if(pickingUp){

            if(player.tool == -1 ){
                gp.playSoundEffect(6);
                player.tool = tool;
                pickedUp = true;
            }
            else if(player.tool != -1 && pickedUp){
                gp.playSoundEffect(6);
                player.tool = -1;
                pickedUp = false;
            }
            pickingUp = false;
        }

    }

    void draw(Graphics2D g2){

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

        if(type == 1 && !pickedUp){
            g2.drawImage(player.toolImages.image[tool], x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
