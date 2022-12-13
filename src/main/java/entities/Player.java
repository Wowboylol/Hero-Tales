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
    public static final Coordinate PLAYER_SPAWN_POSITION = new Coordinate(Simulator.TILE_SIZE*6, Simulator.TILE_SIZE*42);
    public static final int PLAYER_SCREEN_X = Simulator.SCREEN_WIDTH/2 - (Simulator.TILE_SIZE/2);
    public static final int PLAYER_SCREEN_Y = Simulator.SCREEN_HEIGHT/2 - (Simulator.TILE_SIZE/2);
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(9, 12, 30, 30);
    public final int BASE_MOVE_SPEED = 2;
    public final int ANIMATION_SPEED = 13;

    // Attributes
    private Simulator simulator;
    private Keyboard keyboard;
    private Mouse mouse;
    private CollisionChecker collisionChecker;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator simulator)
    {
        super(PLAYER_SPAWN_POSITION);
        this.simulator = simulator;
        getSprite();
        
        // Super class overriding
        this.setHitbox(HITBOX_CONFIGURATIONS);
        this.setMoveSpeed(BASE_MOVE_SPEED);
        this.setAnimationSpeed(ANIMATION_SPEED);
    }

    // External injector injects required dependencies
    public void inject(Keyboard keyboard, Mouse mouse, CollisionChecker collisionCheck)
    {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.collisionChecker = collisionCheck;
    }

    // Update the class data via the Simulator
    public void update()
    {
        isMovingSetter();
        changeDirection();
        this.animateSprite();
        
        if(this.getIsMoving() && !collisionChecker.checkTileWall(this))
        {
            movePlayer();
        }
    }

    // Set player direction based on which directional (WASD) key is pressed
    public void changeDirection()
    {
        if(keyboard.getDirection(MoveDirection.UPLEFT) == true) this.setDirection(MoveDirection.UPLEFT);
        else if(keyboard.getDirection(MoveDirection.UPRIGHT) == true) this.setDirection(MoveDirection.UPRIGHT);
        else if(keyboard.getDirection(MoveDirection.DOWNLEFT) == true) this.setDirection(MoveDirection.DOWNLEFT);
        else if(keyboard.getDirection(MoveDirection.DOWNRIGHT) == true) this.setDirection(MoveDirection.DOWNRIGHT);
        else if(keyboard.getDirection(MoveDirection.LEFT) == true) this.setDirection(MoveDirection.LEFT);
        else if(keyboard.getDirection(MoveDirection.RIGHT) == true) this.setDirection(MoveDirection.RIGHT);
        else if(keyboard.getDirection(MoveDirection.UP) == true) this.setDirection(MoveDirection.UP);
        else if(keyboard.getDirection(MoveDirection.DOWN) == true) this.setDirection(MoveDirection.DOWN);
    }

    // Move player based on which directional (WASD) key is pressed, increasing movespeed if player is on a path
    public void movePlayer()
    {
        int velocity = this.getMoveSpeed();
        if(collisionChecker.checkTilePath(this)) velocity++;

        if(keyboard.getDirection(MoveDirection.LEFT))
        {
            this.setWorldCoordinateX(getWorldCoordinateX() - velocity);
        }
        if(keyboard.getDirection(MoveDirection.RIGHT))
        {
            this.setWorldCoordinateX(getWorldCoordinateX() + velocity);
        }
        if(keyboard.getDirection(MoveDirection.UP))
        {
            this.setWorldCoordinateY(getWorldCoordinateY() - velocity);
        }
        if(keyboard.getDirection(MoveDirection.DOWN))
        {
            this.setWorldCoordinateY(getWorldCoordinateY() + velocity);
        }
    }

    // Draw the class in window via the Simulator
    public void draw(Graphics2D graphics2D)
    {
        BufferedImage image = null;
        int drawPositionX = playerScreenPositionX();
        int drawPositionY = playerScreenPositionY();

        if(mouse.getMousePressed())
        {
            switch(mouse.getAttackDirection(playerScreenPositionX()+getOriginPointX(), playerScreenPositionY()+getOriginPointY()))
            {
                case UP:
                    if(this.getSpriteNum()%2 != 0) drawPositionY = playerScreenPositionY() - Simulator.TILE_SIZE;
                    if(this.getSpriteNum() == 1) image = attackUp;
                    if(this.getSpriteNum() == 2) image = up1;
                    if(this.getSpriteNum() == 3) image = attackUp;
                    if(this.getSpriteNum() == 4) image = up1;
                    break;
                case DOWN:
                    if(this.getSpriteNum() == 1) image = attackDown;
                    if(this.getSpriteNum() == 2) image = down1;
                    if(this.getSpriteNum() == 3) image = attackDown;
                    if(this.getSpriteNum() == 4) image = down1;
                    break;
                case LEFT:
                    if(this.getSpriteNum()%2 != 0) drawPositionX = playerScreenPositionX() - Simulator.TILE_SIZE;
                    if(this.getSpriteNum() == 1) image = attackLeft;
                    if(this.getSpriteNum() == 2) image = left1;
                    if(this.getSpriteNum() == 3) image = attackLeft;
                    if(this.getSpriteNum() == 4) image = left1;
                    break;
                case RIGHT:
                    if(this.getSpriteNum() == 1) image = attackRight;
                    if(this.getSpriteNum() == 2) image = right1;
                    if(this.getSpriteNum() == 3) image = attackRight;
                    if(this.getSpriteNum() == 4) image = right1;
                    break;
            }
        }
        else
        {
            switch(this.getDirection())
            {
                case UPRIGHT:
                case UPLEFT:
                case UP:
                    if(this.getSpriteNum() == 1) image = up1;
                    if(this.getSpriteNum() == 2) image = up2;
                    if(this.getSpriteNum() == 3) image = up1;
                    if(this.getSpriteNum() == 4) image = up3;
                    break;
                case DOWNRIGHT:
                case DOWNLEFT:
                case DOWN:
                    if(this.getSpriteNum() == 1) image = down1;
                    if(this.getSpriteNum() == 2) image = down2;
                    if(this.getSpriteNum() == 3) image = down1;
                    if(this.getSpriteNum() == 4) image = down3;
                    break;
                case LEFT:
                    if(this.getSpriteNum() == 1) image = left1;
                    if(this.getSpriteNum() == 2) image = left2;
                    if(this.getSpriteNum() == 3) image = left1;
                    if(this.getSpriteNum() == 4) image = left3;
                    break;
                case RIGHT:
                    if(this.getSpriteNum() == 1) image = right1;
                    if(this.getSpriteNum() == 2) image = right2;
                    if(this.getSpriteNum() == 3) image = right1;
                    if(this.getSpriteNum() == 4) image = right3;
                    break;
            }
        }
        graphics2D.drawImage(image, drawPositionX, drawPositionY, null);
    }

    // Load player sprites into BufferedImage, returns true if successful
    public void getSprite()
    {
        up1 = spriteSetup("player_up_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up2 = spriteSetup("player_up_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up3 = spriteSetup("player_up_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down1 = spriteSetup("player_down_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down2 = spriteSetup("player_down_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down3 = spriteSetup("player_down_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left1 = spriteSetup("player_left_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left2 = spriteSetup("player_left_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left3 = spriteSetup("player_left_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right1 = spriteSetup("player_right_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right2 = spriteSetup("player_right_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right3 = spriteSetup("player_right_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackUp = spriteSetup("player_attack_up", Simulator.TILE_SIZE, Simulator.TILE_SIZE*2);
        attackDown = spriteSetup("player_attack_down", Simulator.TILE_SIZE, Simulator.TILE_SIZE*2);
        attackLeft = spriteSetup("player_attack_left", Simulator.TILE_SIZE*2, Simulator.TILE_SIZE);
        attackRight = spriteSetup("player_attack_right", Simulator.TILE_SIZE*2, Simulator.TILE_SIZE);
    }

    // Helper function: Sets up player sprites by resizing image from file
    private BufferedImage spriteSetup(String imageName, int width, int height)
    {
        Utility utility = new Utility();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    // Helper function: Determines whether player is moving or attacking in a given frame
    private void isMovingSetter() 
    {
        if(keyboard.isWASDPressed() || mouse.getMousePressed()) 
            this.setIsMoving(true);
        else 
            this.setIsMoving(false);
    }

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
