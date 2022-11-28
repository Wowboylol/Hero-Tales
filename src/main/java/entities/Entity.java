/*  
 *  Entity.java
 *  
 *  Description: Abstract class, super class for players, enemies, objects, heroes, etc.
 *
*/

package entities;

public abstract class Entity 
{
    // Attributes
    private Coordinate position;
    
    // Default constructor
    public Entity(Coordinate spawnPosition)
    {
        this.position = spawnPosition;
    }

    // Getters
    public Coordinate getCoordinate() { return this.position; }
    public int getCoordinateX() { return this.position.get_X(); }
    public int getCoordinateY() { return this.position.get_Y(); }

    // Setters
    public void setCoordinate(Coordinate coordinate) { this.position = coordinate; }
    public void setCoordinateX(int X) { this.position.set_X(X); }
    public void setCoordinateY(int Y) { this.position.set_Y(Y); }
}
