/*  
 *  Damageable.java
 *  
 *  Description: Interface for entities that can be damaged, healed, etc.
 *               An entity that is Damageable must be Updateable and Editable.
 * 
*/

package entities;

public interface Damageable extends Editable
{
    // Damage the entity by given amount
    public void damageEntity(int damage);

    // Heal the entity by given amount
    public void healEntity(int heal);
}
