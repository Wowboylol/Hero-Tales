/*  
 *  Keyboard.java
 *  
 *  Description: Gets input from keyboard.
 *
*/

package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import entities.Direction;

public class Keyboard implements KeyListener
{
    // Attributes
    private final Set<Integer> pressedKeys = new HashSet<>();

    // Returns whether requested direction is pressed (throw if invalid direction)
    public boolean getDirection(Direction d)
    {
        try {
            switch(d)
            {
                case UP:
                    return pressedKeys.contains(KeyEvent.VK_W);
                case DOWN:
                    return pressedKeys.contains(KeyEvent.VK_S);
                case LEFT:
                    return pressedKeys.contains(KeyEvent.VK_A);
                case RIGHT:
                    return pressedKeys.contains(KeyEvent.VK_D);
                case UPLEFT:
                    return (pressedKeys.contains(KeyEvent.VK_W) && pressedKeys.contains(KeyEvent.VK_A)) ? true : false;
                case UPRIGHT:
                    return (pressedKeys.contains(KeyEvent.VK_W) && pressedKeys.contains(KeyEvent.VK_D)) ? true : false;
                case DOWNLEFT:
                    return (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_A)) ? true : false;
                case DOWNRIGHT:
                    return (pressedKeys.contains(KeyEvent.VK_S) && pressedKeys.contains(KeyEvent.VK_D)) ? true : false;
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
        if(
            pressedKeys.contains(KeyEvent.VK_W) || 
            pressedKeys.contains(KeyEvent.VK_A) ||
            pressedKeys.contains(KeyEvent.VK_S) || 
            pressedKeys.contains(KeyEvent.VK_D)
        ) return true;
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int ASCII = e.getKeyCode();
        if(ASCII == KeyEvent.VK_W || ASCII == KeyEvent.VK_S || ASCII == KeyEvent.VK_A || ASCII == KeyEvent.VK_D)
        {
            pressedKeys.add(ASCII);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int ASCII = e.getKeyCode();
        if(ASCII == KeyEvent.VK_W || ASCII == KeyEvent.VK_S || ASCII == KeyEvent.VK_A || ASCII == KeyEvent.VK_D)
        {
            pressedKeys.remove(ASCII);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
