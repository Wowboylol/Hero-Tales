/*  
 *  Editable.java
 *  
 *  Description: Interface for entities that can have certain metadata edited by external classes.
 *               An entity that is Editable must be Updateable.
 *               Editable data includes: hitbox data, world coordinate data.
 * 
*/

package entities;

import java.awt.Rectangle;

public interface Editable extends Updateable
{
    // Edit hitbox
    public Rectangle getHitbox();
    public int getDefaultHitboxX();
    public int getDefaultHitboxY();

    // Edit world coordinate
    public Coordinate getWorldCoordinate();
    public int getWorldCoordinateX();
    public int getWorldCoordinateY();
    public void setWorldCoordinate(Coordinate coordinate);
    public void setWorldCoordinateX(int X);
    public void setWorldCoordinateY(int Y);
}
