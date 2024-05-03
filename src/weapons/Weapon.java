package weapons;

import api.Drawable;
import objects.Player;

public abstract class Weapon implements Drawable {
    protected Player owner;
    protected int attackPower;

    public Weapon(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
