/*  
 *  HorizontalContainer.java
 *  
 *  Description: FIXME: Placeholder
 *
*/

package ui;

import java.awt.Dimension;

public class HorizontalContainer extends UIContainer
{
    public final String SPRITE_NAME = "wood_panel_test";

    @Override
    protected Dimension calculateContentSize()
    {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for(UIComponent child : children)
        {
            combinedChildWidth += child.getSize().getWidth() + child.getMargin().getHorizontal();
            if(child.getSize().getHeight() > tallestChildHeight)
            {
                tallestChildHeight = (int)child.getSize().getHeight();
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
            currentX += child.getSize().getWidth() + child.getMargin().getRight();
        }
    }

    @Override
    public void updateSprite()
    {
        this.setSprite(this.spriteSetup(SPRITE_NAME, this.getSize().width, this.getSize().height));
    }
}
