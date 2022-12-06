/*  
 *  TileHandler.java
 *  
 *  Description: Handles all tile related operations.
 *               Context for MapStrategy.
 *
*/

package graphics;
import java.awt.Graphics2D;
import main.Simulator;
import graphics.maps.MapStrategy;
import graphics.maps.StarterPlainsMap;

public class TileHandler 
{
    // Attributes
    private Camera camera;
    private MapStrategy strategy;

    public TileHandler(Simulator simulator)
    {
        this.camera = new Camera(simulator);
        this.strategy = new StarterPlainsMap(camera); // FIXME: remove later
    }

    // Setters
    public void setStrategy(MapStrategy strategy) { this.strategy = strategy; }

    // Executes strategy of building a specific type of map
    public void draw(Graphics2D graphics2D)
    {
        strategy.buildMap(graphics2D);
    }
}
