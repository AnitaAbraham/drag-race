package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {
    private int x, velX;
    private BufferedImage img;

    public Enemy() {
        x = 400;
        velX = 0;
        try {
            img = ImageIO.read(new File("car1.png"));
        } catch (IOException e) {
            System.out.println("car1.png not found");
        }
    }

    //updates
    public void tick() {
        x += velX;
        x = Board.clamp(x, -200, Game.WIDTH - 400);
    }

    //repaint
    public void render(Graphics g) {
        g.drawImage(img, x, 200, null);
    }

    //sets difficulty by level
    public void setDifficulty(int level) {
        if (level == 1) {
            velX = 0;
        } else if (level == 2) {
            try {
                img = ImageIO.read(new File("car2.png"));
            } catch (IOException e) {
                System.out.println("car2.png not found");
            }
            velX = 0;
        } else if (level == 3) {
            try {
                img = ImageIO.read(new File("car3.png"));
            } catch (IOException e) {
                System.out.println("car3.png not found");
            }
            velX = 1;
        } else if (level == 4) {
            try {
                img = ImageIO.read(new File("car4.png"));
            } catch (IOException e) {
                System.out.println("car4.png not found");
            }
            velX = 1;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getVelX() {
        return this.velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

}
