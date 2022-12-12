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
    private TileType type;

    // Default constructor
    public Tile(BufferedImage image)
    {
        this.image = image;
        this.type = TileType.DEFAULT;
    }

    // Parameterized constructor
    public Tile(BufferedImage image, TileType type)
    {
        this.image = image;
        this.type = type;
    }

    // Getters
    public BufferedImage getImage() { return this.image; }
    public TileType getType() { return this.type; }

    // Setters
    public void setImage(BufferedImage image) { this.image = image; }
    public void setType(TileType type) { this.type = type; }
}
