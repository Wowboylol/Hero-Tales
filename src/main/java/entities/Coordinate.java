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
    public int getX() { return this.X; }
    public int getY() { return this.Y; }

    // Setters
    public void setX(int val) { this.X = val; }
    public void setY(int val) { this.Y = val; }

    // Return whether given coordinate is equal to this coordinate
    public boolean equals(Coordinate other) { return (this.X == other.getX() && this.Y == other.getY()) ? true : false; }
}
