package game;

import java.awt.event.KeyEvent;


public class Keyboard {

    public static boolean[] pressed = new boolean[256];
    public static boolean[] prev = new boolean[256];

    private Keyboard() {}

    public static void update() {
        for (int i = 0; i < 4; i++) {
            if (i == 0) prev[KeyEvent.VK_W] = pressed[KeyEvent.VK_W];
            if (i == 1) prev[KeyEvent.VK_A] = pressed[KeyEvent.VK_A];
            if (i == 2) prev[KeyEvent.VK_S] = pressed[KeyEvent.VK_S];
            if (i == 3) prev[KeyEvent.VK_D] = pressed[KeyEvent.VK_D];
        }
    }

    public static void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    public static void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }

    public static boolean typed(int keyEvent) {
        return !pressed[keyEvent] && prev[keyEvent];
    }
}
