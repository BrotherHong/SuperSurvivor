package main;

import controllers.PlayerController;
import objects.Player;
import weapons.RotatedSword;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;

    private final Player player;
    private final PlayerController playerController;

    private Thread gameThread;
    private final int FPS_SET = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        player = new Player(100, 100, 3);
        player.setWeapon(new RotatedSword(10));
        playerController = new PlayerController(player);

        gamePanel.addKeyListener(playerController);

        gamePanel.draw(player);

        startGameLoop();
        gameWindow.show();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // fixed FPS
        double timePerFrame = 1000000000.0 / FPS_SET; // unit: nano time
        long lastNanoTime = System.nanoTime();

        // calculate FPS
        int frame = 0;
        long lastTimeMillis = System.currentTimeMillis();

        while (true) {
            long currentNanoTime = System.nanoTime();
            // fixed FPS
            if (currentNanoTime - lastNanoTime >= timePerFrame) {
                lastNanoTime = currentNanoTime;
                processGameLoop();
                frame++;
            }

            // calculate FPS
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastTimeMillis >= 1000) {
                System.out.println("FPS: " + frame);
                lastTimeMillis = currentTimeMillis;
                frame = 0;
            }
        }
    }

    private void processGameLoop() {
        playerController.movePlayer();
        gamePanel.repaint();
    }
}
