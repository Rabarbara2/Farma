package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public BufferedImage image;
    GamePanel gp;
    KeyHandler keyH;

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

}
