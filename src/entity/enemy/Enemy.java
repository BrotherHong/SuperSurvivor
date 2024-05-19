package entity.enemy;

import api.Drawable;
import entity.Entity;

public abstract class Enemy extends Entity implements Drawable {
    protected int health;
    protected int attackPower;
    protected float speed;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
