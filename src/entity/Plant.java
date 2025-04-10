package entity;

import java.awt.*;

public class Plant extends Entity{

    public tile.TileManager tileM;
    public int state;
    public int timer;

    public Plant(int x, int y, tile.TileManager tileM) {
        this.x = x;
        this.y = y;
        this.tileM = tileM;
        this.image = tileM.tiles[4].image[0];
    }

    public void draw(Graphics2D g2){
        super.draw(g2);
    }



}
