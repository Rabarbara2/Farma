package entity;

import java.awt.*;
import java.util.Random;

public class Plant extends Entity{

    public tile.TileManager tileM;
    public tile.GameField field;
    public int state;
    public int timer;
    public int pointValue;
    int growTime;
    int imageNumber;
    public Random random = new Random();

    public void update() {
        if(field.state > 1){
        if(state < 2) {
            timer += 1;
            if (timer >= growTime) {
                state += 1;
                image = tileM.tiles[imageNumber].image[state];
                field.state +=1;
                timer = 0;
                growTime = growTime + random.nextInt(100) - 50;
            }
        }
        }
    }

    public void draw(Graphics2D g2){

    }

}
