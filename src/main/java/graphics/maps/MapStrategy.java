/*  
 *  MapStrategy.java
 *  
 *  Description: Interface for map strategies which build a tile map.
 *               The context is MapHandler.
 *
*/

package graphics.maps;
import java.awt.Graphics2D;

public interface MapStrategy 
{
    // Builds and renders map with Camera
    public void buildMap(Graphics2D graphics2D);

    // Returns map width
    public int getMapWidth();

    // Returns map height
    public int getMapHeight();
}
