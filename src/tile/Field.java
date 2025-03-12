package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Field {

    int x,y;
    int type;
    KeyHandler keyH;
    TileManager tileM;
    Player player;
    GamePanel gp;
    BufferedImage currentImage;

    abstract void update();
    abstract void draw(Graphics2D g2);
}
