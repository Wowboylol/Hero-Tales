/*  
 *  Hitbox.java
 *  
 *  Description: Contains hitbox size and origin point
 *
*/

package entities;
import java.awt.Rectangle;

public class Hitbox 
{
    // Attributes
    private Rectangle hitbox;
    private int defaultHitboxX;
    private int defaultHitboxY;
    private Coordinate originPoint;

    // Default constructor
    public Hitbox()
    {
        this.hitbox = new Rectangle();
    }

    // Parameterized constructor
    public Hitbox(Rectangle hitbox)
    {
        this.hitbox = hitbox;
        this.defaultHitboxX = hitbox.x;
        this.defaultHitboxY = hitbox.y;
        this.originPoint = new Coordinate(hitbox.x + hitbox.width/2, hitbox.y + hitbox.height/2);
    }

    // Getters
    public Rectangle getHitbox() { return this.hitbox; }
    public int getDefaultHitboxX() { return this.defaultHitboxX; }
    public int getDefaultHitboxY() { return this.defaultHitboxY; }
    public int getOriginPointX() { return this.originPoint.getX(); }
    public int getOriginPointY() { return this.originPoint.getY(); }

    // Setters
    public void setDefaultHitboxX(int defaultHitboxX) { this.defaultHitboxX = defaultHitboxX; }
    public void setDefaultHitboxY(int defaultHitboxY) { this.defaultHitboxY = defaultHitboxY; }
    public void setHitbox(Rectangle hitbox) 
    { 
        this.hitbox = hitbox; 
        this.defaultHitboxX = hitbox.x;
        this.defaultHitboxY = hitbox.y;
        this.originPoint = new Coordinate(hitbox.x + hitbox.width/2, hitbox.y + hitbox.height/2);
    }
}
