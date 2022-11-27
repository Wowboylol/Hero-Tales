/*  
 *  Player.java
 *  
 *  Description: Controls player movement and stats.
 *
*/

package entities;
import main.*;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends AnimateEntity
{
    // Defaults
    private static final int SPAWN_X = 100;
    private static final int SPAWN_Y = 100;
    private final int BASE_MOVE_SPEED = 4;

    // Attributes
    private Simulator sim;
    private Keyboard keyboard;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator sim)
    {
        super(SPAWN_X, SPAWN_Y);
        this.sim = sim;
        this.keyboard = sim.getKeyboard();
        this.setMoveSpeed(BASE_MOVE_SPEED);
    }

    // Update the class data via the Simulator
    public void update()
    {
        if(keyboard.getDirection(Direction.UP) == true)
        {
            this.setCoordinateY(getCoordinateY() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            this.setCoordinateY(getCoordinateY() + getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            this.setCoordinateX(getCoordinateX() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            this.setCoordinateX(getCoordinateX() + getMoveSpeed());
        }
    }

    // Draw the class in window via the Simulator
    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.white);
        g2.fillRect(getCoordinateX(), getCoordinateY(), sim.getTileSize(), sim.getTileSize());
    }
}
