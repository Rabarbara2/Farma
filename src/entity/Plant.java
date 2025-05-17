package entity;

import main.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Plant extends Entity{

    GameState gameState = GameState.getInstance();
    public tile.TileManager tileM;
    public tile.GameField field;
    public int state;
    public int timer;
    public int pointValue;
    int growTime;
    int imageNumber;
    String name;
    public Random random = new Random();

    public void update() {
        if(field.state > 1){
            if(state < 2) {
                timer += 1;
                if (timer >= growTime) {
                    state += 1;
                    setImage(imageNumber, state);
                    field.state +=1;
                    timer = 0;
                    growTime = growTime + random.nextInt(100) - 50;
                    gameState.setFieldState(this.latitude, this.longitude, name, field.state, state);
                }
            }
        }
    }

    public void setImage(int imageNumber, int state) {
        image =  tileM.tileList.get(imageNumber).image[state];
    }

    public void draw(Graphics2D g2){

    }

}
