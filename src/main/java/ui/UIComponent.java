/*  
 *  UIComponent.java
 *  
 *  Description: All UI elements are derived from this class.
 *
*/

package ui;

import java.awt.Dimension;
import java.awt.Graphics2D;

import entities.Coordinate;

public abstract class UIComponent 
{
    // Attributes
    private Coordinate position;
    private Dimension size;
    private Spacing margin;
    private Spacing padding;

    // Default constrctor
    public UIComponent()
    {
        this.position = new Coordinate(0, 0);
        this.size = new Dimension(1, 1);
        this.margin = new Spacing(5);
        this.padding = new Spacing(5);
    }

    // Methods
    public abstract void update();
    public abstract void draw(Graphics2D graphics2d);

    // Getters
    public Coordinate getPosition() { return position; }
    public Dimension getSize() { return size; }
    public Spacing getMargin() { return margin; }
    public Spacing getPadding() { return padding; }

    // Setters
    protected void setPosition(Coordinate position) { this.position = position; }
    public void setPosition(int x, int y) { this.position = new Coordinate(x, y); }
    protected void setSize(Dimension size) { this.size = size; }
    protected void setSize(int width, int height) { this.size = new Dimension(width, height); }
    public void setMargin(Spacing margin) { this.margin = margin; }
    public void setPadding(Spacing padding) { this.padding = padding; }
}
