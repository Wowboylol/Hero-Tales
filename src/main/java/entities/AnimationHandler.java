/*  
 *  AnimationHandler.java
 *  
 *  Description: Handles animation for animate entities.
 *               Animations include walking and attacking.
 *
*/

package entities;

public class AnimationHandler 
{
    // Sprite animations
    private int walkAnimation = 1;
    private int attackAnimation = 1;
    
    // Animation counts
    private int walkAnimationCount = 0;
    private int attackAnimationCount = 0;

    // Display attack animation when attack is on cooldown
    public int animateAttack(int attackAnimationSpeed, boolean canAttack)
    {
        attackAnimationCount++;

        if(attackAnimationCount <= attackAnimationSpeed/2) attackAnimation = 1;
        else if(attackAnimationCount > attackAnimationSpeed/2 && attackAnimationCount <= attackAnimationSpeed) attackAnimation = 2;
        else
        {
            attackAnimation = 1;
            attackAnimationCount = 0;
        }
        if(canAttack) attackAnimationCount = 0;
        return attackAnimation;
    }

    // Walk animation
    public int animateWalk(int walkAnimationSpeed, boolean isMoving)
    {
        if(isMoving || (walkAnimation % 2) == 0)
        {
            if(walkAnimationCount++ > walkAnimationSpeed)
            {
                walkAnimation = (walkAnimation < 4) ? (walkAnimation + 1) : 1;
                walkAnimationCount = 0;
            }
        }
        return walkAnimation;
    }
}
