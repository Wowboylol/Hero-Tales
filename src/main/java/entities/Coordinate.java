/*  
 *  Coordinate.java
 *  
 *  Description: Stores X and Y coordinates.
 * 
*/

package entities;

public class Coordinate 
{
    // Attributes
    private int X;
    private int Y;

    // Default constructor
    public Coordinate(int setX, int setY)
    {
        this.X = setX;
        this.Y = setY;
    }

    // Getters
    public int get_X() { return this.X; }
    public int get_Y() { return this.Y; }

    // Setters
    public void set_X(int val) { this.X = val; }
    public void set_Y(int val) { this.Y = val; }

    // Return whether given coordinate is equal to this coordinate
    public boolean equals(Coordinate other) { return (this.X == other.get_X() && this.Y == other.get_Y()) ? true : false; }
}
