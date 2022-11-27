/*  
 *  Keyboard.java
 *  
 *  Description: Gets input from keyboard.
 *
*/

package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entities.Direction;

public class Keyboard implements KeyListener
{
    // Attributes
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    // Returns whether requested direction is pressed (throw if invalid direction)
    public boolean getDirection(Direction d)
    {
        try {
            switch(d) {
                case UP:
                    return upPressed;
                case DOWN:
                    return downPressed;
                case LEFT:
                    return leftPressed;
                case RIGHT:
                    return rightPressed;
                default:
                    throw new IllegalArgumentException();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Returns whether ANY directional (WASD) key is pressed
    public boolean isWASDPressed()
    {
        return (upPressed==true || downPressed==true || leftPressed==true || rightPressed==true) ? true : false;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int ASCII = e.getKeyCode();

        if(ASCII == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if(ASCII == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if(ASCII == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if(ASCII == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int ASCII = e.getKeyCode();

        if(ASCII == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if(ASCII == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(ASCII == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(ASCII == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
