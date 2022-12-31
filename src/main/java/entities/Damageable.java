/*  
 *  Damageable.java
 *  
 *  Description: Interface for entities that can be damaged, healed, etc.
 * 
*/

package entities;

public interface Damageable extends Updateable
{
    // Damage the entity by given amount
    public void damageEntity(int damage);

    // Heal the entity by given amount
    public void healEntity(int heal);
}
