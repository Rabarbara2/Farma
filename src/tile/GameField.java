package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;


public class GameField extends Field {

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
        this.type = 0;
        currentImage = tileM.tiles[0].image[tileM.randomNumber[x/gp.TILE_SIZE][y/gp.TILE_SIZE]];

    }

    void update(){


        // -----------------
//        if(isField){ //                          v dojrzałe state
//            if(state != 0 && state!=1 && state != 4 ){
//                timer++; //  v roslinka.timerZmianyStanu[stan]?
//                if(timer == 99){
//                    // osobno pole i roslinka ehhh
//                    // idk bool moze   //nwm o co mi chodzilo ale zostawie z nadzieją ze sb przypomne
//                    state++;  // to zostaje bo pole musi zmienić stan
//                  //roslinka.state++  // też bo urosla
//       //v roslinka.    bo pole sie nie zmienia wsumie na timerze
//                    currentImage = tileM.tiles[1].image[state];
//                }
//            }
//        }\

        if(hoeing){
            if(!isField) {
                isField = true;
                state = 0;
                currentImage = tileM.tiles[1].image[0];

            }
            hoeing = false;
        }

        if(deleting){
            if(isField) {
                isField = false;
                state = -1;
                currentImage = tileM.tiles[0].image[tileM.randomNumber[x / gp.TILE_SIZE][y / gp.TILE_SIZE]];
                //roslinka = null

            }
            deleting = false;
        }
        if(watering){
            if(isField && state == 3){
                state ++;
                currentImage = tileM.tiles[1].image[1];
            }
            watering = false;
        }
        if(sowing ){
            if(isField && state == 0){
                state =3;
            }
            //System.out.println("sadzic palic zalegalizowac");
          //plant = new Plant(rodzaj roslinki itp)
            sowing = false;
        }
        if(harvesting ){
            if(isField && state == 4){
                state = 0;
                currentImage = tileM.tiles[1].image[0];
            }
            harvesting = false;
          //plant = null   // ????
        }

        // podlej, zwiększ wszystkim zasianym timer, zmień stan jak timer = timerZmianyStanu, zatrzymaj timer jeśli
        // dojrzałe, podmień obrazki jeśli zmiana stanu (albo bierz obrazki od stanu),
    }

    void draw(Graphics2D g2){
        // rysuj osobno roślinke i pole

        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

        // chwilowe rysowanie roslinki
        if(state >= 1){
            g2.drawImage(player.toolImages.image[5], x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
