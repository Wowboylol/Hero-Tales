/*  
 *  SinusoidalProjectile.java
 *  
 *  Description: Extends Projectile class to allow for sinusoidal projectile motion.
 *
*/

package entities.projectiles;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import entities.*;
import entities.enums.EntityType;

public class SinusoidalProjectile extends Projectile
{
    // Attributes
    private double amplitude;
    private double frequency;

    // Default constructor
    public SinusoidalProjectile(Rectangle hitbox, Coordinate spawnPosition, Coordinate playerPosition, 
        BufferedImage sprite, int damage, int speed, int angle, int lifetime, 
        EntityType user, boolean canPierce, int amplitude, int frequency)
    {
        super(hitbox, spawnPosition, playerPosition, sprite, damage, speed, angle, lifetime, user, canPierce);
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public void update()
    {
        if(collisionChecker.checkTileWall(this, angle)) 
        {
            this.setLifetime(0);
            this.generateParticle();
        }
        if(collisionChecker.checkProjectileCollision(this)) this.setLifetime(0);
        calculateVelocity();
        this.setWorldCoordinateX((int)(this.getWorldCoordinateX() + this.getXVelocity()));
        this.setWorldCoordinateY((int)(this.getWorldCoordinateY() + this.getYVelocity()));
        this.setLifetime(this.getLifetime() - 1);
    }

    @Override
    public void calculateVelocity()
    {
        int timeAlive = 30 - this.getLifetime();
        double xVelocity = Math.cos(Math.toRadians(angle)) * this.getProjectileVelocity();
        double yVelocity = Math.sin(Math.toRadians(angle)) * this.getProjectileVelocity();
        double forwardX = -yVelocity;
        double forwardY = xVelocity;
        double upSpeed = Math.cos(timeAlive*(frequency/100))*(amplitude/10)*(frequency/100);
        this.setXVelocity(forwardX*upSpeed + xVelocity);
        this.setYVelocity(forwardY*upSpeed + yVelocity);
    }
}
