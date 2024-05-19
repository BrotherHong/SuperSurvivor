package main;

import api.EventListener;
import controllers.EnemyController;
import controllers.PlayerController;
import entity.Entity;
import entity.Hitbox;
import entity.Player;
import entity.enemy.Enemy;
import event.EnemyHitPlayerEvent;
import event.EventDispatcher;
import listeners.PlayerHurtListener;
import spawner.EnemySpawner;
import weapons.RotatedSword;

import java.util.HashSet;
import java.util.Set;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;

    private final EventDispatcher eventDispatcher;

    private final Player player;
    private final PlayerController playerController;

    private final Set<Enemy> enemies;
    private final EnemyController enemyController;
    private final EnemySpawner enemySpawner;
    private final int maxEnemyCount = 10;
    private int secondsPerEnemySpawn = 2;
    private int enemyCount = 0;
    private int enemySpawnTimer = 0;

    private Thread gameThread;
    private static final int FPS_SET = 120;
    private static final int UPS_SET = 120;
    private int lastFPS = 0;
    private int lastUPS = 0;

    public static final double deltaTime = 1.0 / UPS_SET;

    public Game() {
        // initialize window
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(this, gamePanel);
        gamePanel.requestFocus();

        // initialize event
        eventDispatcher = new EventDispatcher();
        registerEventListener(new PlayerHurtListener());

        // initialize player
        player = new Player(100, 100, 50, 50, 250);
        player.setWeapon(new RotatedSword(10));
        playerController = new PlayerController(player);
        gamePanel.addKeyListener(playerController);
        gamePanel.renderItem(player);

        // initialize enemy
        enemies = new HashSet<>();
        enemyController = new EnemyController(player, enemies);
        enemySpawner = new EnemySpawner(player, enemies);
        gamePanel.renderEnemies(enemies);

        // start game loop and show window
        startGameLoop();
        gameWindow.show();
    }

    private void registerEventListener(EventListener listener) {
        try {
            eventDispatcher.registerEventListener(listener);
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getFPS() {
        return lastFPS;
    }

    public int getUPS() {
        return lastUPS;
    }

    @Override
    public void run() {
        // fixed FPS
        double timePerFrame = 1000000000.0 / FPS_SET; // unit: nano time
        double timePerUpdate = 1000000000.0 / UPS_SET; // unit: nano time
        long previousNanoTime = System.nanoTime();

        // calculate FPS / UPS
        int frames = 0;
        int updates = 0;
        long lastTimeMillis = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentNanoTime = System.nanoTime();

            deltaU += (currentNanoTime - previousNanoTime) / timePerUpdate;
            deltaF += (currentNanoTime - previousNanoTime) / timePerFrame;
            previousNanoTime = currentNanoTime;

            // fixed UPS
            if (deltaU >= 1) {
                deltaU--;
                processUpdate();
                updates++;
            }

            // fixed FPS
            if (deltaF >= 1) {
                deltaF--;
                processFrame();
                frames++;
            }

            // calculate FPS
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastTimeMillis >= 1000) {
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                lastFPS = frames;
                lastUPS = updates;
                lastTimeMillis = currentTimeMillis;
                frames = 0;
                updates = 0;
            }
        }
    }

    private void processFrame() {
        gamePanel.repaint();
    }

    private void processUpdate() {
        playerController.movePlayer();
        enemyController.moveEnemies();
        processEnemySpawn();
        processCollision();
    }

    private void processEnemySpawn() {
        enemySpawnTimer++;
        if (enemySpawnTimer >= secondsPerEnemySpawn*UPS_SET) {
            enemySpawnTimer -= secondsPerEnemySpawn*UPS_SET;
            if (enemyCount < maxEnemyCount) {
                enemyCount++;
                enemySpawner.spawnZombie();
            }
        }
    }

    private void processCollision() {
        enemies.forEach(enemy -> {
            if (isCollided(player, enemy)) {
                eventDispatcher.dispatchEvent(new EnemyHitPlayerEvent(player, enemy));
            }
        });
    }

    private boolean isCollided(Entity e1, Entity e2) {
        Hitbox b1 = e1.getHitBox();
        Hitbox b2 = e2.getHitBox();
        if (b1.startX < b2.startX && b1.endX < b2.startX) return false;
        if (b2.startX < b1.startX && b2.endX < b1.startX) return false;
        if (b1.startY < b2.startY && b1.endY < b2.startY) return false;
        if (b2.startY < b1.startY && b2.endY < b1.startY) return false;
        return true;
    }

}
