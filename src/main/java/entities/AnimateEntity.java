/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherits Entity.
 *
*/

package entities;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public abstract class AnimateEntity extends Entity
{
    // Attributes
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    private int actionCount = 0;
    private int spriteNum = 1;
    private boolean isMoving = false;

    // Defaults
    private Rectangle hitbox = new Rectangle();
    private int moveSpeed = 0;
    private int animationSpeed = 0;
    private Direction direction = Direction.DOWN;

    // Default constructor
    public AnimateEntity(Coordinate spawnPosition) { super(spawnPosition); }

    // Getters
    public int getMoveSpeed() { return this.moveSpeed; }
    public Direction getDirection() { return this.direction; }
    public int getActionCount() { return this.actionCount; }
    public int getSpriteNum() { return this.spriteNum; }
    public boolean getIsMoving() { return this.isMoving; }
    public Rectangle getHitbox() { return this.hitbox; }

    // Setters
    public void setMoveSpeed(int speed) { this.moveSpeed = speed; }
    public void setDirection(Direction d) { this.direction = d; }
    public void setAnimationSpeed(int speed) { this.animationSpeed = speed;}
    public void setIsMoving(boolean val) { this.isMoving = val; }
    public void setHitbox(Rectangle hitbox) { this.hitbox = hitbox; }

    // Increments actionCount which is used for sprite animation
    public void animateSprite() 
    {   
        // Change sprite animation if entity is moving or in the middle of moving animation
        if(isMoving == true || (spriteNum % 2) == 0)
        {
            if(actionCount++ > animationSpeed)
            {
                spriteNum = (spriteNum < 4) ? (spriteNum + 1) : 1;
                actionCount = 0;
            }
        }
    }
}
