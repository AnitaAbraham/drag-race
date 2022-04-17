package game;

import java.awt.*;

public class Speeding {

    private int acceleration; //how fast the white bar grows
    private int gear;  //how many times can change gear, depends on the car type

    public Speeding(CarID carID) {
        acceleration = 0;
        if (carID == CarID.Car1 || carID == CarID.Car2) {
            gear = 3;
        } else gear = 4;
    }

    //updates
    public void tick() {
        if (gear > 0) {
            acceleration += 10;
            acceleration = Board.clamp(acceleration, 0, 800 - 1);
        }
    }

    //paints the bar, and the animation for the white bar
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(100, 370, 550, 50);
        g.setColor(Color.green);
        g.fillRect(650, 370, 100, 50);
        g.setColor(Color.red);
        g.fillRect(750, 370, 150, 50);
        g.setColor(Color.white);
        g.fillRect(101, 370, acceleration, 50); // animates the white bar
        g.setColor(Color.BLACK);
        g.drawRect(100, 370, 800, 50);

    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }


}
