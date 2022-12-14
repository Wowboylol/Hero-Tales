/*  
 *  AnimationHandler.java
 *  
 *  Description: Handles animation for animate entities.
 *               Animations include walking, attacking, and dying.
 *
*/

package entities;

public class AnimationHandler 
{
    // Sprite animations
    private int walkAnimation = 1;
    private int attackAnimation = 1;
    private int deathAnimation = 1;
    
    // Animation counts
    private int walkAnimationCount = 0;
    private int attackAnimationCount = 0;
    private int deathAnimationCount = 0;

    // Walk animation
    public int animateWalk(int walkAnimationSpeed, boolean isMoving)
    {
        // Change sprite animation if entity is moving or in the middle of moving animation
        if(isMoving == true || (walkAnimation % 2) == 0)
        {
            if(walkAnimationCount++ > walkAnimationSpeed)
            {
                walkAnimation = (walkAnimation < 4) ? (walkAnimation + 1) : 1;
                walkAnimationCount = 0;
            }
            resetAttackAnimation();
            resetDeathAnimation();
        }
        return walkAnimation;
    }

    // Attack animation
    public int animateAttack(int attackAnimationSpeed)
    {
        if(attackAnimationCount++ > attackAnimationSpeed)
        {
            attackAnimation = (attackAnimation < 4) ? (attackAnimation + 1) : 1;
            attackAnimationCount = 0;
        }
        resetMoveAnimation();
        resetDeathAnimation();
        return attackAnimation;
    }

    // Death animation
    public int animateDeath(int deathAnimationSpeed)
    {
        if(deathAnimationCount++ > deathAnimationSpeed)
        {
            deathAnimation = (deathAnimation < 4) ? (deathAnimation + 1) : 1;
            deathAnimationCount = 0;
        }
        resetMoveAnimation();
        resetAttackAnimation();
        return deathAnimation;
    }

    // Helper function: Reset specified animation state
    private void resetMoveAnimation()
    {
        walkAnimation = 1;
        walkAnimationCount = 0;
    }
    private void resetAttackAnimation()
    {
        attackAnimation = 1;
        attackAnimationCount = 0;
    }
    private void resetDeathAnimation()
    {
        deathAnimation = 1;
        deathAnimationCount = 0;
    }
}
