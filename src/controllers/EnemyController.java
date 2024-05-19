package controllers;

import entity.Player;
import entity.enemy.Enemy;
import main.Game;

import java.util.Set;

public class EnemyController {
    private final Player player;
    private final Set<Enemy> enemies;

    public EnemyController(Player player, Set<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
    }

    public void moveEnemies() {
        enemies.forEach(enemy -> {
            // get unit vector (vx, vy) from enemy to player
            float vx = player.getRawX() - enemy.getRawX();
            float vy = player.getRawY() - enemy.getRawY();
            float vLen = (float) Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));

            // System.out.println("vx: " + vx + " | vy: " + vy);

            float speed = enemy.getSpeed();
            float targetX = enemy.getRawX() + (float) ((vx / vLen) * (speed * Game.deltaTime));
            float targetY = enemy.getRawY() + (float) ((vy / vLen) * (speed * Game.deltaTime));

            // System.out.println("targetX: " + targetX + " | targetY: " + targetY);

            enemy.moveTo(targetX, targetY);
        });
    }

}
