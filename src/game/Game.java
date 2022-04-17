package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, Runnable {

    public static final int WIDTH = 1000, HEIGHT = 500;
    public static boolean running = false;

    private Thread thread;
    private Board board; //sets up the game
    private CarID carID; // which car will the player drive
    private menu.Menu menu;

    public Game(CarID carID, menu.Menu menu, int level) {
        this.menu = menu;
        this.carID = carID;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        board = new Board(carID, level);
        this.start();
        addKeyListener(this);
    }


    public synchronized void start() {
        if (running) return;
        thread = new Thread(this);
        running = true;
        thread.start();  //run()
    }

    public void stop() {
        try {
            running = false;
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (running) {
            render();  //repaints
            try {
                tick(); //update;
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (board.isWinEnemy() || board.isWin()) {
                running = false;
                repaint();  // changes panels
            }
        }
        stop();
    }

    //updating the game
    private void tick() {
        board.tick();
        Keyboard.update();
    }

    //repaints the objects
    private void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!running) {                 //the game finished
            if (board.isWin())          //player won, changes panel to end panel
                menu.changeToEnd();
            else
                menu.changeToStart();   //enemy won, changes panel to start panel
        } else {                        //the game still runs
            board.render(g);
        }

    }

    public CarID getCarID() {
        return carID;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keyboard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.keyReleased(e);
    }

}
