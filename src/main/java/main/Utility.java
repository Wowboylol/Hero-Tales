/*  
 *  Utility.java
 *  
 *  Description: Contains helpful functions used game-wide.
 *
*/

package main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Font;
import javax.swing.JLabel;

public class Utility 
{
    public static final Font DEFAULT_FONT = new JLabel().getFont(); // FIXME: Remove after testing
    public static final Font DAMAGE_TEXT_FONT = new Font("Arial", Font.BOLD, 25); // FIXME: Remove after testing

    // Resizes image to specified width and height
    public static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2d = scaledImage.createGraphics();
        graphics2d.drawImage(image, 0, 0, width, height, null);
        graphics2d.dispose();
        return scaledImage;
    }

    // Rotates image by angle in degrees
    public static BufferedImage rotateImage(BufferedImage sprite, int angle)
    {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(angle), w/2, h/2);
        AffineTransformOp op = new AffineTransformOp(rotate, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(sprite, null);
    }
}
