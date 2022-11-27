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
    private static final int spawnX = 100;
    private static final int spawnY = 100;
    private final int baseMoveSpeed = 4;

    // Attributes
    private Simulator sim;
    private Keyboard keyboard;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator sim)
    {
        super(spawnX, spawnY);
        this.sim = sim;
        this.keyboard = sim.getKeyboard();
        init();
    }

    // Override and adjust superclass attributes
    private void init()
    {
        this.setMoveSpeed(baseMoveSpeed);
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
