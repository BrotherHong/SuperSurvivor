package weapons;

import api.Drawable;
import entity.Entity;
import entity.Player;

public abstract class Weapon extends Entity implements Drawable {
    protected Player owner;
    protected int attackPower;

    public Weapon(int width, int height, int attackPower) {
        super(0, 0, width, height);
        this.attackPower = attackPower;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
