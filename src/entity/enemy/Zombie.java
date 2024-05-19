package entity.enemy;

import java.awt.*;

public class Zombie extends Enemy {

    public Zombie(float x, float y) {
        super(x, y, 25, 25);
        this.speed = 75;
        this.health = 100;
        this.attackPower = 10;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int)(x - width/2), (int)(y - height/2), width, height);
    }


}
