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
    private Simulator simulator;
    private MapStrategy strategy;

    public TileHandler(Simulator sim)
    {
        this.simulator = sim;
        this.strategy = new StarterPlainsMap(); // remove later
    }

    // Setters
    public void setStrategy(MapStrategy strategy) { this.strategy = strategy; }

    // Executes strategy of building a specific type of map
    public void draw(Graphics2D g2)
    {
        strategy.buildMap(g2);
    }
}
