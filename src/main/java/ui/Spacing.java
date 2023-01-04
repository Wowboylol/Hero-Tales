/*  
 *  Spacing.java
 *  
 *  Description: Defines spacing between UI containers (similar to CSS).
 *
*/

package ui;

public class Spacing 
{
    // Attributes
    private int top;
    private int right;
    private int bottom;
    private int left;

    // Constructors
    public Spacing(int top, int right, int bottom, int left) 
    {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Spacing(int horizontal, int vertical) 
    {
        this.top = vertical;
        this.right = horizontal;
        this.bottom = vertical;
        this.left = horizontal;
    }

    public Spacing(int all) 
    {
        this.top = all;
        this.right = all;
        this.bottom = all;
        this.left = all;
    }

    // Getters
    public int getTop() { return top; }
    public int getRight() { return right; }
    public int getBottom() { return bottom; }
    public int getLeft() { return left; }
    public int getHorizontal() { return right + left; }
    public int getVertical() { return top + bottom; }
}
