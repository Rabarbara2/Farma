package entity;

import main.GameState;

public class Bratek extends Plant {

    int variety;
    GameState gameState = GameState.getInstance();

    public Bratek(int x, int y, int latitude, int longitude, tile.TileManager tileM, tile.GameField gField, int state) {
        this.x = x;
        this.y = y;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = "Bratek";
        this.tileM = tileM;
        this.field = gField;
        this.imageNumber = 5;
        setImage(imageNumber, 0);
        this.timer = 0;
        this.state = state;
        this.pointValue = 5;
        this.growTime = 100 + random.nextInt(200);
        this.variety = random.nextInt(3) + 2;
        gameState.setFieldState(this.latitude, this.longitude, name, field.state, state, variety);
    }

    public void update() {
        if(field.state > 1){
            if(state < 2) {
                timer += 1;
                if (timer >= growTime) {
                    state += 1;
                    if (state < 2) {
                        setImage(imageNumber, state);
                    }
                    else{
                        setImage(imageNumber, variety);
                    }

                    field.state +=1;
                    timer = 0;
                    growTime = growTime + random.nextInt(100) - 50;
                    gameState.setFieldState(this.latitude, this.longitude, name, field.state, state, variety);
                }
            }
        }
    }

}
