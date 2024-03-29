/*  
 *  Entity.java
 *  
 *  Description: Abstract class, super class for players, enemies, objects, heroes, etc.
 *
*/

package entities;
import java.awt.Rectangle;

public abstract class Entity 
{
    // Attributes
    private Coordinate worldPosition;
    private Hitbox hitbox;
    
    // Default constructor
    public Entity(Coordinate spawnPosition)
    {
        this.worldPosition = spawnPosition;
        this.hitbox = new Hitbox();
    }

    // Getters
    public Coordinate getWorldCoordinate() { return this.worldPosition; }
    public int getWorldCoordinateX() { return this.worldPosition.getX(); }
    public int getWorldCoordinateY() { return this.worldPosition.getY(); }
    public Rectangle getHitbox() { return this.hitbox.getHitbox(); }
    public int getDefaultHitboxX() { return this.hitbox.getDefaultHitboxX(); }
    public int getDefaultHitboxY() { return this.hitbox.getDefaultHitboxY(); }
    public int getOriginPointX() { return this.hitbox.getOriginPointX(); }
    public int getOriginPointY() { return this.hitbox.getOriginPointY(); }

    // Setters
    public void setWorldCoordinate(Coordinate coordinate) { this.worldPosition = coordinate; }
    public void setWorldCoordinateX(int X) { this.worldPosition.setX(X); }
    public void setWorldCoordinateY(int Y) { this.worldPosition.setY(Y); }
    public void setHitbox(Rectangle hitbox) { this.hitbox.setHitbox(hitbox); }
}
