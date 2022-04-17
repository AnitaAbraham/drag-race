package menu;

import javax.swing.*;
import java.awt.*;

public class End extends JPanel {
    JButton no, yes;
    JLabel coinsLabel;

    public End() {
        //Price label
        Font font = new Font("Serif", Font.BOLD, 120);
        JLabel title = new JLabel();
        title.setBounds(350, 50, 300, 100);
        title.setText("Prize");
        title.setFont(font);
        title.setForeground(new Color(0x66D8EB));
        add(title);

        //shows how much money the player won
        Font fnt = new Font("Serif", 0, 40);
        coinsLabel = new JLabel();
        coinsLabel.setBounds(360, 200, 500, 50);
        coinsLabel.setText("Coins won: " + Menu.endCoins);
        coinsLabel.setFont(fnt);
        coinsLabel.setForeground(new Color(0xD3FCE3));
        add(coinsLabel);

        //question label
        JLabel doubleCoins = new JLabel();
        doubleCoins.setBounds(360, 270, 500, 50);
        doubleCoins.setText("Double your coins?");
        doubleCoins.setFont(fnt);
        doubleCoins.setForeground(new Color(0xD3FCE3));
        add(doubleCoins);

        //yes button
        fnt = new Font("Serif", 0, 20);
        yes = new JButton();
        yes.setText("Yes");
        yes.setFont(fnt);
        yes.setForeground(Color.WHITE);
        yes.setBackground(new Color(0x223e52));
        yes.setBounds(400, 350, 80, 40);
        add(yes);

        //no button
        no = new JButton();
        no.setText("No");
        no.setFont(fnt);
        no.setForeground(Color.WHITE);
        no.setBackground(new Color(0x223e52));
        no.setBounds(550, 350, 80, 40);
        add(no);

        setBackground(new Color(0x003e52));
        setBounds(0, 0, 1000, 500);
        setLayout(null);
        setVisible(true);

    }

    public JButton getNo() {
        return no;
    }

    public JButton getYes() {
        return yes;
    }

    //repaints the player's won coins
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        coinsLabel.setText("Coins won: " + Menu.endCoins);
    }
}
