package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Field {


    //for fields
    boolean isField;
    public boolean hoeing, watering, sowing, harvesting, deleting, interaction = false;
    int state; // 0-suche, 1-zasiane/suche, 2-zasiane/mokre, 3-rosnie, 4-gotowe, -1-nie pole
    int timer = 0;

    //for tools
    public boolean pickedUp, pickingUp = false;
    public int tool;
    // 0-nic
    // 1-motyka
    // 2-podlewaczka
    // 3-kosa
    // 4-trawsko


    int x,y;
    int type;
    KeyHandler keyH;
    TileManager tileM;
    Player player;
    GamePanel gp;
    BufferedImage currentImage;

    void update()
    {

    }
    void draw(Graphics2D g2)
    {

    }
}
