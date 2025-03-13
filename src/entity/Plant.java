package entity;

public class Plant extends Entity{

    public tile.TileManager tileM;
    public int state;
    public int timer;

    public Plant(int x, int y, tile.TileManager tileM) {
        this.x = x;
        this.y = y;
        this.tileM = tileM;
    }


}
