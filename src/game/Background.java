package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    private int x, velX;
    private BufferedImage img;

    public Background() {
        try {
            img = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            System.out.println("background error");
        }
        x = 0;
        velX = 1;
    }

    //updates
    public void tick() {
        x -= velX;
        x = Board.clamp(x, -2550, Game.WIDTH - 100);
    }

    //repaints
    public void render(Graphics g) {
        g.drawImage(img, x, 0, null);
    }

    public int getX() {
        return x;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }
}
