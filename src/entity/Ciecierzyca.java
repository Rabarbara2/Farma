package entity;

public class Ciecierzyca extends Plant {


    public Ciecierzyca(int x, int y, int latitude, int longitude, tile.TileManager tileM, tile.GameField gField, int state) {
        this.x = x;
        this.y = y;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = "Ciecierzyca";
        this.tileM = tileM;
        this.field = gField;
        this.image = tileM.tileList.get(4).image[0];
        this.timer = 0;
        this.state = state;
        this.pointValue = 1;
        this.growTime = 200 + random.nextInt(200);
        this.imageNumber = 4;
        gameState.setFieldState(this.latitude, this.longitude, name, field.state, state);
    }
}
