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
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    protected BufferedImage attackUp, attackDown, attackLeft, attackRight;
    private int actionCount = 0;
    private int spriteNum = 1;
    private boolean isMoving = false;
    private boolean collisionOn = false;

    // Defaults
    private int moveSpeed = 0;
    private int animationSpeed = 0;
    private MoveDirection moveDirection = MoveDirection.DOWN;

    // Default constructor
    public AnimateEntity(Coordinate spawnPosition) { super(spawnPosition); }

    // Getters
    public int getMoveSpeed() { return this.moveSpeed; }
    public MoveDirection getMoveDirection() { return this.moveDirection; }
    public int getActionCount() { return this.actionCount; }
    public int getSpriteNum() { return this.spriteNum; }
    public boolean getIsMoving() { return this.isMoving; }
    public boolean getCollisionOn(boolean val) { return this.collisionOn; }

    // Setters
    public void setMoveSpeed(int speed) { this.moveSpeed = speed; }
    public void setMoveDirection(MoveDirection d) { this.moveDirection = d; }
    public void setAnimationSpeed(int speed) { this.animationSpeed = speed;}
    public void setIsMoving(boolean val) { this.isMoving = val; }
    public void setCollisionOn(boolean val) { this.collisionOn = val; }

    // Sets spriteNum with precondition that 0 < spriteNum < 5, returns true if successful
    public boolean setSpriteNum(int spriteNum) 
    { 
        if(spriteNum > 0 && spriteNum < 5)
        {
            this.spriteNum = spriteNum;
            return true;
        }
        return false;
    }

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
