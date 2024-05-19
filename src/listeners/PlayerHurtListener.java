package listeners;

import api.EventListener;
import entity.Player;
import entity.enemy.Enemy;
import event.EnemyHitPlayerEvent;
import event.EventHandler;

public class PlayerHurtListener implements EventListener {

    @EventHandler
    public void onPlayerHitByEnemy(EnemyHitPlayerEvent event) {
        Player player = event.getPlayer();
        Enemy enemy = event.getEnemy();
        System.out.println("Player hit by enemy");
    }

}
