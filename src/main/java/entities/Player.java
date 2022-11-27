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
    private final int BASE_MOVE_SPEED = 3;
    private final int ANIMATION_SPEED = 15;

    // Attributes
    private Simulator sim;
    private Keyboard keyboard;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator sim)
    {
        super(SPAWN_X, SPAWN_Y);
        this.sim = sim;
        this.keyboard = sim.getKeyboard();
        getSprite();
        
        // Super class overriding
        this.setMoveSpeed(BASE_MOVE_SPEED);
        this.setAnimationSpeed(ANIMATION_SPEED);
    }

    // Update the class data via the Simulator
    public void update()
    {
        if(keyboard.isWASDPressed() == true)
        {
            movePlayer();
            this.animateSprite();
        }
    }

    // Move player based on which directional (WASD) key is pressed
    public void movePlayer()
    {
        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            this.setDirection(Direction.LEFT);
            this.setCoordinateX(getCoordinateX() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            this.setDirection(Direction.RIGHT);
            this.setCoordinateX(getCoordinateX() + getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.UP) == true)
        {
            this.setDirection(Direction.UP);
            this.setCoordinateY(getCoordinateY() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            this.setDirection(Direction.DOWN);
            this.setCoordinateY(getCoordinateY() + getMoveSpeed());
        }
    }

    // Draw the class in window via the Simulator
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch(this.getDirection())
        {
            case UP:
                if(this.getSpriteNum() == 1)
                    image = up1;
                if(this.getSpriteNum() == 2)
                    image = up2;
                if(this.getSpriteNum() == 3)
                    image = up1;
                if(this.getSpriteNum() == 4)
                    image = up3;
                break;
            case DOWN:
                if(this.getSpriteNum() == 1)
                    image = down1;
                if(this.getSpriteNum() == 2)
                    image = down2;
                if(this.getSpriteNum() == 3)
                    image = down1;
                if(this.getSpriteNum() == 4)
                    image = down3;
                break;
            case LEFT:
                if(this.getSpriteNum() == 1)
                    image = left1;
                if(this.getSpriteNum() == 2)
                    image = left2;
                if(this.getSpriteNum() == 3)
                    image = left1;
                if(this.getSpriteNum() == 4)
                    image = left3;
                break;
            case RIGHT:
                if(this.getSpriteNum() == 1)
                    image = right1;
                if(this.getSpriteNum() == 2)
                    image = right2;
                if(this.getSpriteNum() == 3)
                    image = right1;
                if(this.getSpriteNum() == 4)
                    image = right3;
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
