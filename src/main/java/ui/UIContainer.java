/*  
 *  UIContainer.java
 *  
 *  Description: A container for UIComponents. Can be used to group UIComponents together.
 *
*/

package ui;

import java.awt.Image;
import java.awt.Graphics2D;

public class UIContainer extends UIComponent
{
    // Attributes
    private Image sprite;

    // Default constructor
    public UIContainer()
    {
        super();
        calculateSize();
        calculatePosition();
        loadSprite();
    }

    // Helper: FIXME: calculate size with margin and padding
    private void calculateSize()
    {
        this.setSize(getPadding().getHorizontal(), getPadding().getVertical());
    }

    // Helper: FIXME: calculate position based on top and left margins
    private void calculatePosition()
    {
        this.setPosition(getMargin().getLeft(), getMargin().getTop());
    }

    @Override
    public void update() 
    { 
        calculateSize();
        calculatePosition();
    }

    @Override
    public void draw(Graphics2D graphics2d) 
    { 
        graphics2d.drawImage(sprite, this.getPosition().getX(), this.getPosition().getY(), null);
    }

    // Loads and stores sprite
    private void loadSprite()
    {
        sprite = this.spriteSetup("wood_panel_test", this.getSize().width, this.getSize().height);
    }
}
