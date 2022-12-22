/*  
 *  Projectile.java
 *  
 *  Description: This is the basic class for all projectiles.
 *
*/

package entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import main.Simulator;

public class Projectile extends Entity implements Updateable
{
    // Attributes
    private BufferedImage sprite;
    private Coordinate playerPosition;
    private int mapPixelWidth = Simulator.getInstance().mapHandler.getCurrentMapWidth();
    private int mapPixelHeight = Simulator.getInstance().mapHandler.getCurrentMapHeight();
    private int damage;
    private int projectileVelocity;
    private double xVelocity;
    private double yVelocity;
    private int lifetime;

    // Default constructor
    public Projectile(Coordinate spawnPosition, Coordinate playerPosition, BufferedImage sprite, int damage, int speed, int angle, int lifetime)
    {
        super(spawnPosition);
        this.playerPosition = playerPosition;
        this.sprite = sprite;
        this.damage = damage;
        this.projectileVelocity = speed;
        this.xVelocity = Math.cos(Math.toRadians(angle)) * this.projectileVelocity;
        this.yVelocity = Math.sin(Math.toRadians(angle)) * this.projectileVelocity;
        this.lifetime = lifetime;
    }

    // Getters
    public int getDamage() { return this.damage; }

    public void update()
    {
        this.setWorldCoordinateX((int)(this.getWorldCoordinateX() + xVelocity));
        this.setWorldCoordinateY((int)(this.getWorldCoordinateY() + yVelocity));
        lifetime--;
    }

    public void draw(Graphics2D graphics2D)
    {
        if(isProjectileOnScreen()) graphics2D.drawImage(sprite, getScreenX()-(Simulator.TILE_SIZE/2), getScreenY()-(Simulator.TILE_SIZE/2), null);
    }

    public boolean isDestroyed()
    {
        return lifetime <= 0;
    }

    // Get the x position of the projectile on the screen
    public int getScreenX()
    {
        if(Player.PLAYER_SCREEN_X > playerPosition.getX())
            return this.getWorldCoordinateX();

        if((Simulator.SCREEN_WIDTH - Player.PLAYER_SCREEN_X) > mapPixelWidth - playerPosition.getX())
            return Simulator.SCREEN_WIDTH - (mapPixelWidth - this.getWorldCoordinateX());

        return this.getWorldCoordinateX() - playerPosition.getX() + Player.PLAYER_SCREEN_X;
    }

    // Get the x position of the projectile on the screen
    public int getScreenY()
    {
        if(Player.PLAYER_SCREEN_Y > playerPosition.getY())
            return this.getWorldCoordinateY();

        if((Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y) > mapPixelHeight - playerPosition.getY())
            return Simulator.SCREEN_HEIGHT - (mapPixelHeight - this.getWorldCoordinateY());

        return this.getWorldCoordinateY() - playerPosition.getY() + Player.PLAYER_SCREEN_Y;
    }

    // Check if projectile is on screen
    private boolean isProjectileOnScreen()
    {
        if(this.getWorldCoordinateX() + Simulator.TILE_SIZE <= playerPosition.getX() - Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateX() - Simulator.TILE_SIZE >= playerPosition.getX() + Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateY() + Simulator.TILE_SIZE <= playerPosition.getY() - Player.PLAYER_SCREEN_Y) return false;
        if(this.getWorldCoordinateY() - Simulator.TILE_SIZE >= playerPosition.getY() + Player.PLAYER_SCREEN_Y) return false;
        return true;
    }
}
