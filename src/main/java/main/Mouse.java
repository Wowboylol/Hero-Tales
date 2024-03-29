/*  
 *  Mouse.java
 *  
 *  Description: Gets input from mouse.
 *
*/

package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entities.Coordinate;
import entities.enums.AttackDirection;

public class Mouse implements MouseListener, MouseMotionListener
{
    // Attributes
    private Coordinate mouseClickPosition = new Coordinate(0, 0);
    private Coordinate cursorlocation = new Coordinate(0, 0);
    private boolean mousePressed = false;
    private boolean mouseClicked = false;

    // Getters
    public boolean getMousePressed() { return mousePressed; }
    public boolean getMouseClicked() { return mouseClicked; }

    // Mouse methods
    public Coordinate getMouseClickPosition() { return mouseClickPosition; }
    public void setMouseClickPosition(Coordinate mousePosition) { this.mouseClickPosition = mousePosition; }
    public Coordinate getCursorLocation() { return cursorlocation; }
    public void clearMouseClicked() { mouseClicked = false; }

    // Calculate attack angle
    public int getAttackAngle(int playerOriginX, int playerOriginY)
    {
        return Utility.calculateAngle(playerOriginX, playerOriginY, mouseClickPosition.getX(), mouseClickPosition.getY());
    }

    // Calculate attack direction
    public AttackDirection getAttackDirection(int playerOriginX, int playerOriginY)
    {
        int angle = getAttackAngle(playerOriginX, playerOriginY);
        if(angle >= 0 && angle < 45) return AttackDirection.RIGHT;
        else if(angle >= 45 && angle < 135) return AttackDirection.DOWN;
        else if(angle >= 135 && angle < 225) return AttackDirection.LEFT;
        else if(angle >= 225 && angle < 315) return AttackDirection.UP;
        else return AttackDirection.RIGHT;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        mousePressed = true;
        if(e.getX() < 0 || e.getX() > Simulator.SCREEN_WIDTH || e.getY() < 0 || e.getY() > Simulator.SCREEN_HEIGHT) return;
        mouseClickPosition.setX(e.getX());
        mouseClickPosition.setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        mousePressed = false;
        mouseClicked = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    { 
        if(e.getX() < 0 || e.getX() > Simulator.SCREEN_WIDTH || e.getY() < 0 || e.getY() > Simulator.SCREEN_HEIGHT) return;
        mouseClickPosition.setX(e.getX());
        mouseClickPosition.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    { 
        if(e.getX() < 0 || e.getX() > Simulator.SCREEN_WIDTH || e.getY() < 0 || e.getY() > Simulator.SCREEN_HEIGHT) return;
        cursorlocation.setX(e.getX());
        cursorlocation.setY(e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
