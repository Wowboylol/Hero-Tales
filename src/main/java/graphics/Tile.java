/*  
 *  Tile.java
 *  
 *  Description: Single tile object of size TILE_SIZE.
 *
*/

package graphics;
import java.awt.image.BufferedImage;

public class Tile 
{
    // Attributes
    private BufferedImage image;
    private boolean canCollide;

    // Default constructor
    public Tile(BufferedImage image)
    {
        this.image = image;
        this.canCollide = false;
    }

    // Parameterized constructor
    public Tile(BufferedImage image, boolean canCollide)
    {
        this.image = image;
        this.canCollide = canCollide;
    }

    // Getters
    public BufferedImage getImage() { return this.image; }
    public boolean getCanCollide() { return this.canCollide; }

    // Setters
    public void setImage(BufferedImage image) { this.image = image; }
    public void setCanCollide(boolean val) { this.canCollide = val; }
}
