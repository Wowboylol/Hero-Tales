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
    private Mouse mouse;

    // Create the test case
    public PlayerTest()
    {
        Simulator simulator = Simulator.getInstance();
        this.player = simulator.player;
        this.keyboard = simulator.keyboard;
        this.mouse = simulator.mouse;
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
        assertEquals(player.getMoveDirection(), MoveDirection.UP);
    }

    // Test setting player direction when S (down) is pressed
    @Test
    public void testSetDirectionDown()
    {
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.changeDirection();
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(player.getMoveDirection(), MoveDirection.DOWN);
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
        assertEquals(player.getMoveDirection(), MoveDirection.UPLEFT);
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
        assertEquals(player.getMoveDirection(), MoveDirection.DOWNRIGHT);
    }

    // Move player in down when not on path and test that player position is correct
    @Test
    public void testMovePlayerDownNotOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.movePlayer(false);
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(player.getWorldCoordinate().getY(), player.getStats().getSpeed());
    }

    // Move player in down when on path and test that player position is correct
    @Test
    public void testMovePlayerDownOnPath()
    {
        player.setWorldCoordinate(new Coordinate(0, 0));
        keyboard.addPressedKey(KeyEvent.VK_S);
        player.movePlayer(true);
        keyboard.removePressedKey(KeyEvent.VK_S);
        assertEquals(player.getWorldCoordinate().getY(), player.getStats().getSpeed()+1);
    }
}
