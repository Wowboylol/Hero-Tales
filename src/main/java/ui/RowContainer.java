/*  
 *  RowContainer.java
 *  
 *  Description: A UIContainer that arranges its children in a row.
 *
*/

package ui;

import java.awt.Dimension;

public class RowContainer extends UIContainer
{
    public final String SPRITE_NAME = "wood_panel_test";

    @Override
    protected Dimension calculateContentSize()
    {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for(UIComponent child : children)
        {
            combinedChildWidth += child.getSize().width + child.getMargin().getHorizontal();
            if(child.getSize().height > tallestChildHeight)
            {
                tallestChildHeight = (int)child.getSize().height;
            }
        }
        return new Dimension(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition()
    {
        int currentX = getPadding().getLeft();

        for(UIComponent child : children)
        {
            currentX += child.getMargin().getLeft();
            child.setPosition(currentX, getPadding().getTop());
            currentX += child.getSize().width + child.getMargin().getRight();
        }
    }

    @Override
    public void updateSprite()
    {
        if(this.getSize().width <= 0 && this.getSize().height <= 0) return;
        this.setSprite(this.spriteSetup(SPRITE_NAME, this.getSize().width, this.getSize().height));
    }
}
