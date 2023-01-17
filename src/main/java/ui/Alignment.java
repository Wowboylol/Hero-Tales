/*  
 *  Alignment.java
 *  
 *  Description: Class that determines the position of the UIContainer on the screen.
 *
*/

package ui;

public class Alignment 
{
    // Alignment positions
    public enum Position
    {
        START, CENTER, END
    }

    // Attributes
    public final Position horizontal;
    public final Position vertical;

    // Constructor
    public Alignment(Position horizontal, Position vertical)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
