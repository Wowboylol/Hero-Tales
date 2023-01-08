/*  
 *  Alignment.java
 *  
 *  Description: FIXME: placeholder
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
