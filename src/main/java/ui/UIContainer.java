/*  
 *  UIContainer.java
 *  
 *  Description: A container for UIComponents. Can be used to group UIComponents together.
 *
*/

package ui;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.ArrayList;

public abstract class UIContainer extends UIComponent
{
    // Attributes
    private Image sprite;
    protected ArrayList<UIComponent> children;

    // Default constructor
    public UIContainer()
    {
        super();
        this.children = new ArrayList<UIComponent>();
        calculateSize();
        calculatePosition();
        updateSprite();
    }

    protected abstract Dimension calculateContentSize();
    protected abstract void calculateContentPosition();
    protected abstract void updateSprite();

    // Setters
    public void setSprite(Image sprite) { this.sprite = sprite; }

    // Add a child UIComponent to this container
    public void addUIComponent(UIComponent uiComponent)
    {
        children.add(uiComponent);
    }

    @Override
    public void update() 
    { 
        children.forEach(child -> child.update());
        calculateSize();
        calculatePosition();
    }

    @Override
    public void draw(Graphics2D graphics2d) 
    { 
        graphics2d.drawImage(sprite, this.getPosition().getX(), this.getPosition().getY(), null);
        children.forEach(child -> child.draw(graphics2d));
    }

    // Helper: calculate size with margin and padding
    private void calculateSize()
    {
        Dimension calculatedContentSize = calculateContentSize();
        int oldWidth = this.getSize().width;
        int oldHeight = this.getSize().height;
        this.setSize(
            getPadding().getHorizontal() + (int)calculatedContentSize.getWidth(), 
            getPadding().getVertical() + (int)calculatedContentSize.getHeight()
        );
        if(oldWidth != this.getSize().width || oldHeight != this.getSize().height) updateSprite();
    }

    // Helper: calculate position based on top and left margins
    private void calculatePosition()
    {
        this.setPosition(getMargin().getLeft(), getMargin().getTop());
        calculateContentPosition();
    }
}
