package entity;

public class Ciecierzyca extends Plant {


    public Ciecierzyca(int x, int y, tile.TileManager tileM, tile.GameField gField) {
        this.x = x;
        this.y = y;
        this.tileM = tileM;
        this.field = gField;
        this.image = tileM.tiles[4].image[0];
        this.timer = 0;
        this.state = 0;
        this.pointValue = 1;
        this.growTime = 200 + random.nextInt(200);
        this.imageNumber = 4;
    }
}
