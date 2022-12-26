/*  
 *  KeyboardTest.java
 *  
 *  Description: Unit tests for the Keyboard class.
 *
*/

package main;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Component;
import java.awt.event.KeyEvent;
import entities.enums.MoveDirection;

public class KeyboardTest 
{
    private Keyboard keyboard;

    // Create the test case
    public KeyboardTest()
    {
        this.keyboard = new Keyboard();
    }

    // Test getDirection() when W (up) is pressed
    @Test
    public void testGetDirectionUp()
    {
        keyboard.addPressedKey(KeyEvent.VK_W);
        assertTrue(keyboard.getDirection(MoveDirection.UP));
        keyboard.removePressedKey(KeyEvent.VK_W);
    }

    // Test getDirection() when S (down) and D (right) is pressed
    @Test
    public void testGetDirectionDownRight()
    {
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.addPressedKey(KeyEvent.VK_D);
        assertTrue(keyboard.getDirection(MoveDirection.DOWNRIGHT));
        keyboard.removePressedKey(KeyEvent.VK_S);
        keyboard.removePressedKey(KeyEvent.VK_D);
    }

    // Check if isWASDPressed() returns true when W (up) is pressed
    @Test
    public void testIsWASDPressedUp()
    {
        keyboard.addPressedKey(KeyEvent.VK_W);
        assertTrue(keyboard.isWASDPressed());
        keyboard.removePressedKey(KeyEvent.VK_W);
    }

    // Check if isWASDPressed() returns true when S (down) and A (left) is pressed
    @Test
    public void testIsWASDPressedDownLeft()
    {
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.addPressedKey(KeyEvent.VK_A);
        assertTrue(keyboard.isWASDPressed());
        keyboard.removePressedKey(KeyEvent.VK_S);
        keyboard.removePressedKey(KeyEvent.VK_A);
    }

    // Check if isWASDPressed() returns false when no WASD keys are pressed
    @Test
    public void testIsWASDPressedNone()
    {
        assertFalse(keyboard.isWASDPressed());
    }

    // Check if debug mode is off by default
    @Test
    public void testDebugModeOff()
    {
        assertFalse(keyboard.getDebugConsole());
    }

    // Check if debug mode is toggled when = is pressed
    @Test
    public void testToggleDebugMode()
    {
        KeyEvent test = new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_EQUALS, '=');
        keyboard.keyPressed(test);
        assertTrue(keyboard.getDebugConsole());
        keyboard.keyReleased(test);
        keyboard.keyPressed(test);
        keyboard.keyReleased(test);
    }

    // Test if A (left) is removed from pressed keys when released
    @Test
    public void testRemovePressedKeyOnReleaseSingle()
    {
        keyboard.addPressedKey(KeyEvent.VK_A);
        keyboard.keyReleased(new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        assertFalse(keyboard.getDirection(MoveDirection.LEFT));
    }

    // Test if W (up) and S (down) are removed from pressed keys when released
    @Test
    public void testRemovePressedKeysOnReleaseDouble()
    {
        keyboard.addPressedKey(KeyEvent.VK_W);
        keyboard.addPressedKey(KeyEvent.VK_S);
        keyboard.keyReleased(new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_W, 'W'));
        keyboard.keyReleased(new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        assertFalse(keyboard.getDirection(MoveDirection.UP));
        assertFalse(keyboard.getDirection(MoveDirection.DOWN));
    }

    // Test if A (left) and D (right) are added when keys are pressed
    @Test
    public void testAddPressedKeysOnPressDouble()
    {
        keyboard.keyPressed(new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        keyboard.keyPressed(new KeyEvent(new Component(){}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        assertTrue(keyboard.getDirection(MoveDirection.LEFT));
        assertTrue(keyboard.getDirection(MoveDirection.RIGHT));
        keyboard.removePressedKey(KeyEvent.VK_A);
        keyboard.removePressedKey(KeyEvent.VK_D);
    }
}
