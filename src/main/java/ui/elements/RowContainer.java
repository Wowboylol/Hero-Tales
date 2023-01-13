/*  
 *  RowContainer.java
 *  
 *  Description: A UIContainer that arranges its children in a row.
 *
*/

package ui.elements;

import java.awt.Dimension;

import ui.UIComponent;
import ui.UIContainer;

public class RowContainer extends UIContainer
{
    public RowContainer(String imageName)
    {
        super(imageName);
    }

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
        int currentX = getRelativePosition().getX() + getPadding().getLeft();

        for(UIComponent child : children)
        {
            currentX += child.getMargin().getLeft();
            child.setRelativePosition(currentX, getRelativePosition().getY() + getPadding().getTop());
            child.setAbsolutePosition(
                currentX + getAbsolutePosition().getX(), 
                getRelativePosition().getY() + getPadding().getTop() + getAbsolutePosition().getY()
            );
            currentX += child.getSize().width + child.getMargin().getRight();
        }
    }
}
