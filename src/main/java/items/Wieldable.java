/*  
 *  Wieldable.java
 *  
 *  Description: Interface for items that can be used to attack.
 *               A wieldable must be able to create new projectiles and shoot them.
 *
*/

package items;

import entities.Coordinate;

public interface Wieldable 
{
    // Shoot projectile(s)
    public void attack(Coordinate spawnPosition, Coordinate playerPosition, int angle);

    // Load sprites of item and projectile
    public void loadSprite();
}
