package weapons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class RotatedSword extends Weapon {

    // draw
    private final float radius = 100;
    private final float degreePerFrame = 3;
    private BufferedImage image = null;

    private int degree = 0;

    public RotatedSword(int attackPower) {
        super(100, 100, attackPower);
        loadImage();
    }

    private void loadImage() {
        Image originImage;
        try {
            originImage = ImageIO.read(new FileInputStream("resources/sword_900.png"));
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(originImage, 0, 0, width, height, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        // draw
        float offsetX = (float) (radius * Math.cos(Math.toRadians(degree)));
        float offsetY = (float) (radius * Math.sin(Math.toRadians(degree)));

        x = owner.getX() + offsetX;
        y = owner.getY() + offsetY;

        int drawX = (int) (x - width/2f);
        int drawY = (int) (y - height/2f);

        AffineTransform transform = AffineTransform.getTranslateInstance(drawX, drawY);
        transform.rotate(Math.toRadians(degree), width/2f, height/2f);

        ((Graphics2D)g).drawImage(image, transform, null);
        g.drawRect(drawX, drawY, width, height);

        // rotate (update)
        degree += degreePerFrame;
        degree %= 360;
    }
}