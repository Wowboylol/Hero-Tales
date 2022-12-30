/*  
 *  Enemy.java
 *  
 *  Description: Abstract class that extends AnimateEntity, base class for all enemies.
 *
*/

package entities.enemies;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.ThreadLocalRandom;

import main.Simulator;
import main.Utility;
import entities.*;
import entities.enums.*;

public abstract class Enemy extends AnimateEntity
{
    // Attributes
    private Coordinate playerCoordinate;
    private int mapPixelWidth;
    private int mapPixelHeight;
    private int actionLockCounter;

    public Enemy(Coordinate spawnPosition, Simulator simulator)
    {
        super(spawnPosition);
        this.playerCoordinate = simulator.getPlayerCoordinate();
        this.mapPixelWidth = simulator.getMapWidth();
        this.mapPixelHeight = simulator.getMapHeight();
    }

    // Set this enemy next action
    public void setAction()
    {
        if(actionLockCounter++ >= 60)
        {
            int random = ThreadLocalRandom.current().nextInt(1, 16 + 1);

            if(random == 1) this.setMoveDirection(MoveDirection.UP);
            else if(random == 2) this.setMoveDirection(MoveDirection.DOWN);
            else if(random == 3) this.setMoveDirection(MoveDirection.LEFT);
            else if(random == 4) this.setMoveDirection(MoveDirection.RIGHT);
            else if(random == 5) this.setMoveDirection(MoveDirection.UPLEFT);
            else if(random == 6) this.setMoveDirection(MoveDirection.UPRIGHT);
            else if(random == 7) this.setMoveDirection(MoveDirection.DOWNLEFT);
            else if(random == 8) this.setMoveDirection(MoveDirection.DOWNRIGHT);
            else 
            {
                this.setIsMoving(false);
                actionLockCounter = 0;
                return;
            }
            this.setIsMoving(true);
            actionLockCounter = 0;
        }
    }

    // Move this enemy based on set action
    public void moveEnemy()
    {
        if(this.getIsMoving() == false) return;

        switch(this.getMoveDirection())
        {
            case UP:
                this.setWorldCoordinateY(this.getWorldCoordinateY() - this.getStats().getSpeed());
                break;
            case DOWN:
                this.setWorldCoordinateY(this.getWorldCoordinateY() + this.getStats().getSpeed());
                break;
            case LEFT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() - this.getStats().getSpeed());
                break;
            case RIGHT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() + this.getStats().getSpeed());
                break;
            case UPLEFT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() - this.getStats().getSpeed());
                this.setWorldCoordinateY(this.getWorldCoordinateY() - this.getStats().getSpeed());
                break;
            case UPRIGHT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() + this.getStats().getSpeed());
                this.setWorldCoordinateY(this.getWorldCoordinateY() - this.getStats().getSpeed());
                break;
            case DOWNLEFT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() - this.getStats().getSpeed());
                this.setWorldCoordinateY(this.getWorldCoordinateY() + this.getStats().getSpeed());
                break;
            case DOWNRIGHT:
                this.setWorldCoordinateX(this.getWorldCoordinateX() + this.getStats().getSpeed());
                this.setWorldCoordinateY(this.getWorldCoordinateY() + this.getStats().getSpeed());
                break;
        }
    }

    // Get the x position of this enemy on the screen
    public int getScreenX()
    {
        if(Player.PLAYER_SCREEN_X > playerCoordinate.getX())
            return this.getWorldCoordinateX();

        if((Simulator.SCREEN_WIDTH - Player.PLAYER_SCREEN_X) > mapPixelWidth - playerCoordinate.getX())
            return Simulator.SCREEN_WIDTH - (mapPixelWidth - this.getWorldCoordinateX());

        return this.getWorldCoordinateX() - playerCoordinate.getX() + Player.PLAYER_SCREEN_X;
    }

    // Get the y position of this enemy on the screen
    public int getScreenY()
    {
        if(Player.PLAYER_SCREEN_Y > playerCoordinate.getY())
            return this.getWorldCoordinateY();

        if((Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y) > mapPixelHeight - playerCoordinate.getY())
            return Simulator.SCREEN_HEIGHT - (mapPixelHeight - this.getWorldCoordinateY());

        return this.getWorldCoordinateY() - playerCoordinate.getY() + Player.PLAYER_SCREEN_Y;
    }

    // Check if this enemy is on screen
    public boolean onScreen()
    {
        if(this.getWorldCoordinateX() + Simulator.TILE_SIZE <= playerCoordinate.getX() - Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateX() - Simulator.TILE_SIZE >= playerCoordinate.getX() + Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateY() + Simulator.TILE_SIZE <= playerCoordinate.getY() - Player.PLAYER_SCREEN_Y) return false;
        if(this.getWorldCoordinateY() - Simulator.TILE_SIZE >= playerCoordinate.getY() + Player.PLAYER_SCREEN_Y) return false;
        return true;
    }

    // Sets up enemy sprites by resizing image from file
    public BufferedImage spriteSetup(String imageName, int width, int height)
    {
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemies/mobs/red_mushroom/" + imageName + ".png"));
            image = Utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
