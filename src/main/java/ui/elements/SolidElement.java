/*  
 *  SolidElement.java
 *  
 *  Description: Renders a solid rectangle UI of a given color that cannot contain children.
 *
*/

package ui.elements;

import java.awt.Color;
import java.awt.Graphics2D;

import ui.UIComponent;

public class SolidElement extends UIComponent
{
    // Attributes
    private Color color;

    // Default constructor
    public SolidElement(Color color)
    {
        super();
        this.color = color;
    }

    @Override
    public void update() 
    {
        calculateSize();
    }

    @Override
    public void draw(Graphics2D graphics2d)
    {
        graphics2d.setColor(color);
        graphics2d.fillRect(
            this.getPosition().getX(), 
            this.getPosition().getY(), 
            this.getSize().width, 
            this.getSize().height
        );
    }

    // Calculate size of solid rectangle with margin and padding
    public void calculateSize()
    {
        this.setSize(this.getPadding().getHorizontal(), this.getPadding().getVertical());
    }
}
