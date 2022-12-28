/*  
 *  Camera.java
 *  
 *  Description: Unit tests for the Camera class.
 *
*/

package graphics;

import static org.junit.Assert.*;
import org.junit.Test;

import main.Simulator;

public class CameraTest 
{
    private Camera camera;
    private int mapPixelWidth;
    private int mapPixelHeight;

    // Create the test case
    public CameraTest()
    {
        Simulator simulator = Simulator.getInstance();
        this.camera = simulator.camera;
        this.mapPixelWidth = simulator.mapHandler.getCurrentMapWidth();
        this.mapPixelHeight = simulator.mapHandler.getCurrentMapHeight();

        // Set dependencies
        simulator.player.setWorldCoordinateX(500);
        simulator.player.setWorldCoordinateY(500);
    }

    // Test that getTileScreenPositionX() of tile (0,0) when player at (500, 500) returns -44
    @Test
    public void testGetTileScreenPositionXDefault()
    {
        assertEquals(-44, camera.getTileScreenPositionX(0, mapPixelWidth));
    }

    // Test that getTileScreenPositionX() of tile (10,10) when player at (500, 500) returns 436
    @Test
    public void testGetTileScreenPositionX10()
    {
        assertEquals(436, camera.getTileScreenPositionX(10, mapPixelWidth));
    }

    // Test that getTileScreenPositionX() of tile (20,20) when player at (500, 500) returns 916
    @Test
    public void testGetTileScreenPositionX20()
    {
        assertEquals(916, camera.getTileScreenPositionX(20, mapPixelWidth));
    }

    // Test that getTileScreenPositionY() of tile (0,0) when player at (500, 500) returns -164
    @Test
    public void testGetTileScreenPositionYDefault()
    {
        assertEquals(-164, camera.getTileScreenPositionY(0, mapPixelHeight));
    }

    // Test that getTileScreenPositionY() of tile (10,10) when player at (500, 500) returns 316
    @Test
    public void testGetTileScreenPositionY10()
    {
        assertEquals(316, camera.getTileScreenPositionY(10, mapPixelHeight));
    }

    // Test that getTileScreenPositionY() of tile (20,20) when player at (500, 500) returns 796
    @Test
    public void testGetTileScreenPositionY20()
    {
        assertEquals(796, camera.getTileScreenPositionY(20, mapPixelHeight));
    }

    // Check that tile (0,0) is not screen
    @Test
    public void testTileNotOnScreenX()
    {
        assertFalse(camera.isTileOnScreen(0, 0));
    }

    // Check that tile (10,10) is on screen
    @Test
    public void testTileOnScreenX()
    {
        assertTrue(camera.isTileOnScreen(10, 10));
    }

    // Check that tile (20,20) is not on screen
    @Test
    public void testTileNotOnScreenY()
    {
        assertFalse(camera.isTileOnScreen(20, 20));
    }

    // Check that screen/camera is not at edge map
    @Test
    public void testCameraNotAtEdge()
    {
        assertFalse(camera.isScreenAtMapEdge(mapPixelWidth, mapPixelHeight));
    }
}
