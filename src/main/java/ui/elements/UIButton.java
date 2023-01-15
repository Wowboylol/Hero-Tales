/*  
 *  UIButton.java
 *  
 *  Description: Renders a UIComponent that is clickable and set to perform an action.
 *
*/

package ui.elements;

import java.awt.Graphics2D;
import java.awt.Dimension;

import ui.*;
import main.Mouse;
import entities.Coordinate;

public class UIButton extends UIClickable
{
    // Attributes
    private Runnable clickEvent;
    private UIContainer currentContainer;

    // Containers
    private UIContainer defaultContainer;
    private UIContainer hoverContainer;

    // Constructor
    public UIButton(Mouse mouse, String defaultImageName, String hoverImageName, Runnable clickEvent)
    {
        super(mouse);
        this.clickEvent = clickEvent;
        this.defaultContainer = new RowContainer(defaultImageName);
        this.hoverContainer = new RowContainer(hoverImageName);
        this.currentContainer = defaultContainer;
    }

    // Setters
    @Override
    public void setPadding(Spacing padding) 
    { 
        super.setPadding(padding);
        defaultContainer.setPadding(padding); 
        hoverContainer.setPadding(padding);
    }
    @Override
    public void setMargin(Spacing margin) 
    { 
        super.setMargin(margin);
        defaultContainer.setMargin(margin); 
        hoverContainer.setMargin(margin);
    }
    @Override
    public void setPosition(Coordinate position)
    {
        super.setPosition(position);
        defaultContainer.setPosition(position);
        hoverContainer.setPosition(position);
    }
    @Override
    public void setPosition(int x, int y)
    {
        super.setPosition(x, y);
        defaultContainer.setPosition(x, y);
        hoverContainer.setPosition(x, y);
    }
    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        defaultContainer.setSize(width, height);
        hoverContainer.setSize(width, height);
        updateSprites();
    }
    @Override
    public void setSize(Dimension size)
    {
        super.setSize(size);
        defaultContainer.setSize(size);
        hoverContainer.setSize(size);
        updateSprites();
    }

    @Override
    public void update()
    {
        super.update();
        setSize(getPadding().getHorizontal(), getPadding().getVertical());
        currentContainer.update();

        
        if(this.isHovered && this.isPressed) currentContainer = defaultContainer;
        else if(this.isHovered) currentContainer = hoverContainer;
        else currentContainer = defaultContainer;
    }
    
    @Override
    public void draw(Graphics2D graphics2d)
    {
        currentContainer.draw(graphics2d);
    }

    @Override
    public void onClick()
    {
        clickEvent.run();
    }

    private void updateSprites()
    {
        defaultContainer.updateSprite();
        hoverContainer.updateSprite();
    }
}
