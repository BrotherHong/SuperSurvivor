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
    private final int imageWidth = 100;
    private final int imageHeight = 100;

    private int degree = 0;

    public RotatedSword(int attackPower) {
        super(attackPower);
        Image originImage;
        try {
            originImage = ImageIO.read(new FileInputStream("resources/sword_900.png"));
            image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(originImage, 0, 0, imageWidth, imageHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        // draw
        float offsetX = (float) (radius * Math.cos(Math.toRadians(degree)));
        float offsetY = (float) (radius * Math.sin(Math.toRadians(degree)));

        int swordX = imageWidth/2;
        int swordY = imageHeight/2;

        int drawX = (int) (owner.getX() + offsetX - swordX);
        int drawY = (int) (owner.getY() + offsetY - swordY);

        AffineTransform transform = AffineTransform.getTranslateInstance(drawX, drawY);
        transform.rotate(Math.toRadians(degree), swordX, swordY);

        ((Graphics2D)g).drawImage(image, transform, null);

        // rotate (update)
        degree += degreePerFrame;
        degree %= 360;
    }
}
