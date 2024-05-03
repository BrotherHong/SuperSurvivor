package objects;

import api.Drawable;
import weapons.Weapon;

import java.awt.*;

public class Player implements Drawable {
    private int x;
    private int y;
    private int speed;
    private Weapon weapon;

    private int width = 50;
    private int height = 50;

    public Player(int startX, int startY, int speed) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
        weapon.draw(g);
    }

    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    public void moveLeft() {
        this.x -= speed;
    }

    public void moveRight() {
        this.x += speed;
    }

    public int getX() {
        return x + width / 2;
    }

    public int getY() {
        return y + height / 2;
    }

    public void setWeapon(Weapon weapon) {
        weapon.setOwner(this);
        this.weapon = weapon;
    }
}
