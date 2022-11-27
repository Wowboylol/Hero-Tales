/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherits Entity.
 *
*/

package entities;
import java.awt.image.BufferedImage;

public abstract class AnimateEntity extends Entity
{
    // Attributes
    private int moveSpeed;
    private Direction direction;
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;

    // Default constructor
    public AnimateEntity(int setX, int setY)
    {
        super(setX, setY);
        this.moveSpeed = 3;
        this.direction = Direction.DOWN;
    }

    // Getters
    public int getMoveSpeed() { return this.moveSpeed; }
    public Direction getDirection() { return this.direction; }

    // Setters
    public void setMoveSpeed(int speed) { this.moveSpeed = speed; }
    public void setDirection(Direction d) { this.direction = d; }
}
