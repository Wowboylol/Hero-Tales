/*  
 *  Projectile.java
 *  
 *  Description: This is the basic class for all projectiles.
 *
*/

package entities.projectiles;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

import main.Simulator;
import main.CollisionChecker;
import main.Utility;
import entities.*;
import entities.Updateable;
import entities.enums.EntityType;
import graphics.effects.Particle;

public class Projectile extends Entity implements Updateable
{
    // Attributes
    private AtomicBoolean debugConsole = Simulator.getInstance().keyboard.getDebugConsoleReference();
    protected CollisionChecker collisionChecker = Simulator.getInstance().collisionChecker;
    private BufferedImage sprite;
    private EntityType user;
    protected int angle;
    protected Coordinate playerPosition;
    private int mapPixelWidth = Simulator.getInstance().mapHandler.getCurrentMapWidth();
    private int mapPixelHeight = Simulator.getInstance().mapHandler.getCurrentMapHeight();
    private Color spriteColor;

    // Stats
    private int damage;
    private int projectileVelocity;
    private double xVelocity;
    private double yVelocity;
    private int lifetime;
    private boolean pierce;

    // Default constructor
    public Projectile(Rectangle hitbox, Coordinate spawnPosition, Coordinate playerPosition, 
        BufferedImage sprite, int damage, int speed, 
        int angle, int lifetime, EntityType user, boolean canPierce)
    {
        super(spawnPosition);
        this.spriteColor = new Color(sprite.getRGB(27, 24));
        this.sprite = Utility.rotateImage(sprite, (angle/8) << 3);
        this.angle = angle;
        hitbox.x = hitbox.x-(Simulator.TILE_SIZE >> 1);
        hitbox.y = hitbox.y-(Simulator.TILE_SIZE >> 1);
        this.setHitbox(hitbox);
        this.user = user;
        this.playerPosition = playerPosition;
        this.damage = damage;
        this.projectileVelocity = speed;
        this.lifetime = lifetime;
        this.pierce = canPierce;
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
    public double getProjectileVelocity() { return this.projectileVelocity; }
    public double getXVelocity() { return this.xVelocity; }
    public double getYVelocity() { return this.yVelocity; }
    public int getLifetime() { return this.lifetime; }
    public boolean getPierce() { return this.pierce; }

    // Setters
    public void setSpeed(int speed) { this.projectileVelocity = speed; calculateVelocity(); }
    public void setAngle(int angle) { this.angle = angle; calculateVelocity(); }
    public void setLifetime(int lifetime) { this.lifetime = lifetime; }
    public void setPosition(Coordinate position) { this.setWorldCoordinate(position); }
    public void setXVelocity(double xVelocity) { this.xVelocity = xVelocity; }
    public void setYVelocity(double yVelocity) { this.yVelocity = yVelocity; }

    @Override
    public void update()
    {
        if(collisionChecker.checkTileWall(this, angle)) 
        {
            lifetime = 0;
            generateParticle();
        }
        if(collisionChecker.checkProjectileCollision(this)) lifetime = 0;
        this.setWorldCoordinateX((int)(this.getWorldCoordinateX() + xVelocity));
        this.setWorldCoordinateY((int)(this.getWorldCoordinateY() + yVelocity));
        lifetime--;
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        if(isProjectileOnScreen()) 
        {
            graphics2D.drawImage(sprite, getScreenX()-(Simulator.TILE_SIZE/2), getScreenY()-(Simulator.TILE_SIZE/2), null);
            if(debugConsole.get()) debugConsole(graphics2D);
        }
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

    // Get the y position of the projectile on the screen
    public int getScreenY()
    {
        if(Player.PLAYER_SCREEN_Y > playerPosition.getY())
            return this.getWorldCoordinateY();

        if((Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y) > mapPixelHeight - playerPosition.getY())
            return Simulator.SCREEN_HEIGHT - (mapPixelHeight - this.getWorldCoordinateY());

        return this.getWorldCoordinateY() - playerPosition.getY() + Player.PLAYER_SCREEN_Y;
    }

    // Animate particle animation
    public void generateParticle()
    {
        Simulator simulator = Simulator.getInstance();
        Coordinate origin = new Coordinate(
            this.getWorldCoordinateX() + this.getOriginPointX(), 
            this.getWorldCoordinateY() + this.getOriginPointY()
        );
        simulator.particleHandler.addParticle(new Particle(simulator, origin, spriteColor, -1, -1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, spriteColor, 1, -1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, spriteColor, -1, 1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, spriteColor, 1, 1)); 
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

    // Draw debug information
    public void debugConsole(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(
            this.getDefaultHitboxX() + getScreenX(), 
            this.getDefaultHitboxY() + getScreenY(), 
            this.getHitbox().width, 
            this.getHitbox().height
        );
    }

    // Testing only: Set player position, replacing playerPosition local reference
    public void setPlayerPosition(Coordinate playerPosition) { this.playerPosition = playerPosition; }
}
