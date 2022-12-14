/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherits Entity.
 *
*/

package entities;
import java.awt.image.BufferedImage;
import entities.stats.Stats;

public abstract class AnimateEntity extends Entity
{
    // Attributes
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    protected BufferedImage attackUp, attackDown, attackLeft, attackRight;
    private int actionCount = 0;
    private int fireRateCount = 0;
    private int spriteNum = 1;
    private boolean isMoving = false;
    private boolean collisionOn = false;

    // Defaults
    private Stats entityStats;
    private int moveAnimationSpeed = 0;
    private MoveDirection moveDirection = MoveDirection.DOWN;

    // Default constructor
    public AnimateEntity(Coordinate spawnPosition) { super(spawnPosition); }

    // Getters
    public Stats getStats() { return this.entityStats; }
    public MoveDirection getMoveDirection() { return this.moveDirection; }
    public int getActionCount() { return this.actionCount; }
    public int getSpriteNum() { return this.spriteNum; }
    public boolean getIsMoving() { return this.isMoving; }
    public boolean getCollisionOn(boolean val) { return this.collisionOn; }

    // Setters
    public void setStats(Stats stats) { this.entityStats = stats; }
    public void setMoveDirection(MoveDirection d) { this.moveDirection = d; }
    public void setMoveAnimationSpeed(int speed) { this.moveAnimationSpeed = speed;}
    public void setIsMoving(boolean val) { this.isMoving = val; }
    public void setCollisionOn(boolean val) { this.collisionOn = val; }

    // Fire rate methods
    public boolean isFiring() { return (fireRateCount > 0) ? true : false; }
    public void decreaseFireRateCount() { if(fireRateCount > 0) fireRateCount--; }
    public void resetFireRateCount() { this.fireRateCount = entityStats.calculateFramesPerAttack(); }
    public int getFireRateCount() { return this.fireRateCount; }

    // Increments actionCount which is used for sprite animation
    public void animateSprite() 
    {   
        // Change sprite animation if entity is moving or in the middle of moving animation
        if(isMoving == true || (spriteNum % 2) == 0)
        {
            int animationSpeed = isFiring() ? entityStats.calculateFramesPerAttack() : moveAnimationSpeed;
            if(actionCount++ > animationSpeed)
            {
                spriteNum = (spriteNum < 4) ? (spriteNum + 1) : 1;
                actionCount = 0;
            }
        }
    }
}
