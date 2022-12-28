/*  
 *  AnimationHandlerTest.java
 *  
 *  Description: Unit tests for the AnimationHandler class.
 *
*/

package entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class AnimationHandlerTest 
{
    private AnimationHandler animationHandler;

    // Create the test case
    public AnimationHandlerTest()
    {
        animationHandler = new AnimationHandler();
        animationHandler.setAttackAnimationCount(0);
        animationHandler.setWalkAnimationCount(0);
    }

    // Test that when attackAnimationCount is 3 and attackAnimationSpeed is 8, attackAnimation is 1
    @Test
    public void testAnimateAttack1()
    {
        animationHandler.setAttackAnimationCount(3);
        animationHandler.setAttackAnimation(1);
        assertEquals(1, animationHandler.animateAttack(8, true));
    }

    // Test that when attackAnimationCount is 4 and attackAnimationSpeed is 8, attackAnimation is 2
    @Test
    public void testAnimateAttack2()
    {
        animationHandler.setAttackAnimationCount(4);
        animationHandler.setAttackAnimation(1);
        assertEquals(2, animationHandler.animateAttack(8, true));
    }

    // Test that when attackAnimationCount is 8 and attackAnimationSpeed is 8, attackAnimation and attackAnimationCount are reset
    @Test
    public void testAnimateAttackReset()
    {
        animationHandler.setAttackAnimationCount(8);
        animationHandler.setAttackAnimation(2);
        assertEquals(1, animationHandler.animateAttack(8, true));
        assertEquals(0, animationHandler.getAttackAnimationCount());
    }

    // Test that when canAttack is false, attackAnimationCount is reset
    @Test
    public void testAnimateAttackCanAttack()
    {
        animationHandler.setAttackAnimationCount(1);
        animationHandler.setAttackAnimation(1);
        animationHandler.animateAttack(0, false);
        assertEquals(0, animationHandler.getAttackAnimationCount());
    }

    // Test that when isMoving is false and walkAnimation is odd, walkAnimationCount is not incremented
    @Test
    public void testAnimateWalkNotMovingOdd()
    {
        animationHandler.setWalkAnimationCount(0);
        animationHandler.setWalkAnimation(1);
        animationHandler.animateWalk(1, false);
        assertEquals(0, animationHandler.getWalkAnimationCount());
    }

    // Test that when isMoving is true and walkAnimation is odd, walkAnimationCount is incremented
    @Test
    public void testAnimateWalkMoving()
    {
        animationHandler.setWalkAnimationCount(0);
        animationHandler.setWalkAnimation(1);
        animationHandler.animateWalk(1, true);
        assertEquals(1, animationHandler.getWalkAnimationCount());
    }

    // Test that when isMoving is false and walkAnimation is even, walkAnimationCount is incremented
    @Test
    public void testAnimateWalkNotMovingEven()
    {
        animationHandler.setWalkAnimationCount(0);
        animationHandler.setWalkAnimation(2);
        animationHandler.animateWalk(1, false);
        assertEquals(1, animationHandler.getWalkAnimationCount());
    }

    // Test that when walkAnimationCount > walkAnimationSpeed and walkAnimation < 4, walkAnimation is incremented
    @Test
    public void testAnimateWalkIncrement()
    {
        animationHandler.setWalkAnimationCount(2);
        animationHandler.setWalkAnimation(1);
        assertEquals(2, animationHandler.animateWalk(1, true));
    }

    // Test that when walkAnimationCount <= walkAnimationSpeed, walkAnimation is not incremented
    @Test
    public void testAnimateWalkNotIncrement()
    {
        animationHandler.setWalkAnimationCount(1);
        animationHandler.setWalkAnimation(1);
        assertEquals(1, animationHandler.animateWalk(2, true));
    }

    // Test that when walkAnimationCount > walkAnimationSpeed and walkAnimation >= 4, walkAnimation is reset
    @Test
    public void testAnimateWalkReset()
    {
        animationHandler.setWalkAnimationCount(2);
        animationHandler.setWalkAnimation(4);
        assertEquals(1, animationHandler.animateWalk(1, true));
    }
}
