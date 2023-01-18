/*  
 *  StaticContainer.java
 *  
 *  Description: A UIContainer that arranges its children on top of each other. 
 *               This container does not resize with its children.
 *
*/

package ui.elements;

import java.awt.Dimension;

import ui.UIContainer;
import ui.UIComponent;

public class StaticContainer extends UIContainer
{
    private Dimension finalSize;

    public StaticContainer(String imageName, int width, int height)
    {
        super(imageName);
        this.finalSize = new Dimension(width, height);
    }

    @Override
    protected Dimension calculateContentSize()
    {
        if(finalSize == null) return new Dimension(0, 0);
        return finalSize;
    }

    @Override
    protected void calculateContentPosition()
    {
        int currentY = getPosition().getY() + getPadding().getTop();

        for(UIComponent child : children)
        {
            currentY += child.getMargin().getTop();
            child.setPosition(getPosition().getX() + getPadding().getLeft(), currentY);
            currentY += child.getSize().height + child.getMargin().getBottom();
        }
    }
}
