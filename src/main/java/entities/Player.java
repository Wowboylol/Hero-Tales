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
    public static final Coordinate PLAYER_SPAWN_POSITION = new Coordinate(Simulator.TILE_SIZE*8, Simulator.TILE_SIZE*8);
    public final int PLAYER_SCREEN_X = Simulator.SCREEN_WIDTH/2 - (Simulator.TILE_SIZE/2);
    public final int PLAYER_SCREEN_Y = Simulator.SCREEN_HEIGHT/2 - (Simulator.TILE_SIZE/2);
    public final int BASE_MOVE_SPEED = 3;
    public final int ANIMATION_SPEED = 13;

    // Attributes
    private Keyboard keyboard;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator simulator)
    {
        super(PLAYER_SPAWN_POSITION);
        this.keyboard = simulator.getKeyboard();
        getSprite();
        
        // Super class overriding
        this.setMoveSpeed(BASE_MOVE_SPEED);
        this.setAnimationSpeed(ANIMATION_SPEED);
    }

    // Update the class data via the Simulator
    public void update()
    {
        isMovingSetter();
        this.animateSprite();
        
        if(this.getIsMoving() == true)
        {
            movePlayer();
        }
    }

    // Move player based on which directional (WASD) key is pressed
    public void movePlayer()
    {
        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            this.setDirection(Direction.LEFT);
            this.setWorldCoordinateX(getWorldCoordinateX() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            this.setDirection(Direction.RIGHT);
            this.setWorldCoordinateX(getWorldCoordinateX() + getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.UP) == true)
        {
            this.setDirection(Direction.UP);
            this.setWorldCoordinateY(getWorldCoordinateY() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            this.setDirection(Direction.DOWN);
            this.setWorldCoordinateY(getWorldCoordinateY() + getMoveSpeed());
        }
    }

    // Draw the class in window via the Simulator
    public void draw(Graphics2D graphics2D)
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
        graphics2D.drawImage(image, PLAYER_SCREEN_X, PLAYER_SCREEN_Y, Simulator.TILE_SIZE, Simulator.TILE_SIZE, null);
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

    // Helper function: Determines whether player is moving in given frame
    private void isMovingSetter() { this.setIsMoving(keyboard.isWASDPressed()); }
}
