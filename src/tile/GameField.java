package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;



public class GameField {

    int x,y;
    boolean isField = false;
    public boolean hoeing, watering, sowing, harvesting, deleting = false;
    int state; // 0-suche, 1-zasiane/suche, 2-zasiane/mokre, 3-rosnie, 4-gotowe, -1-nie pole
    int timer = 0;
    KeyHandler keyH;
    TileManager tileM;
    Player player;
    GamePanel gp;
    BufferedImage currentImage; //??
    // Plant plant - tworzony przy update zasiania, ustawiany na null przy zebraniu
    //               chyyyba

    public GameField(int x, int y, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp){
        this.x = x;
        this.y = y;
        this.state = -1;
        this.isField = false;
        this.keyH = keyH;
        this.gp = gp;
        this.tileM = tileM;
        this.player = player;
        currentImage = tileM.tiles[0].image[tileM.randomNumber[x/gp.TILE_SIZE][y/gp.TILE_SIZE]];
        //                   tiles[0 jesli trawa, 1 jesli pole itp]
    }

    public void update(){

        // to niech sprawdza TileManager + interakcje inne (zasianie)
        if(keyH.spacePressed){
            tileM.mapTiles[player.x/gp.TILE_SIZE][player.y/gp.TILE_SIZE] = 1;
            System.out.println("spacja!");
            keyH.spacePressed = false;
        }
        // -----------------
        if(isField){ //                          v dojrzałe state
            if(state != 0 && state!=1 && state != 4 ){
                timer++; //  v roslinka.timerZmianyStanu[stan]?
                if(timer == 99){
                    // osobno pole i roslinka ehhh
                    // idk bool moze   //nwm o co mi chodzilo ale zostawie z nadzieją ze sb przypomne
                    state++;  // to zostaje bo pole musi zmienić stan
                  //roslinka.state++  // też bo urosla
       //v roslinka.    bo pole sie nie zmienia wsumie na timerze
                    currentImage = tileM.tiles[1].image[state];
                }
            }
        }
        if(hoeing && !isField){
            isField = true;
            state = 0;
            hoeing = false;
            currentImage = tileM.tiles[1].image[0];
        }
        if(deleting && isField){
            isField = false;
            state = -1;
            currentImage = tileM.tiles[0].image[tileM.randomNumber[x/gp.TILE_SIZE][y/gp.TILE_SIZE]];
            deleting = false;
        }
        if(watering && isField && state == 1){
            state ++;
            currentImage = tileM.tiles[1].image[1];
            watering = false;
        }
        if(sowing && isField && state == 0){
            state ++;
          //plant = new Plant(rodzaj roslinki itp)
            sowing = false;
        }
        if(harvesting && isField && state == 4){
            state = 0;
            currentImage = tileM.tiles[1].image[0];
            harvesting = false;
          //plant = null   // ????
        }



        // podlej, zwiększ wszystkim zasianym timer, zmień stan jak timer = timerZmianyStanu, zatrzymaj timer jeśli
        // dojrzałe, podmień obrazki jeśli zmiana stanu (albo bierz obrazki od stanu),
    }

    public void draw(Graphics2D g2){
        // rysuj osobno roślinke i pole

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
