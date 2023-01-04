/*  
 *  UIContainer.java
 *  
 *  Description: FIXME: Placeholder
 *
*/

package ui;

import java.awt.Color;
import java.awt.Image;

public class UIContainer extends UIComponent
{
    // Attributes
    private Color backgroundColor;

    // Default constructor
    public UIContainer()
    {
        super();
        backgroundColor = new Color(255, 0, 0, 1);
        calculateSize();
        calculatePosition();
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
    public Image loadSprite() 
    { 
        return this.spriteSetup("/ui/wood_panel_test.png", this.getSize().width, this.getSize().height);
    }

    @Override
    public void update() 
    { 
        calculateSize();
        calculatePosition();
    }
}
