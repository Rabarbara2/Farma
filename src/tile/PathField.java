package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class PathField extends Field{

    public PathField(int x, int y, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp){
        this.x = x;
        this.y = y;
        this.keyH = keyH;
        this.gp = gp;
        this.tileM = tileM;
        this.player = player;
        this.type = 3;
        currentImage = tileM.tiles[2].image[0];
    }
    void draw(Graphics2D g2) {

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
