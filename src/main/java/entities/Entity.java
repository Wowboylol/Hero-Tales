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
    private Coordinate worldPosition;
    
    // Default constructor
    public Entity(Coordinate spawnPosition)
    {
        this.worldPosition = spawnPosition;
    }

    // Getters
    public Coordinate getWorldCoordinate() { return this.worldPosition; }
    public int getWorldCoordinateX() { return this.worldPosition.get_X(); }
    public int getWorldCoordinateY() { return this.worldPosition.get_Y(); }

    // Setters
    public void setWorldCoordinate(Coordinate coordinate) { this.worldPosition = coordinate; }
    public void setWorldCoordinateX(int X) { this.worldPosition.set_X(X); }
    public void setWorldCoordinateY(int Y) { this.worldPosition.set_Y(Y); }
}
