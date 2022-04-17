package menu;

import game.CarID;
import game.Game;
import game.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame implements ActionListener {

    private CarID carID;    //which car is used
    private static int level;
    private boolean boughtCar2, boughtCar3, boughtCar4;  // true if the car was bought
    private static int carPoints;   //differs by cars, the prize is based on this

    Game game;      //the game panel
    Start start;    //panel
    End end;        //panel
    CardLayout layout;
    JPanel cardPanel;

    public static int coins;
    public static int car2Price = 1000;
    public static int car3Price = 5000;
    public static int car4Price = 15000;
    public static int endCoins;     //how much coins gets the player at the end of a race

    LostMusic gameOver;
    WonSound winner;
    public Menu(int width, int height) {
        //initializing
        boughtCar2 = boughtCar3 = boughtCar4 = false;
        carID = CarID.Car1;
        coins = 0;
        carPoints = 1;

        gameOver= new LostMusic();
        winner=new WonSound();

        layout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(layout);
        add(cardPanel);

        start = new Start();
        end = new End();
        cardPanel.add(start, "Start");
        cardPanel.add(end, "End");

        buttonListeners();

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Keyboard.typed(e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Keyboard.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Keyboard.keyReleased(e);
            }
        });

        Music backgroundMusic= new Music();
        backgroundMusic.play();
        setTitle("Drag race");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void buttonListeners() {
        JButton b1 = start.getB1();
        b1.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     carID = CarID.Car1;
                                     carPoints = 1;
                                 }
                             }
        );
        JButton b2 = start.getB2();
        b2.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     if (boughtCar2) {
                                         carID = CarID.Car2;
                                         carPoints = 2;
                                     }
                                 }
                             }
        );
        JButton b3 = start.getB3();
        b3.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     if (boughtCar3) {
                                         carID = CarID.Car3;
                                         carPoints = 3;
                                     }
                                 }
                             }
        );
        JButton b4 = start.getB4();
        b4.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     if (boughtCar4) {
                                         carID = CarID.Car4;
                                         carPoints = 4;
                                     }
                                 }
                             }
        );

        JButton buy2 = start.getBuy2();
        buy2.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       if (coins >= car2Price) {
                                           boughtCar2 = true;
                                           coins -= car2Price;
                                           buy2.setVisible(false);
                                       }
                                   }
                               }
        );
        JButton buy3 = start.getBuy3();
        buy3.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       if (coins >= car3Price) {
                                           boughtCar3 = true;
                                           coins -= car3Price;
                                           buy3.setVisible(false);
                                       }
                                   }
                               }
        );
        JButton buy4 = start.getBuy4();
        buy4.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       if (coins >= car4Price) {
                                           boughtCar4 = true;
                                           coins -= car4Price;
                                           buy4.setVisible(false);
                                       }
                                   }
                               }
        );

        JButton startButton = start.getStart();
        startButton.addActionListener(e -> {
            level = 1;          //every time its starts from level 1, this sets the enemy's car type
            endCoins = 50 * carPoints;
            game = new Game(carID, this, level);
            cardPanel.add(game, "Game");
            layout.show(cardPanel, "Game");
        });


        //end panel buttons
        JButton yes = end.getYes();
        yes.addActionListener(e -> {
            level += 1;
            game = new Game(game.getCarID(), this, level);
            cardPanel.add(game, "Game");
            layout.show(cardPanel, "Game");
        });

        JButton no = end.getNo();
        no.addActionListener(e -> {
            level = 1;
            layout.show(cardPanel, "Start");
            coins += endCoins;  //in case the player lost, the coins won't appear in the total coins
        });
    }


    public void changeToStart() {
        gameOver.play();
        start.repaint(); //repainting the coins
        layout.show(cardPanel, "Start");
    }

    public void changeToEnd() {
        winner.play();
        layout.show(cardPanel, "End");
        levelCoins();
    }

    public static void levelCoins() {
        endCoins *= 2;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        requestFocus();
    }
}