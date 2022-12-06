/*  
 *  Camera.java
 *  
 *  Description: Keeps track of screen rendering based on player world position.
 *
*/

package graphics;
import main.Simulator;
import entities.Player;

public class Camera 
{
    // Attributes
    private Player player;

    // Default constructor
    public Camera(Simulator simulator)
    {
        this.player = simulator.getPlayer();
    }

    // Get tile screen horizontal position based on tile's map columm number
    public int getTileScreenPositionX(int mapTileX)
    {
        int mapCoordinateX = mapTileX * Simulator.TILE_SIZE;
        return mapCoordinateX - player.getWorldCoordinateX() + player.PLAYER_SCREEN_X;
    }

    // Get tile screen vertical position based on tile's map row number
    public int getTileScreenPositionY(int mapTileY)
    {
        int mapCoordinateY = mapTileY * Simulator.TILE_SIZE;
        return mapCoordinateY - player.getWorldCoordinateY() + player.PLAYER_SCREEN_Y;
    }

    // Check if a map tile is in player's screen camera
    public boolean isTileOnScreen(int mapTileX, int mapTileY)
    {
        int tileSize = Simulator.TILE_SIZE;
        int mapCoordinateX = mapTileX * tileSize;
        int mapCoordinateY = mapTileY * tileSize;

        if(mapCoordinateX + tileSize <= player.getWorldCoordinateX() - player.PLAYER_SCREEN_X)
            return false;
        if(mapCoordinateX - tileSize >= player.getWorldCoordinateX() + player.PLAYER_SCREEN_X)
            return false;
        if(mapCoordinateY + tileSize <= player.getWorldCoordinateY() - player.PLAYER_SCREEN_Y)
            return false;
        if(mapCoordinateY - tileSize >= player.getWorldCoordinateY() + player.PLAYER_SCREEN_Y)
            return false;
        return true;
    }
}
