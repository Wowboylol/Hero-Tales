/*  
 *  ColumnContainer.java
 *  
 *  Description: A UIContainer that arranges its children in a column.
 *
*/

package ui;

import java.awt.Dimension;

public class ColumnContainer extends UIContainer
{
    public final String SPRITE_NAME = "wood_panel_test";

    @Override
    protected Dimension calculateContentSize()
    {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for(UIComponent child : children)
        {
            combinedChildHeight += child.getSize().height + child.getMargin().getVertical();
            if(child.getSize().width > widestChildWidth)
            {
                widestChildWidth = (int)child.getSize().width;
            }
        }
        return new Dimension(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition()
    {
        int currentY = getPadding().getTop();

        for(UIComponent child : children)
        {
            currentY += child.getMargin().getTop();
            child.setPosition(getPadding().getLeft(), currentY);
            currentY += child.getSize().height + child.getMargin().getBottom();
        }
    }

    @Override
    public void updateSprite()
    {
        if(this.getSize().width <= 0 && this.getSize().height <= 0) return;
        this.setSprite(this.spriteSetup(SPRITE_NAME, this.getSize().width, this.getSize().height));
    }
}
