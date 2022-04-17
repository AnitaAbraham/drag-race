package game;


import menu.GearSound;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Board {

    private Player player;
    private Enemy enemy;
    private Background background;
    private Speeding speeding;

    private int level;  //sets the difficulty, by changing the enemy's car
    private boolean win; // the player's win/lost status
    private boolean winEnemy; // the enemy's win/lost status

    GearSound gearSound;
    public Board(CarID carID, int level) {
        gearSound = new GearSound();
        speeding = new Speeding(carID); // the speeding bar
        background = new Background();
        player = new Player(carID);
        enemy = new Enemy();
        win = false;
        this.level = level;
        level(level);
    }

    //sets the difficulty
    public void level(int level) {
        if (level >= 4) //there's just 4 car type
            level = 4;
        enemy.setDifficulty(level);
        background.setVelX(level * 4);
    }

    //who won
    public void end() {
        if (enemy.getX() >= player.getX()) {  // the player's X value , the player doesn't move
            winEnemy = true;
            win = false;
            System.out.println("The enemy won.");
        } else if (enemy.getVelX() < player.getX()) {
            win = true;
            winEnemy = false;
            enemy.setVelX(2);
            System.out.println("The player won.");
        }
    }

    //updating
    public void tick() {
        // if the road is finished and neither of cars has yet won
        if (background.getX() == -2550 && !win && !winEnemy) {
            end();
        }
        checkKeys(); // if w,a,s,d is pressed
        background.tick(); //updating the background
        enemy.tick();   //updating the enemy
        speeding.tick();    //updating the s bar
    }

    //repainting the objects
    public void render(Graphics g) {
        background.render(g);
        player.render(g);
        enemy.render(g);
        speeding.render(g);
    }

    //clamps the values between a max and a min value
    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return max;
        if (var <= min)
            return min;
        return var;
    }

    //moves the enemy and the background speed depending on level, player's car type, enemy's car type
    private void changeGear(int yellow, int green, int red) {
        if (speeding.getAcceleration() < 550) {  //orange zone
            enemy.setVelX(-yellow);
        } else if (speeding.getAcceleration() >= 550 && speeding.getAcceleration() <= 650) {    //green zone
            if (enemy.getX() < 0) {     //if the enemy is out of picture for the players to pick up speed the background moves faster
                background.setVelX(background.getVelX() + 3);
            } else                      //the enemy moves backward => the player 'moves forward'
                enemy.setVelX(enemy.getVelX() - green);
        } else if (speeding.getAcceleration() >= 650) {  //red zone
            enemy.setVelX(enemy.getVelX() + red);       //the enemy moves forward, because of player's penalty
        }
        enemy.setVelX(enemy.getVelX() + level / 2);  //the enemy is speeding up too
        speeding.setGear(speeding.getGear() - 1);
        speeding.setAcceleration(0);
        if (background.getX() == -2500) {
            speeding.setGear(0);
        }
    }

    //sets difficulty based on player's car
    private void changeGearByCar() {
        if (player.getCarId() == CarID.Car1) {
            changeGear(1, 1, 1);

        } else if (player.getCarId() == CarID.Car2) {
            changeGear(1, 2, 1);

        } else if (player.getCarId() == CarID.Car3) {
            changeGear(2, 3, 2);

        } else if (player.getCarId() == CarID.Car4) {
            changeGear(2, 4, 2);

        }
    }

    private void checkKeys() {
        //if a,s,d or w is pressed
        if (Keyboard.typed(KeyEvent.VK_A) || Keyboard.typed(KeyEvent.VK_S) || Keyboard.typed(KeyEvent.VK_W) || Keyboard.typed(KeyEvent.VK_D)) {
            if(speeding.getGear()>0) {
                gearSound.play();
                changeGearByCar();
            }
        }
        if (Keyboard.typed(KeyEvent.VK_ESCAPE)) {
            System.exit(1);
        }
    }

    public boolean isWin() {
        return win;
    }

    public boolean isWinEnemy() {
        return winEnemy;
    }
}
