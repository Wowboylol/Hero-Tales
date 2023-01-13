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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Simulator;
import main.Utility;

public abstract class UIContainer extends UIComponent
{
    // Attributes
    private Image sprite;
    private String spriteName;
    private Alignment alignment;
    protected ArrayList<UIComponent> children;

    // Default constructor
    public UIContainer(String imageName)
    {
        super();
        this.spriteName = imageName;
        this.alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        this.children = new ArrayList<UIComponent>();
        calculateSize();
        calculatePosition();
    }

    protected abstract Dimension calculateContentSize();
    protected abstract void calculateContentPosition();

    // Setters
    public void setSprite(Image sprite) { this.sprite = sprite; }
    public void setAlignment(Alignment alignment) { this.alignment = alignment; }

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
        graphics2d.drawImage(sprite, this.getRelativePosition().getX(), this.getRelativePosition().getY(), null);
        children.forEach(child -> child.draw(graphics2d));
    }

    // Rerender the sprite based on the current size
    public void updateSprite()
    {
        if(this.getSize().width <= 0 && this.getSize().height <= 0) return;
        this.setSprite(this.spriteSetup(spriteName, this.getSize().width, this.getSize().height));
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
        int x = getMargin().getLeft();
        if(alignment.horizontal == Alignment.Position.CENTER) x = Simulator.SCREEN_WIDTH/2 - this.getSize().width/2;
        else if(alignment.horizontal == Alignment.Position.END) x = Simulator.SCREEN_WIDTH - this.getSize().width - getMargin().getRight();

        int y = getMargin().getTop();
        if(alignment.vertical == Alignment.Position.CENTER) y = Simulator.SCREEN_HEIGHT/2 - this.getSize().height/2;
        else if(alignment.vertical == Alignment.Position.END) y = Simulator.SCREEN_HEIGHT - this.getSize().height - getMargin().getBottom();

        this.setRelativePosition(x, y);
        calculateContentPosition();
    }

    // Helper: Sets up player sprites by resizing image from file
    protected BufferedImage spriteSetup(String imageName, int width, int height)
    {
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/" + imageName + ".png"));
            image = Utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
