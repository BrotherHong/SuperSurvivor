package controllers;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    private final Player player;

    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void movePlayer() {
        if (isUpPressed) {
            player.moveUp();
        }
        if (isDownPressed) {
            player.moveDown();
        }
        if (isLeftPressed) {
            player.moveLeft();
        }
        if (isRightPressed) {
            player.moveRight();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            isUpPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            isLeftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            isDownPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            isRightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            isUpPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            isLeftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            isDownPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            isRightPressed = false;
        }
    }
}
