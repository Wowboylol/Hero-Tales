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
    private Camera camera;

    public MapHandler(Camera camera)
    {
        this.camera = camera;
    }

    // Setters
    public void setStrategy(MapStrategy strategy) 
    { 
        this.strategy = strategy;
    }

    // Getters
    public int getCurrentMapWidth() { return strategy.getMapWidth(); }
    public int getCurrentMapHeight() { return strategy.getMapHeight(); }
    public TileType getTileType(int col, int row) { return strategy.getTileType(col, row); }
    public boolean getTileCollision(int col, int row) { return (strategy.getTileType(col, row) == TileType.WALL) ? true : false; }

    // Initializes map by setting strategy
    public void initializeMap()
    {
        setStrategy(new StarterPlainsMap(camera));
    }

    // Executes strategy of building a specific type of map
    public void draw(Graphics2D graphics2D)
    {
        strategy.buildMap(graphics2D);
    }
}
