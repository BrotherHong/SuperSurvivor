package spawner;

import entity.Player;
import entity.enemy.Enemy;
import entity.enemy.Zombie;

import java.util.Random;
import java.util.Set;

public class EnemySpawner {
    private final Player player;
    private final Set<Enemy> enemies;
    private final Random random;

    private final int safeRadius = 100;
    private final int maxRadius = 500;

    public EnemySpawner(Player player, Set<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.random = new Random();
    }

    public void spawnZombie() {
        int[] pos = getSpawnPositionRandomly();
        Zombie zombie = new Zombie(pos[0], pos[1]);
        enemies.add(zombie);
        System.out.println("Zombie Spawned!");
    }

    private int[] getSpawnPositionRandomly() {
        int[] pos = new int[2];
        int radius = random.nextInt(maxRadius-safeRadius) + safeRadius;
        int degree = random.nextInt(360);

        int deltaX = (int) (radius * Math.cos(Math.toRadians(degree)));
        int deltaY = (int) (radius * Math.sin(Math.toRadians(degree)));

        pos[0] = player.getX() + deltaX;
        pos[1] = player.getY() + deltaY;

        return pos;
    }
}
