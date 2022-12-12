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
    private Coordinate originPoint;

    // Default constructor
    public Hitbox()
    {
        this.hitbox = new Rectangle();
    }

    // Getters
    public Rectangle getHitbox() { return this.hitbox; }
    public int getOriginPointX() { return this.originPoint.getX(); }
    public int getOriginPointY() { return this.originPoint.getY(); }

    // Setters
    public void setHitbox(Rectangle hitbox) 
    { 
        this.hitbox = hitbox; 
        this.originPoint = new Coordinate(hitbox.x + hitbox.width/2, hitbox.y + hitbox.height/2);
    }
}
