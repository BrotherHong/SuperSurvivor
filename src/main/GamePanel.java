package main;

import api.Drawable;
import entity.enemy.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GamePanel extends JPanel {
    private final Game game;
    private final List<Drawable> itemsToRender;
    private Set<Enemy> enemies;

    public GamePanel(Game game) {
        this.game = game;
        itemsToRender = new ArrayList<>();
        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void renderItem(Drawable item) {
        itemsToRender.add(item);
    }

    public void renderEnemies(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFPSAndUPS(g);
        itemsToRender.forEach(item -> item.draw(g));
        enemies.forEach(enemy -> enemy.draw(g));
    }

    private void drawFPSAndUPS(Graphics g) {
        String str = String.format("FPS: %d | UPS: %d", game.getFPS(), game.getUPS());
        g.drawString(str, 0, 10);
    }
}
