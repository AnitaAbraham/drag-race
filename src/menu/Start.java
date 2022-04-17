package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Start extends JPanel {
    private JButton start;
    private JButton b1, b2, b3, b4;
    private JButton buy2, buy3, buy4;
    private JLabel coins;

    public Start() {

        //title
        Font font = new Font("Serif", Font.BOLD, 90);
        JLabel title = new JLabel();
        title.setBounds(100, 50, 300, 100);
        title.setText("Cars");
        title.setFont(font);
        title.setForeground(new Color(0x66D8EB));
        add(title);

        //coins label
        Font fnt = new Font("Serif", 1, 30);
        coins = new JLabel();
        coins.setBounds(800, 0, 200, 50);
        coins.setText("Coins: " + Menu.coins);
        coins.setFont(fnt);
        coins.setForeground(new Color(0xD3FCE3));
        add(coins);

        //car buttons
        Icon icon1 = new ImageIcon("car1.png");
        b1 = new JButton();
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setIcon(icon1);
        b1.setBounds(40, 200, 200, 100);
        add(b1);

        Icon icon2 = new ImageIcon("car2.png");
        b2 = new JButton();
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b2.setIcon(icon2);
        b2.setBounds(280, 200, 200, 100);
        add(b2);

        Icon icon3 = new ImageIcon("car3.png");
        b3 = new JButton();
        b3.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        b3.setIcon(icon3);
        b3.setBounds(500, 200, 200, 100);
        add(b3);

        Icon icon4 = new ImageIcon("car4.png");
        b4 = new JButton();
        b4.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b4.setIcon(icon4);
        b4.setBounds(730, 200, 200, 100);
        add(b4);

        //buy car buttons
        fnt = new Font("Serif", 1, 20);
        buy2 = new JButton();
        buy2.setBackground(new Color(0x003e52));
        buy2.setForeground(Color.WHITE);
        buy2.setFont(fnt);
        buy2.setText("Buy car:" + Menu.car2Price + " C");
        buy2.setBounds(280, 350, 200, 50);
        add(buy2);

        buy3 = new JButton();
        buy3.setBackground(new Color(0x003e52));
        buy3.setForeground(Color.WHITE);
        buy3.setFont(fnt);
        buy3.setText("Buy car:" + Menu.car3Price + " C");
        buy3.setBounds(500, 350, 200, 50);
        add(buy3);

        buy4 = new JButton();
        buy4.setBackground(new Color(0x003e52));
        buy4.setForeground(Color.WHITE);
        buy4.setFont(fnt);
        buy4.setText("Buy car:" + Menu.car4Price + " C");
        buy4.setBounds(730, 350, 200, 50);
        add(buy4);

        //start button
        start = new JButton();
        start.setBackground(new Color(0x223e52));
        start.setText("Start");
        start.setFont(fnt);
        start.setForeground(Color.WHITE);
        start.setBounds(800, 100, 80, 50);
        add(start);

        setBackground(new Color(0x003e52));
        setBounds(0, 0, 1000, 500);
        setLayout(null);
        setVisible(true);
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getBuy2() {
        return buy2;
    }

    public JButton getBuy3() {
        return buy3;
    }

    public JButton getBuy4() {
        return buy4;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        coins.setText("Coins: " + Menu.coins);
    }
}
