/*  
 *  ColumnContainer.java
 *  
 *  Description: A UIContainer that arranges its children in a column.
 *
*/

package ui.elements;

import java.awt.Dimension;

import ui.UIComponent;
import ui.UIContainer;

public class ColumnContainer extends UIContainer
{
    public ColumnContainer(String imageName)
    {
        super(imageName);
    }

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
        int currentY = getRelativePosition().getY() + getPadding().getTop();

        for(UIComponent child : children)
        {
            currentY += child.getMargin().getTop();
            child.setRelativePosition(getRelativePosition().getX() + getPadding().getLeft(), currentY);
            child.setAbsolutePosition(
                getRelativePosition().getX() + getPadding().getLeft() + getAbsolutePosition().getX(),
                currentY + getAbsolutePosition().getY()
            );
            currentY += child.getSize().height + child.getMargin().getBottom();
        }
    }
}
