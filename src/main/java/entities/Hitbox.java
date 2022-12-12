/*  
 *  Player.java
 *  
 *  Description: Controls player movement and stats.
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
    public int getOriginPointX() { return this.originPoint.get_X(); }
    public int getOriginPointY() { return this.originPoint.get_Y(); }

    // Setters
    public void setHitbox(Rectangle hitbox) 
    { 
        this.hitbox = hitbox; 
        this.originPoint = new Coordinate(hitbox.x + hitbox.width/2, hitbox.y + hitbox.height/2);
    }
}