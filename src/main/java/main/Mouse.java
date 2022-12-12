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
import entities.AttackDirection;
import entities.Coordinate;

public class Mouse implements MouseListener, MouseMotionListener
{
    // Attributes
    private boolean mousePressed = false;
    private Coordinate mousePosition = new Coordinate(0, 0);

    // Returns whether mouse is pressed
    public boolean getMousePressed() { return mousePressed; }

    // Returns mouse position
    public Coordinate getMousePosition() { return mousePosition; }

    // Calculate attack direction
    public AttackDirection getAttackDirection(int playerOriginX, int playerOriginY)
    {
        int deltaX = mousePosition.getX() - playerOriginX;
        int deltaY = mousePosition.getY() - playerOriginY;
        int angle = (int)Math.toDegrees(Math.atan2(deltaY, deltaX));
        if(angle < 0) angle += 360;
        
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
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        mousePressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    { 
        if(e.getX() < 0 || e.getX() > Simulator.SCREEN_WIDTH || e.getY() < 0 || e.getY() > Simulator.SCREEN_HEIGHT) return;
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
