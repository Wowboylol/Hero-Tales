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
    public final int RIGHT_OFFSET;
    public final int BOTTOM_OFFSET;
    private Player player;

    // Default constructor
    public Camera()
    {
        this.RIGHT_OFFSET = Simulator.SCREEN_WIDTH - Player.PLAYER_SCREEN_X;
        this.BOTTOM_OFFSET = Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y;
    }

    // External injector injects required dependencies
    public void inject(Player player)
    {
        this.player = player;
    }

    // Get tile screen horizontal position based on tile's map columm number
    // Stops camera if it reaches the map right left borders.
    public int getTileScreenPositionX(int mapTileX, int mapPixelWidth)
    {
        int mapCoordinateX = mapTileX * Simulator.TILE_SIZE;
        int tileScreenPositionX = mapCoordinateX - player.getWorldCoordinateX() + Player.PLAYER_SCREEN_X;

        if(Player.PLAYER_SCREEN_X > player.getWorldCoordinateX())
            tileScreenPositionX = mapCoordinateX;

        if(RIGHT_OFFSET > mapPixelWidth - player.getWorldCoordinateX())
            tileScreenPositionX = Simulator.SCREEN_WIDTH - (mapPixelWidth - mapCoordinateX);

        return tileScreenPositionX;
    }

    // Get tile screen vertical position based on tile's map row number.
    // Stops camera if it reaches the map top bottom borders.
    public int getTileScreenPositionY(int mapTileY, int mapPixelHeight)
    {
        int mapCoordinateY = mapTileY * Simulator.TILE_SIZE;
        int tileScreenPositionY = mapCoordinateY - player.getWorldCoordinateY() + Player.PLAYER_SCREEN_Y;

        if(Player.PLAYER_SCREEN_Y > player.getWorldCoordinateY())
            tileScreenPositionY = mapCoordinateY;
        
        if(BOTTOM_OFFSET > mapPixelHeight - player.getWorldCoordinateY())
            tileScreenPositionY = Simulator.SCREEN_HEIGHT - (mapPixelHeight - mapCoordinateY);

        return tileScreenPositionY;
    }

    // Check if a map tile is in player's screen camera.
    public boolean isTileOnScreen(int mapTileX, int mapTileY)
    {
        int tileSize = Simulator.TILE_SIZE;
        int mapCoordinateX = mapTileX * tileSize;
        int mapCoordinateY = mapTileY * tileSize;

        if(mapCoordinateX + tileSize <= player.getWorldCoordinateX() - Player.PLAYER_SCREEN_X) return false;
        if(mapCoordinateX - tileSize >= player.getWorldCoordinateX() + Player.PLAYER_SCREEN_X) return false;
        if(mapCoordinateY + tileSize <= player.getWorldCoordinateY() - Player.PLAYER_SCREEN_Y) return false;
        if(mapCoordinateY - tileSize >= player.getWorldCoordinateY() + Player.PLAYER_SCREEN_Y) return false;
        return true;
    }

    // Checks if the screen is at the edge of the map
    // Alters playerScreenPosition when screen is at edge of map
    public boolean isScreenAtMapEdge(int mapWidth, int mapHeight)
    {
        if(Player.PLAYER_SCREEN_X > player.getWorldCoordinateX()) return true;
        if(Player.PLAYER_SCREEN_Y > player.getWorldCoordinateY()) return true;
        if(RIGHT_OFFSET > mapWidth - player.getWorldCoordinateX()) return true;
        if(BOTTOM_OFFSET > mapHeight - player.getWorldCoordinateY()) return true;
        return false;
    }
}
