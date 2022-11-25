/*  
 *  Keyboard.java
 *  
 *  Description: Gets input from keyboard;
 *
*/

package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
    // Attributes
    private boolean upPressed, downPressed, leftPressed, rightPressed;

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
