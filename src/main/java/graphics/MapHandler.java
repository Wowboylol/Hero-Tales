/*  
 *  TileHandler.java
 *  
 *  Description: Handles map generation with tile rendering with map building strategies.
 *               Context for MapStrategy.
 *
*/

package graphics;
import java.awt.Graphics2D;
import graphics.maps.MapStrategy;
import graphics.maps.StarterPlainsMap;

public class MapHandler 
{
    // Attributes
    private MapStrategy strategy;
    private int mapWidth;
    private int mapHeight;

    public MapHandler(Camera camera)
    {
        setStrategy(new StarterPlainsMap(camera)); // FIXME: remove later
    }

    // Setters
    public void setStrategy(MapStrategy strategy) 
    { 
        this.mapWidth = strategy.getMapWidth();
        this.mapHeight = strategy.getMapHeight();
        this.strategy = strategy;
    }

    // Getters
    public int getCurrentMapWidth() { return this.mapWidth; }
    public int getCurrentMapHeight() { return this.mapHeight; }

    // Executes strategy of building a specific type of map
    public void draw(Graphics2D graphics2D)
    {
        strategy.buildMap(graphics2D);
    }
}
