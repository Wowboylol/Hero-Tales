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

import main.Simulator;
import main.Utility;
import entities.*;

public abstract class Enemy extends AnimateEntity
{
    // Attributes
    private Coordinate playerCoordinate;
    private int mapPixelWidth;
    private int mapPixelHeight;

    public Enemy(Coordinate spawnPosition, Simulator simulator)
    {
        super(spawnPosition);
        this.playerCoordinate = simulator.getPlayerCoordinate();
        this.mapPixelWidth = simulator.getMapWidth();
        this.mapPixelHeight = simulator.getMapHeight();
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
