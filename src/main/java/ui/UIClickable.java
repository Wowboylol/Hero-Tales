/*  
 *  UIClickable.java
 *  
 *  Description: Abstract class that all clickable UI elements should extend.
 *
*/

package ui;

import java.awt.Rectangle;

import main.Mouse;

public abstract class UIClickable extends UIComponent
{
    // Button attributes
    protected boolean isHovered;
    protected boolean isPressed;
    private Mouse mouse;
    private Rectangle clickbox;

    // Constructor
    public UIClickable(Mouse mouse)
    {
        super();
        this.mouse = mouse;
        this.clickbox = new Rectangle(0, 0, 0, 0);
    }

    // Methods
    public abstract void onClick();

    @Override
    public void update()
    {
        updateClickbox();
        isHovered = clickbox.contains(mouse.getCursorLocation().getX(), mouse.getCursorLocation().getY());
        isPressed = (isHovered && mouse.getMousePressed());

        if(isHovered && isPressed) onClick();
    }

    private void updateClickbox()
    {
        clickbox.setRect(
            getPosition().getX(), 
            getPosition().getY(), 
            (int)getSize().getWidth(), 
            (int)getSize().getHeight()
        );
    }
}
