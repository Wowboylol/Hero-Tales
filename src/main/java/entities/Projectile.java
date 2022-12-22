/*  
 *  Projectile.java
 *  
 *  Description: This is the basic class for all projectiles (MAY BE REFACTORED IN THE FUTURE).
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
    private int damage;
    private int projectileVelocity;
    private double xVelocity;
    private double yVelocity;
    private int lifetime;

    // Default constructor
    public Projectile(Coordinate spawnPosition, BufferedImage sprite, int damage, int speed, int angle, int lifetime)
    {
        super(spawnPosition);
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
        graphics2D.drawImage(
            sprite, 
            this.getWorldCoordinateX() - Simulator.TILE_SIZE/2, 
            this.getWorldCoordinateY() - Simulator.TILE_SIZE/2, 
            null
        );
    }

    public boolean isDestroyed()
    {
        return lifetime <= 0;
    }
}
