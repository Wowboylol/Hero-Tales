/*  
 *  VerticalContainer.java
 *  
 *  Description: FIXME: Placeholder
 *
*/

package ui;

import java.awt.Dimension;

public class VerticalContainer extends UIContainer
{
    public final String SPRITE_NAME = "wood_panel_test";

    @Override
    protected Dimension calculateContentSize()
    {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for(UIComponent child : children)
        {
            combinedChildHeight += child.getSize().getHeight() + child.getMargin().getVertical();
            if(child.getSize().getWidth() > widestChildWidth)
            {
                widestChildWidth = (int)child.getSize().getWidth();
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
            currentY += child.getSize().getHeight() + child.getMargin().getBottom();
        }
    }

    @Override
    public void updateSprite()
    {
        this.setSprite(this.spriteSetup(SPRITE_NAME, this.getSize().width, this.getSize().height));
    }
}
