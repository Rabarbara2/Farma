package entity;

public class Bratek extends Plant {

    int variety;


    public Bratek(int x, int y, tile.TileManager tileM, tile.GameField gField) {
        this.x = x;
        this.y = y;
        this.tileM = tileM;
        this.field = gField;
        this.imageNumber = 5;
        this.image = tileM.tileList.get(imageNumber).image[0];
        this.timer = 0;
        this.state = 0;
        this.pointValue = 5;
        this.growTime = 500 + random.nextInt(200);
        this.variety = random.nextInt(3) + 2;
    }

    public void update() {
        if(field.state > 1){
            if(state < 2) {
                timer += 1;
                if (timer >= growTime) {
                    state += 1;
                    if (state < 2) {
                        image = tileM.tileList.get(imageNumber).image[state];
                    }
                    else{
                        image = tileM.tileList.get(imageNumber).image[variety];
                    }

                    field.state +=1;
                    timer = 0;
                    growTime = growTime + random.nextInt(100) - 50;
                }
            }
        }
    }

}
