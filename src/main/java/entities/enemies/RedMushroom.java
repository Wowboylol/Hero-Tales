
/*  
 *  RedMushroom.java
 *  
 *  Description: Placeholder.
 *
*/

package entities.enemies;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import main.Simulator;
import entities.*;
import entities.stats.EnemyStats;
import entities.enums.MoveDirection;

public class RedMushroom extends Enemy implements Updateable
{
    // Testing attributes
    public static final Coordinate TESTING_SPAWN_POSITION = new Coordinate(Simulator.TILE_SIZE*5, Simulator.TILE_SIZE*41);

    // Configurations
    public final int MOVE_ANIMATION_SPEED = 12;

    // Default constructor
    public RedMushroom(Simulator simulator)
    {
        super(TESTING_SPAWN_POSITION, simulator);
        loadSprite();

        // Super class overloading
        this.setStats(new EnemyStats(100, 0, 0, 5, 3));
        this.setMoveAnimationSpeed(MOVE_ANIMATION_SPEED);

        this.setIsMoving(true); // FIXME: Remove this line after testing
        this.setMoveDirection(MoveDirection.RIGHT); // FIXME: Remove this line after testing
    }

    @Override
    public void update()
    {
        if(this.onScreen()) this.animateSprite();
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        if(this.onScreen()) drawMovingSprite(graphics2D);
    }

    @Override
    public boolean isDestroyed() { return false; }

    // Set and draw image for moving sprite based on spriteNum
    public void drawMovingSprite(Graphics2D graphics2D)
    {
        BufferedImage image = null;

        switch(this.getMoveDirection())
        {
            case DOWNRIGHT:
            case DOWNLEFT:
            case DOWN:
                if(this.getSpriteNum() == 1) image = down1;
                if(this.getSpriteNum() == 2) image = down2;
                if(this.getSpriteNum() == 3) image = down3;
                if(this.getSpriteNum() == 4) image = down2;
                break;
            case UPRIGHT:
            case UPLEFT:
            case UP:
                if(this.getSpriteNum() == 1) image = up1;
                if(this.getSpriteNum() == 2) image = up2;
                if(this.getSpriteNum() == 3) image = up3;
                if(this.getSpriteNum() == 4) image = up2;
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
        graphics2D.drawImage(image, this.getScreenX(), this.getScreenY(), null);
    }

    // Load player sprites into BufferedImage
    public void loadSprite()
    {
        down1 = this.spriteSetup("walk_down_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down2 = this.spriteSetup("walk_down_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down3 = this.spriteSetup("walk_down_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up1 = this.spriteSetup("walk_up_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up2 = this.spriteSetup("walk_up_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up3 = this.spriteSetup("walk_up_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left1 = this.spriteSetup("walk_left_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left2 = this.spriteSetup("walk_left_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left3 = this.spriteSetup("walk_left_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right1 = this.spriteSetup("walk_right_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right2 = this.spriteSetup("walk_right_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right3 = this.spriteSetup("walk_right_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }
}
