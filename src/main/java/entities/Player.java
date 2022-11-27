/*  
 *  Player.java
 *  
 *  Description: Controls player movement and stats.
 *
*/

package entities;
import main.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        this.getSprite();
        
        // Super class overriding
        this.setMoveSpeed(BASE_MOVE_SPEED);
    }

    // Update the class data via the Simulator
    public void update()
    {
        if(keyboard.getDirection(Direction.UP) == true)
        {
            setDirection(Direction.UP);
            this.setCoordinateY(getCoordinateY() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            setDirection(Direction.DOWN);
            this.setCoordinateY(getCoordinateY() + getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            setDirection(Direction.LEFT);
            this.setCoordinateX(getCoordinateX() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            setDirection(Direction.RIGHT);
            this.setCoordinateX(getCoordinateX() + getMoveSpeed());
        }
    }

    // Draw the class in window via the Simulator
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch(this.getDirection())
        {
            case UP:
                image = up1;
                break;
            case DOWN:
                image = down1;
                break;
            case LEFT:
                image = left1;
                break;
            case RIGHT:
                image = right1;
                break;
        }
        g2.drawImage(image, getCoordinateX(), getCoordinateY(), sim.getTileSize(), sim.getTileSize(), null);
    }

    // Load player sprites into BufferedImage, returns true if successful
    public boolean getSprite()
    {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_3.png"));
            return true;
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
