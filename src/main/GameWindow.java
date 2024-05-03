package main;

import javax.swing.*;

public class GameWindow {
    private final JFrame jframe;
    private final GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.jframe = new JFrame();

        buildJFrame();
    }

    private void buildJFrame() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);

        jframe.add(gamePanel);

        jframe.pack(); // to fit panel size
        jframe.setLocationRelativeTo(null);
    }

    public void show() {
        jframe.setVisible(true);
    }
}
