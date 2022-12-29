/*  
 *  ProjectileTest.java
 *  
 *  Description: Unit tests for the Projectile class.
 *
*/

package entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProjectileTest 
{
    private Projectile projectile;

    // Create the test case
    public ProjectileTest()
    {
        projectile = new Projectile(new Coordinate(0, 0), new Coordinate(0, 0), 0, 0, 0);
    }

    // Test that getScreenX() returns 0 when player coordinate is (Player.PLAYER_SCREEN_X,0)
    @Test
    public void testGetScreenXPlayerScreenX()
    {
        projectile.setPlayerPosition(new Coordinate(Player.PLAYER_SCREEN_X,0));
        projectile.setPosition(new Coordinate(0, 0));
        assertEquals(0, projectile.getScreenX());
    }

    // Test that getScreenX() returns -1 when player coordinate is (Player.PLAYER_SCREEN_X+1,0)
    @Test
    public void testGetScreenXPlayerScreenXOutpoint()
    {
        projectile.setPlayerPosition(new Coordinate(Player.PLAYER_SCREEN_X+1,0));
        projectile.setPosition(new Coordinate(0, 0));
        assertEquals(-1, projectile.getScreenX());
    }

    // Test that getScreenX() returns 560 when player coordinate is (1900,0)
    // AND projectile coordinate is (2000, 0)
    @Test
    public void testGetScreenXPlayerScreenXOffset()
    {
        projectile.setPlayerPosition(new Coordinate(1900,0));
        projectile.setPosition(new Coordinate(2000, 0));
        assertEquals(560, projectile.getScreenX());
    }

    // Test that getScreenY() returns 0 when player coordinate is (0,Player.PLAYER_SCREEN_Y)
    @Test
    public void testGetScreenYPlayerScreenY()
    {
        projectile.setPlayerPosition(new Coordinate(0,Player.PLAYER_SCREEN_Y));
        projectile.setPosition(new Coordinate(0, 0));
        assertEquals(0, projectile.getScreenY());
    }

    // Test that getScreenY() returns -1 when player coordinate is (0,Player.PLAYER_SCREEN_Y+1)
    @Test
    public void testGetScreenYPlayerScreenYOutpoint()
    {
        projectile.setPlayerPosition(new Coordinate(0,Player.PLAYER_SCREEN_Y+1));
        projectile.setPosition(new Coordinate(0, 0));
        assertEquals(-1, projectile.getScreenY());
    }

    // Test that getScreenY() returns 560 when player coordinate is (0,2020)
    // AND projectile coordinate is (0, 2200)
    @Test
    public void testGetScreenYPlayerScreenYOffset()
    {
        projectile.setPlayerPosition(new Coordinate(0,2020));
        projectile.setPosition(new Coordinate(0, 2000));
        assertEquals(320, projectile.getScreenY());
    }

    // Test that projectile is on screen when player coordinate is (0,0) and projectile coordinate is (0,0)
    @Test
    public void testProjectileIsOnScreen()
    {
        projectile.setPlayerPosition(new Coordinate(0,0));
        projectile.setPosition(new Coordinate(0, 0));
        assertTrue(projectile.isProjectileOnScreen());
    }

    // Test that projectile is not on screen when player coordinate is (0,0) and projectile coordinate is (0,1000)
    @Test
    public void testProjectileIsNotOnScreen()
    {
        projectile.setPlayerPosition(new Coordinate(0,0));
        projectile.setPosition(new Coordinate(0, 1000));
        assertFalse(projectile.isProjectileOnScreen());
    }
}
