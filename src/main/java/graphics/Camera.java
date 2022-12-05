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

    // Get player's world position X with an offset of screen position X in pixels
    public int getPlayerScreenX()
    {
        return player.getWorldCoordinateX() - player.PLAYER_SCREEN_X;
    }

    // Get player's world position Y with an offset of screen position Y in pixels
    public int getPlayerScreenY()
    {
        return player.getWorldCoordinateY() - player.PLAYER_SCREEN_Y;
    }
}
