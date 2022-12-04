/*  
 *  MapStrategy.java
 *  
 *  Description: Interface for map strategies which build a tile map.
 *               The context is TileHandler.
 *
*/

package graphics.maps;
import java.awt.Graphics2D;

public interface MapStrategy 
{
    public void buildMap(Graphics2D graphics2D);
}
