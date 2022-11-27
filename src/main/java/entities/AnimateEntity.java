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
    private int moveSpeed;
    private Direction direction;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int animationSpeed;

    // Default constructor
    public AnimateEntity(int setX, int setY)
    {
        super(setX, setY);
        this.moveSpeed = 3;
        this.direction = Direction.DOWN;
        this.animationSpeed = 15;
    }

    // Getters
    public int getMoveSpeed() { return this.moveSpeed; }
    public Direction getDirection() { return this.direction; }
    public int getSpriteCounter() { return this.spriteCounter; }
    public int getSpriteNum() { return this.spriteNum; }

    // Setters
    public void setMoveSpeed(int speed) { this.moveSpeed = speed; }
    public void setDirection(Direction d) { this.direction = d; }
    public void setAnimationSpeed(int speed) { this.animationSpeed = speed;}

    // Increments spriteCount which is used for sprite animation
    public void animateSprite() 
    {   
        if(spriteCounter++ > animationSpeed)
        {
            spriteNum = (spriteNum < 4) ? (spriteNum + 1) : 1;
            spriteCounter = 0;
        }
    }
}
