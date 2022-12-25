/*  
 *  PlayerTest.java
 *  
 *  Description: Unit tests for the Player class.
 *
*/

package main;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.event.KeyEvent;
import entities.Player;
import entities.enums.MoveDirection;
import entities.Coordinate;

public class PlayerTest 
{
    // Attributes
    private Player player;
    private Keyboard keyboard;
    private int mapWidth;
    private int mapHeight;

    // Create the test case
    public PlayerTest()
    {
        Simulator simulator = Simulator.getInstance();
        this.player = simulator.player;
        this.keyboard = simulator.keyboard;
        this.mapWidth = simulator.getMapWidth();
        this.mapHeight = simulator.getMapHeight();
    }

    // Test that player isDestroyed() always returns false
    @Test
    public void testIsDestroyed() { assertFalse(player.isDestroyed()); }

    // Test that player stats exists
    @Test
    public void testStatsExist() { assertNotNull(player.getStats()); }
    
    // Test that player hitbox exists
    @Test
    public void testHitboxExist() { assertNotNull(player.getHitbox()); }

    // Test that player has a weapon equipped
    @Test
    public void testWeaponExist() { assertNotNull(player.getWeapon()); }

    // Test setting player direction when W (up) is pressed
    @Test
    public void testSetDirectionUp()
    {
        keyboard.addPressedKey(KeyEvent.VK_W);
        player.changeDirection();
        keyboard.removePressedKey(KeyEvent.VK_W);
        assertEquals(MoveDirection.UP, player.getMoveDirection());
    }

    // Test setting player direction when S (down) is pressed
    @Test
    public void testSetDirectionDown()
    {
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.changeDirection();
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(MoveDirection.DOWN, player.getMoveDirection());
    }

    // Test setting player direction when W (up) and A (left) are pressed
    @Test
    public void testSetDirectionUpLeft()
    {
        keyboard.addPressedKey(KeyEvent.VK_W);
        keyboard.addPressedKey(KeyEvent.VK_A);
        player.changeDirection();
        keyboard.removePressedKey(KeyEvent.VK_W);
        keyboard.removePressedKey(KeyEvent.VK_A);
        assertEquals(MoveDirection.UPLEFT, player.getMoveDirection());
    }

    // Test setting player direction when S (down) and D (right) are pressed
    @Test
    public void testSetDirectionDownRight()
    {
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.addPressedKey(KeyEvent.VK_D);
        player.changeDirection();
        keyboard.removePressedKey(KeyEvent.VK_S);
        keyboard.removePressedKey(KeyEvent.VK_D);
        assertEquals(MoveDirection.DOWNRIGHT, player.getMoveDirection());
    }

    // Move player down when not on path and test that player position is correct
    @Test
    public void testMovePlayerDownNotOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.movePlayer(false);
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(player.getStats().getSpeed(), player.getWorldCoordinate().getY());
    }

    // Move player down when on path and test that player position is correct
    @Test
    public void testMovePlayerDownOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.movePlayer(true);
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(player.getStats().getSpeed()+1, player.getWorldCoordinate().getY());
    }

    // Move player down and right when not on path and test that player position is correct
    @Test
    public void testMovePlayerDownRightNotOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.addPressedKey(KeyEvent.VK_D);
        player.movePlayer(false);
        keyboard.removePressedKey(KeyEvent.VK_S);
        keyboard.removePressedKey(KeyEvent.VK_D);
        assertEquals(player.getStats().getSpeed(), player.getWorldCoordinate().getY());
        assertEquals(player.getStats().getSpeed(), player.getWorldCoordinate().getX());
    }

    // Move player down and right when on path and test that player position is correct
    @Test
    public void testMovePlayerDownRightOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.addPressedKey(KeyEvent.VK_D);
        player.movePlayer(true);
        keyboard.removePressedKey(KeyEvent.VK_S);
        keyboard.removePressedKey(KeyEvent.VK_D);
        assertEquals(player.getStats().getSpeed()+1, player.getWorldCoordinate().getY());
        assertEquals(player.getStats().getSpeed()+1, player.getWorldCoordinate().getX());
    }

    // Assert that playerScreenPositionX() returns correct value at coordinate (0, 0)
    @Test
    public void testPlayerScreenXAtOrigin()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        assertEquals(0, player.playerScreenPositionX());
    }

    // Assert that playerScreenPositionX() returns correct value at coordinate (500, 500)
    @Test
    public void testPlayerScreenXAt500()
    {
        player.setWorldCoordinate(new Coordinate(500, 500));
        assertEquals(Player.PLAYER_SCREEN_X, player.playerScreenPositionX());
    }

    // Assert that playerScreenPositionX() returns correct value at coordinate map edge
    @Test
    public void testPlayerScreenXAtMapEdge()
    {
        player.setWorldCoordinate(new Coordinate(mapWidth, 0));
        assertEquals(Simulator.SCREEN_WIDTH, player.playerScreenPositionX());
    }

    // Assert that playerScreenPositionY() returns correct value at coordinate (0, 0)
    @Test
    public void testPlayerScreenYAtOrigin()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        assertEquals(0, player.playerScreenPositionY());
    }

    // Assert that playerScreenPositionY() returns correct value at coordinate (500, 500)
    @Test
    public void testPlayerScreenYAt500()
    {
        player.setWorldCoordinate(new Coordinate(500, 500));
        assertEquals(Player.PLAYER_SCREEN_Y, player.playerScreenPositionY());
    }

    // Assert that playerScreenPositionY() returns correct value at coordinate map edge
    @Test
    public void testPlayerScreenYAtMapEdge()
    {
        player.setWorldCoordinate(new Coordinate(0, mapHeight));
        assertEquals(Simulator.SCREEN_HEIGHT, player.playerScreenPositionY());
    }
}
