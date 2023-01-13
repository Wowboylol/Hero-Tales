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
    private Coordinate relativePosition;
    private Coordinate absolutePosition;
    private Dimension size;
    private Spacing margin;
    private Spacing padding;

    // Default constrctor
    public UIComponent()
    {
        this.relativePosition = new Coordinate(0, 0);
        this.absolutePosition = new Coordinate(0, 0);
        this.size = new Dimension(1, 1);
        this.margin = new Spacing(0);
        this.padding = new Spacing(0);
    }

    // Methods
    public abstract void update();
    public abstract void draw(Graphics2D graphics2d);

    // Getters
    public Coordinate getRelativePosition() { return relativePosition; }
    public Coordinate getAbsolutePosition() { return absolutePosition; }
    public Dimension getSize() { return size; }
    public Spacing getMargin() { return margin; }
    public Spacing getPadding() { return padding; }

    // Setters
    protected void setRelativePosition(Coordinate position) { this.relativePosition = position; }
    public void setRelativePosition(int x, int y) { this.relativePosition = new Coordinate(x, y); }
    protected void setAbsolutePosition(Coordinate position) { this.absolutePosition = position; }
    public void setAbsolutePosition(int x, int y) { this.absolutePosition = new Coordinate(x, y); }
    protected void setSize(Dimension size) { this.size = size; }
    protected void setSize(int width, int height) { this.size = new Dimension(width, height); }
    public void setMargin(Spacing margin) { this.margin = margin; }
    public void setPadding(Spacing padding) { this.padding = padding; }
}
