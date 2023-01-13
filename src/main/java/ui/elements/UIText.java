/*  
 *  UIText.java
 *  
 *  Description: Renders a UIComponent containing text of a certain font.
 *
*/

package ui.elements;

import java.awt.Graphics2D;

import ui.UIComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Canvas;

public class UIText extends UIComponent
{
    // Text attributes
    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color fontColor;

    // Drop shadow attributes
    private boolean dropShadow;
    private int dropShadowOffset;
    private Color dropShadowColor;

    // Configured font
    private Font font;

    // Default constructor
    public UIText(String text)
    {
        super();
        this.text = text;
        this.fontSize = 24;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "ExpressionPro";
        this.fontColor = Color.WHITE;
        this.dropShadow = true;
        this.dropShadowOffset = 2;
        this.dropShadowColor = new Color(140, 140, 140);
        updateFont();
    }

    // Setters
    public void setText(String text) { this.text = text; updateFont();}
    public void setFontSize(int fontSize) { this.fontSize = fontSize; updateFont();}
    public void setFontStyle(int fontStyle) { this.fontStyle = fontStyle; updateFont();}
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; updateFont();}
    public void setFontColor(Color fontColor) { this.fontColor = fontColor; }
    public void setDropShadow(boolean dropShadow) { this.dropShadow = dropShadow; }
    public void setDropShadowOffset(int dropShadowOffset) { this.dropShadowOffset = dropShadowOffset; }
    public void setDropShadowColor(Color dropShadowColor) { this.dropShadowColor = dropShadowColor; }

    @Override
    public void update()
    {
        // Do nothing yet
    }

    @Override
    public void draw(Graphics2D graphics2d)
    {
        graphics2d.setFont(font);

        if(dropShadow)
        {
            graphics2d.setColor(dropShadowColor);
            graphics2d.drawString(
                text, 
                this.getRelativePosition().getX()+this.getPadding().getLeft()+dropShadowOffset, 
                fontSize+this.getRelativePosition().getY()+dropShadowOffset
            );
        }
        graphics2d.setColor(fontColor);
        graphics2d.drawString(
            text, 
            this.getRelativePosition().getX()+this.getPadding().getLeft(), 
            fontSize+this.getRelativePosition().getY()
        );
    }

    private void createFont()
    {
        font = new Font(fontFamily, fontStyle, fontSize);
    }

    private void calculateSize()
    {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        setSize(
            fontMetrics.stringWidth(text) + this.getPadding().getHorizontal(), 
            fontMetrics.getHeight() + this.getPadding().getVertical()
        );
    }

    private void updateFont()
    {
        createFont();
        calculateSize();
    }
}
