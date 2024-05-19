package entity;

import java.awt.*;

public abstract class Entity {
    protected float x;
    protected float y;
    protected int width;
    protected int height;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getRawX() {
        return x;
    }

    public float getRawY() {
        return y;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Hitbox getHitBox() {
        return new Hitbox(getX()-width/2, getY()-height/2, getX()+width/2, getY()+height/2);
    }
}
