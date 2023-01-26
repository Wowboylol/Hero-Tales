/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherits Entity.
 *
*/

package entities;
import java.awt.image.BufferedImage;

import entities.enums.MoveDirection;
import entities.stats.Stats;
import graphics.effects.PopUpText;

public abstract class AnimateEntity extends Entity
{
    // Attributes
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    protected BufferedImage attackUp, attackDown, attackLeft, attackRight;
    private int spriteNum = 1;
    private boolean collisionOn = false;
    private int attackCooldown = 0;
    private PopUpText popUpText = new PopUpText();

    // States
    private AnimationHandler animationHandler;
    private boolean isMoving = false;
    private boolean isAttacking = false;
    private boolean isDead = false;

    // Defaults
    private Stats entityStats;
    private int moveAnimationSpeed = 0;
    private MoveDirection moveDirection = MoveDirection.DOWN;

    // Default constructor
    public AnimateEntity(Coordinate spawnPosition) 
    { 
        super(spawnPosition); 
        this.animationHandler = new AnimationHandler();
    }

    // Getters
    public Stats getStats() { return this.entityStats; }
    public MoveDirection getMoveDirection() { return this.moveDirection; }
    public int getSpriteNum() { return this.spriteNum; }
    public boolean getCollisionOn(boolean val) { return this.collisionOn; }
    public boolean getIsMoving() { return this.isMoving; }
    public boolean getIsAttacking() { return this.isAttacking; }
    public boolean getIsDead() { return this.isDead; }
    public PopUpText getPopUpText() { return this.popUpText; }

    // Setters
    public void setStats(Stats stats) { this.entityStats = stats; }
    public void setMoveDirection(MoveDirection d) { this.moveDirection = d; }
    public void setMoveAnimationSpeed(int speed) { this.moveAnimationSpeed = speed;}
    public void setCollisionOn(boolean val) { this.collisionOn = val; }
    public void setIsMoving(boolean val) { this.isMoving = val; }
    public void setIsAttacking(boolean val) { this.isAttacking = val; }
    public void setIsDead(boolean val) { this.isDead = val; }

    // Projectile firing
    public boolean canAttack() { return attackCooldown == 0; }
    public void decreaseAttackCooldown() { if(attackCooldown > 0) attackCooldown--;}
    public void startAttackCooldown() { attackCooldown = entityStats.calculateFramesPerAttack(); }

    // Sets spriteNum based on animation priority: attack > walk > idle
    public void animateSprite() 
    {   
        if(isAttacking || canAttack() == false) 
            spriteNum = animationHandler.animateAttack(entityStats.calculateFramesPerAttack(), canAttack());
        else 
            spriteNum = animationHandler.animateWalk(moveAnimationSpeed, isMoving);
    }
}
