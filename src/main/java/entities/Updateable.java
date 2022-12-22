/*  
 *  Updateable.java
 *  
 *  Description: Interface for entities that can be updated and drawn on screen.
 * 
*/

package entities;

import java.awt.Graphics2D;

public interface Updateable 
{
    // Update the class data via the Simulator class
    public void update();

    // Draw the class in window via the Simulator class
    public void draw(Graphics2D graphics2D);

    // Check if the entity is destroyed
    public boolean isDestroyed();
}
