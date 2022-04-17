package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private int x, y;
    private CarID carid;

    private BufferedImage img;

    //the player doesn't move
    public Player(CarID carid) {
        x = 400;
        y = 100;
        this.carid = carid;
        drawImage();
    }

    private void drawImage() {

        if (carid == CarID.Car1) {
            try {
                img = ImageIO.read(new File("car1.png"));
            } catch (IOException e) {
                System.out.println("car1.png not found");
            }
        } else if (carid == CarID.Car2) {
            try {
                img = ImageIO.read(new File("car2.png"));
            } catch (IOException e) {
                System.out.println("car2.png not found");
            }
        } else if (carid == CarID.Car3) {
            try {
                img = ImageIO.read(new File("car3.png"));
            } catch (IOException e) {
                System.out.println("car3.png not found");
            }
        } else if (carid == CarID.Car4) {
            try {
                img = ImageIO.read(new File("car4.png"));
            } catch (IOException e) {
                System.out.println("car4.png not found");
            }
        }
    }

    public int getX() {
        return x;
    }

    public CarID getCarId() {
        return carid;
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }

}
