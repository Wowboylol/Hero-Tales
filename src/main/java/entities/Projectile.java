/*  
 *  Projectile.java
 *  
 *  Description: This is the basic class for all projectiles.
 *
*/

package entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import main.Simulator;
import main.CollisionChecker;
import main.Utility;
import entities.enums.EntityType;

public class Projectile extends Entity implements Updateable
{
    // Attributes
    private CollisionChecker collisionChecker = Simulator.getInstance().collisionChecker;
    private BufferedImage sprite;
    private EntityType user;
    private int angle;
    private Coordinate playerPosition;
    private int mapPixelWidth = Simulator.getInstance().mapHandler.getCurrentMapWidth();
    private int mapPixelHeight = Simulator.getInstance().mapHandler.getCurrentMapHeight();

    // Stats
    private int damage;
    private int projectileVelocity;
    private double xVelocity;
    private double yVelocity;
    private int lifetime;

    // Default constructor
    public Projectile(Rectangle hitbox, Coordinate spawnPosition, Coordinate playerPosition, 
        BufferedImage sprite, int damage, int speed, 
        int angle, int lifetime, EntityType user)
    {
        super(spawnPosition);
        this.sprite = Utility.rotateImage(sprite, (angle/8)*8);
        this.angle = angle;
        hitbox.x = hitbox.x-(Simulator.TILE_SIZE/2);
        hitbox.y = hitbox.y-(Simulator.TILE_SIZE/2);
        this.setHitbox(hitbox);
        this.user = user;
        this.playerPosition = playerPosition;
        this.damage = damage;
        this.projectileVelocity = speed;
        this.lifetime = lifetime;
        calculateVelocity();
    }

    // Parameterized constructor (for testing purposes)
    public Projectile(Coordinate spawnPosition, Coordinate playerPosition, int speed, int angle, int lifetime)
    {
        super(spawnPosition);
        this.angle = angle;
        this.playerPosition = playerPosition;
        this.projectileVelocity = speed;
        this.lifetime = lifetime;
        calculateVelocity();
    }

    // Getters
    public int getDamage() { return this.damage; }
    public EntityType getUser() { return this.user; }
    public double getXVelocity() { return this.xVelocity; }
    public double getYVelocity() { return this.yVelocity; }

    // Setters
    public void setSpeed(int speed) { this.projectileVelocity = speed; calculateVelocity(); }
    public void setAngle(int angle) { this.angle = angle; calculateVelocity(); }
    public void setLifetime(int lifetime) { this.lifetime = lifetime; }
    public void setPosition(Coordinate position) { this.setWorldCoordinate(position); }

    public void update()
    {
        if(collisionChecker.checkTileWall(this, angle)) lifetime = 0;
        if(collisionChecker.checkProjectileCollision(this)) lifetime = 0;
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

    // Draw debug information
    public void debugConsole(Graphics2D graphics2D)
    {
        Rectangle hitbox = this.getHitbox();
        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(
            this.getDefaultHitboxX() + getScreenX(), 
            this.getDefaultHitboxY() + getScreenY(), 
            hitbox.width, 
            hitbox.height
        );
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

    // Get the y position of the projectile on the screen
    public int getScreenY()
    {
        if(Player.PLAYER_SCREEN_Y > playerPosition.getY())
            return this.getWorldCoordinateY();

        if((Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y) > mapPixelHeight - playerPosition.getY())
            return Simulator.SCREEN_HEIGHT - (mapPixelHeight - this.getWorldCoordinateY());

        return this.getWorldCoordinateY() - playerPosition.getY() + Player.PLAYER_SCREEN_Y;
    }

    // Calculate xVelocity and yVelocity based on angle and speed
    public void calculateVelocity()
    {
        this.xVelocity = Math.cos(Math.toRadians(angle)) * this.projectileVelocity;
        this.yVelocity = Math.sin(Math.toRadians(angle)) * this.projectileVelocity;
    }

    // Helper function: Check if projectile is on screen
    public boolean isProjectileOnScreen()
    {
        if(this.getWorldCoordinateX() + Simulator.TILE_SIZE <= playerPosition.getX() - Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateX() - Simulator.TILE_SIZE >= playerPosition.getX() + Player.PLAYER_SCREEN_X) return false;
        if(this.getWorldCoordinateY() + Simulator.TILE_SIZE <= playerPosition.getY() - Player.PLAYER_SCREEN_Y) return false;
        if(this.getWorldCoordinateY() - Simulator.TILE_SIZE >= playerPosition.getY() + Player.PLAYER_SCREEN_Y) return false;
        return true;
    }

    // Testing only: Set player position, replacing playerPosition local reference
    public void setPlayerPosition(Coordinate playerPosition) { this.playerPosition = playerPosition; }
}
