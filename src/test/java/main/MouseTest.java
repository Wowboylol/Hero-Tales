/*  
 *  MouseTest.java
 *  
 *  Description: Unit tests for the Mouse class.
 *
*/

package main;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Component;
import java.awt.event.MouseEvent;
import entities.Coordinate;
import entities.enums.AttackDirection;

public class MouseTest 
{
    private Mouse mouse;

    // Create the test case
    public MouseTest()
    {
        this.mouse = new Mouse();
    }

    // Test that mousePressed is false by default
    @Test
    public void testMousePressedDefault()
    {
        assertFalse(mouse.getMousePressed());
    }

    // Test that mousePressed is true when mouse is pressed
    @Test
    public void testMousePressed()
    {
        MouseEvent click = new MouseEvent(new Component(){}, 0, 0, 0, 0, 0, 0, false);
        mouse.mousePressed(click);
        assertTrue(mouse.getMousePressed());
        mouse.mouseReleased(click);
    }

    // Test that mousePressed is false when mouse is released
    @Test
    public void testMouseReleased()
    {
        MouseEvent click = new MouseEvent(new Component(){}, 0, 0, 0, 0, 0, 0, false);
        mouse.mousePressed(click);
        mouse.mouseReleased(click);
        assertFalse(mouse.getMousePressed());
    }

    // Check that getMousePosition() is (500, 500) when mouse is clicked or held at (500, 500)
    @Test
    public void testGetMousePosition()
    {
        MouseEvent click = new MouseEvent(new Component(){}, 0, 0, 0, 500, 500, 0, false);
        mouse.mousePressed(click);
        assertTrue(new Coordinate(500, 500).equals(mouse.getMousePosition()));
        mouse.mouseReleased(click);
    }

    // Check that getAttackAngle() is 0 when mouse is click at (500, 0) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle0()
    {
        mouse.setMousePosition(new Coordinate(500, 0));
        assertEquals(0, mouse.getAttackAngle(0, 0));
    }

    // Check that getAttackAngle() is 45 when mouse is click at (500, 500) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle45()
    {
        mouse.setMousePosition(new Coordinate(500, 500));
        assertEquals(45, mouse.getAttackAngle(0, 0));
    }

    // Check that getAttackAngle() is 90 when mouse is click at (0, 500) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle90()
    {
        mouse.setMousePosition(new Coordinate(0, 500));
        assertEquals(90, mouse.getAttackAngle(0, 0));
    }

    // Check that getAttackAngle() is 135 when mouse is click at (-500, 500) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle135()
    {
        mouse.setMousePosition(new Coordinate(-500, 500));
        assertEquals(135, mouse.getAttackAngle(0, 0));
    }

    // Check that getAttackAngle() is 180 when mouse is click at (-500, 0) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle180()
    {
        mouse.setMousePosition(new Coordinate(-500, 0));
        assertEquals(180, mouse.getAttackAngle(0, 0));
    }

    // Check that getAttackAngle() is 270 when mouse is click at (0, -500) and origin is at (0, 0)
    @Test
    public void testGetAttackAngle270()
    {
        mouse.setMousePosition(new Coordinate(0, -500));
        assertEquals(270, mouse.getAttackAngle(0, 0));
    }

    // Test that attackDirection is AttackDirection.RIGHT when mouse is clicked at (500, 0) and origin is at (0, 0)
    @Test
    public void testGetAttackDirectionRight()
    {
        mouse.setMousePosition(new Coordinate(500, 0));
        assertEquals(AttackDirection.RIGHT, mouse.getAttackDirection(0, 0));
    }

    // Test that attackDirection is AttackDirection.DOWN when mouse is clicked at (0, 500) and origin is at (0, 0)
    @Test
    public void testGetAttackDirectionDown()
    {
        mouse.setMousePosition(new Coordinate(0, 500));
        assertEquals(AttackDirection.DOWN, mouse.getAttackDirection(0, 0));
    }

    // Test that attackDirection is AttackDirection.LEFT when mouse is clicked at (-500, 0) and origin is at (0, 0)
    @Test
    public void testGetAttackDirectionLeft()
    {
        mouse.setMousePosition(new Coordinate(-500, 0));
        assertEquals(AttackDirection.LEFT, mouse.getAttackDirection(0, 0));
    }

    // Test that attackDirection is AttackDirection.UP when mouse is clicked at (0, -500) and origin is at (0, 0)
    @Test
    public void testGetAttackDirectionUp()
    {
        mouse.setMousePosition(new Coordinate(0, -500));
        assertEquals(AttackDirection.UP, mouse.getAttackDirection(0, 0));
    }
}
