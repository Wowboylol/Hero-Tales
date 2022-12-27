/*  
 *  MapHandlerTest.java
 *  
 *  Description: Unit tests for the MapHandler class.
 *
*/

package main;

import static org.junit.Assert.*;
import org.junit.Test;

import graphics.MapHandler;
import graphics.TileType;
import graphics.maps.*;

public class MapHandlerTest 
{
    private MapHandler mapHandler;
    private StarterPlainsMap testMap;

    // Create the test case
    public MapHandlerTest()
    {
        Simulator simulator = Simulator.getInstance();
        this.mapHandler = simulator.mapHandler;
        this.testMap = new StarterPlainsMap(simulator.camera);
    }

    // Test that map width of Starter Plains map is 2400 pixels
    @Test
    public void testStarterPlainsMapWidth()
    {
        mapHandler.setStrategy(testMap);
        assertEquals(2400, mapHandler.getCurrentMapWidth());
    }

    // Test that map height of Starter Plains map is 2400 pixels
    @Test
    public void testStarterPlainsMapHeight()
    {
        mapHandler.setStrategy(testMap);
        assertEquals(2400, mapHandler.getCurrentMapHeight());
    }

    // Test that the tile type of (0, 0) tile of Starter Plains map is WALL
    @Test
    public void testStarterPlainsMapTileType()
    {
        mapHandler.setStrategy(testMap);
        assertEquals(TileType.WALL, mapHandler.getTileType(0, 0));
    }
}
