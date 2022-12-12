/*  
 *  Utility.java
 *  
 *  Description: Contains helpful functions used game-wide.
 *
*/

package main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility 
{
    // Resizes image to specified width and height
    public BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2d = scaledImage.createGraphics();
        graphics2d.drawImage(image, 0, 0, width, height, null);
        graphics2d.dispose();
        return scaledImage;
    }
}
