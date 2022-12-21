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
    // Update the entity every frame
    public void update();

    // Draw the entity on screen every frame
    public void draw(Graphics2D graphics2D);
}
