/*  
 *  UIComponent.java
 *  
 *  Description: All UI elements are derived from this class.
 *
*/

package ui;

import main.Utility;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        this.margin = new Spacing(0);
        this.padding = new Spacing(5);
    }

    // Methods
    public abstract Image loadSprite();
    public abstract void update();

    // Getters
    public Coordinate getPosition() { return position; }
    public Dimension getSize() { return size; }
    public Spacing getMargin() { return margin; }
    public Spacing getPadding() { return padding; }

    // Setters
    public void setPosition(Coordinate position) { this.position = position; }
    public void setPosition(int x, int y) { this.position = new Coordinate(x, y); }
    public void setSize(Dimension size) { this.size = size; }
    public void setSize(int width, int height) { this.size = new Dimension(width, height); }
    public void setMargin(Spacing margin) { this.margin = margin; }
    public void setPadding(Spacing padding) { this.padding = padding; }

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
