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
    public int state; // 0-suche, 1-zasiane/suche, 2-zasiane/mokre, 3-rosnie, 4-gotowe, -1-nie pole

    //for tools
    public boolean pickedUp, pickingUp = false;
    public int tool;

    int x,y;
    int latitude; // x
    int longitude; // y
    int type;
    transient KeyHandler keyH;
    transient TileManager tileM;
    transient Player player;
    transient GamePanel gp;
    transient BufferedImage currentImage;

    void update() {

    }
    void draw(Graphics2D g2)
    {

    }
}
