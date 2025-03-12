package tile;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class ToolField extends Field {

    public boolean pickedUp, interaction = false;
    public final int tool;
    // 0-nic
    // 1-motyka
    // 2-podlewaczka
    // 3-kosa
    // 4-trawa

// TODO uhhhh przenies to do gamefield I guess

    public ToolField(int x, int y, KeyHandler keyH, TileManager tileM, Player player, GamePanel gp, int tool) {
        this.x = x;
        this.y = y;
        this.keyH = keyH;
        this.tileM = tileM;
        this.player = player;
        this.gp = gp;
        this.tool = tool;
        this.type = 1;

    }

    void update() {

        if (interaction && !pickedUp) {
            pickedUp = true;
            player.tool = tool;
            interaction = false;
        }
        if (interaction && pickedUp) {
            pickedUp = false;
            player.tool = 0;
            interaction = false;
        }

    }
    void draw(Graphics2D g2) {

        // draw floor

        if (!pickedUp) {
            //draw tool
        }
    }
}
