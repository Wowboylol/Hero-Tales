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
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends AnimateEntity
{
    // Configurations
    public static final Coordinate PLAYER_SPAWN_POSITION = new Coordinate(Simulator.TILE_SIZE*8, Simulator.TILE_SIZE*8);
    public static final int PLAYER_SCREEN_X = Simulator.SCREEN_WIDTH/2 - (Simulator.TILE_SIZE/2);
    public static final int PLAYER_SCREEN_Y = Simulator.SCREEN_HEIGHT/2 - (Simulator.TILE_SIZE/2);
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(9, 12, 30, 33);
    public final int BASE_MOVE_SPEED = 3;
    public final int ANIMATION_SPEED = 13;

    // Attributes
    private Simulator simulator;
    private Keyboard keyboard;
    private CollisionChecker collisionChecker;

    // Default constructor (starting coordinate based on defaults)
    public Player()
    {
        super(PLAYER_SPAWN_POSITION);
        getSprite();
        
        // Super class overriding
        this.setHitbox(HITBOX_CONFIGURATIONS);
        this.setMoveSpeed(BASE_MOVE_SPEED);
        this.setAnimationSpeed(ANIMATION_SPEED);
    }

    // External injector injects required dependencies
    public void inject(Simulator simulator, Keyboard keyboard, CollisionChecker collisionCheck)
    {
        this.simulator = simulator;
        this.keyboard = keyboard;
        this.collisionChecker = collisionCheck;
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
    // Stops movement when colliding with wall
    public void movePlayer()
    {
        boolean isColliding = collisionChecker.checkTileCollision(this);

        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            this.setDirection(Direction.LEFT);
            if(isColliding == false) this.setWorldCoordinateX(getWorldCoordinateX() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            this.setDirection(Direction.RIGHT);
            if(isColliding == false) this.setWorldCoordinateX(getWorldCoordinateX() + getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.UP) == true)
        {
            this.setDirection(Direction.UP);
            if(isColliding == false) this.setWorldCoordinateY(getWorldCoordinateY() - getMoveSpeed());
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            this.setDirection(Direction.DOWN);
            if(isColliding == false) this.setWorldCoordinateY(getWorldCoordinateY() + getMoveSpeed());
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
        graphics2D.drawImage(
            image, playerScreenPositionX(), playerScreenPositionY(), 
            Simulator.TILE_SIZE, Simulator.TILE_SIZE, null
        );
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

    // Helper function: Determines player horizontal screen position
    private int playerScreenPositionX()
    {
        int x = PLAYER_SCREEN_X;
        int rightOffset = Simulator.SCREEN_WIDTH - PLAYER_SCREEN_X;

        if(PLAYER_SCREEN_X > this.getWorldCoordinateX()) x = this.getWorldCoordinateX();
        if(rightOffset > simulator.getMapWidth() - this.getWorldCoordinateX())
        {
            x = Simulator.SCREEN_WIDTH - (2400 - this.getWorldCoordinateX());
        }
        return x;
    }

    // Helper function: Determines player vertical screen position
    private int playerScreenPositionY()
    {
        int y = PLAYER_SCREEN_Y;
        int bottomOffset = Simulator.SCREEN_HEIGHT - PLAYER_SCREEN_Y;

        if(PLAYER_SCREEN_Y > this.getWorldCoordinateY()) y = this.getWorldCoordinateY();
        if(bottomOffset > simulator.getMapHeight() - this.getWorldCoordinateY()) 
        {
            y = Simulator.SCREEN_HEIGHT - (2400 - this.getWorldCoordinateY());
        }
        return y;
    }
}
