/*  
 *  TileHandler.java
 *  
 *  Description: Handles all tile related operations.
 *               Context for MapStrategy.
 *
*/

package graphics;
import java.awt.Graphics2D;
import graphics.maps.MapStrategy;
import graphics.maps.StarterPlainsMap;

public class TileHandler 
{
    // Attributes
    private MapStrategy strategy;

    public TileHandler()
    {
        this.strategy = new StarterPlainsMap(); // remove later
    }

    // Setters
    public void setStrategy(MapStrategy strategy) { this.strategy = strategy; }

    // Executes strategy of building a specific type of map
    public void draw(Graphics2D graphics2D)
    {
        strategy.buildMap(graphics2D);
    }
}
