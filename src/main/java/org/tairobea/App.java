package org.tairobea;

import javax.swing.*;

public class App {
    int boardWidth = 360;
    int boardHeight = 640;

    JFrame frame = new JFrame("pájaro flappy");
    public App(){
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        panel.requestFocus();
        frame.setVisible(true);
    }
}
