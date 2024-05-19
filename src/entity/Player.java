package entity;

import api.Drawable;
import main.Game;
import weapons.Weapon;

import java.awt.*;

public class Player extends Entity implements Drawable {
    private int health;
    private float speed;
    private Weapon weapon;

    public Player(float x, float y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        this.health = 100;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect((int)(x - width/2), (int)(y - height/2), width, height);
        weapon.draw(g);
    }

    public void moveUp() {
        this.y -= speed * Game.deltaTime;
    }

    public void moveDown() {
        this.y += speed * Game.deltaTime;
    }

    public void moveLeft() {
        this.x -= speed * Game.deltaTime;
    }

    public void moveRight() {
        this.x += speed * Game.deltaTime;
    }

    public void setWeapon(Weapon weapon) {
        weapon.setOwner(this);
        this.weapon = weapon;
    }
}
