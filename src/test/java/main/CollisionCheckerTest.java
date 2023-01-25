/*  
 *  CollisionCheckerTest.java
 *  
 *  Description: Integration tests for the CollisionChecker class.
 *
*/

package main;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Rectangle;
import entities.Player;
import entities.projectiles.Projectile;
import entities.Coordinate;

public class CollisionCheckerTest 
{
    private CollisionChecker collisionChecker;
    private Player testPlayer;
    private Projectile testProjectile;

    // Create the test case
    public CollisionCheckerTest()
    {
        Simulator simulator = Simulator.getInstance();
        this.collisionChecker = simulator.collisionChecker;
        this.testPlayer = simulator.player;
        this.testProjectile = new Projectile(new Coordinate(0, 0), new Coordinate(0, 0), 0, 0, 0);
        this.testProjectile.setHitbox(new Rectangle(0, 0, 40, 40));
    }

    // Test that player is not colliding with anything by default
    @Test
    public void testPlayerCollisionDefault()
    {
        testPlayer.setWorldCoordinate(Player.PLAYER_SPAWN_POSITION);
        assertFalse(collisionChecker.checkTileWall(testPlayer));
    }

    // Test that player is in collision when at (0,0)
    @Test
    public void testPlayerCollisionOrigin()
    {
        testPlayer.setWorldCoordinate(new Coordinate(0, 0));
        assertTrue(collisionChecker.checkTileWall(testPlayer));
    }

    // Test that player is on path at default spawn position
    @Test
    public void testPlayerOnPathDefault()
    {
        testPlayer.setWorldCoordinate(Player.PLAYER_SPAWN_POSITION);
        assertTrue(collisionChecker.checkTilePath(testPlayer));
    }

    // Test that player is not on path at (370, 1850)
    @Test
    public void testPlayerOnPath()
    {
        testPlayer.setWorldCoordinate(new Coordinate(370, 1850));
        assertFalse(collisionChecker.checkTilePath(testPlayer));
    }

    // Test that projectile is not in collision at player default spawn
    @Test
    public void testProjectileCollisionDefault()
    {
        testProjectile.setWorldCoordinate(Player.PLAYER_SPAWN_POSITION);
        assertFalse(collisionChecker.checkTileWall(testProjectile, 180));
    }

    // Test that projectile is in collision at (48, 48) with 0 degree angle
    @Test
    public void testProjectileCollisionOrigin0()
    {
        testProjectile.setWorldCoordinate(new Coordinate(48, 48));
        assertTrue(collisionChecker.checkTileWall(testProjectile, 0));
    }

    // Test that projectile is in collision at (48, 48) with 90 degree angle
    @Test
    public void testProjectileCollisionOrigin90()
    {
        testProjectile.setWorldCoordinate(new Coordinate(48, 48));
        assertTrue(collisionChecker.checkTileWall(testProjectile, 90));
    }

    // Test that projectile is in collision at (48, 48) with 180 degree angle
    @Test
    public void testProjectileCollisionOrigin180()
    {
        testProjectile.setWorldCoordinate(new Coordinate(48, 48));
        assertTrue(collisionChecker.checkTileWall(testProjectile, 180));
    }

    // Test that projectile is in collision at (48, 48) with 270 degree angle
    @Test
    public void testProjectileCollisionOrigin270()
    {
        testProjectile.setWorldCoordinate(new Coordinate(48, 48));
        assertTrue(collisionChecker.checkTileWall(testProjectile, 270));
    }
}
