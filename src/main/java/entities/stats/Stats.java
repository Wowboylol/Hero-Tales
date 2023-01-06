/*  
 *  Stats.java
 *  
 *  Description: Interface that defines the stats of an animate entity.
 *               An animate entity must have stats: health, attack, defense, dexterity, and movespeed.
 *
*/

package entities.stats;

import items.Wieldable;

public interface Stats 
{
    // Max Health: how much health an entity can have at most.
    public int getMaxHealth();
    public void setMaxHealth(int health);

    // Current Health: how much damage an entity can take before dying.
    public double getCurrentHealth();
    public void setCurrentHealth(double health);

    // Attack: how much damage an entities attack and abilities deal.
    // Formula: Dmg = Weapon dmg * ((25+attack)/50)
    public double getAttack();
    public void setAttack(double attack);

    // Defense: how much damage an entity can take before taking damage.
    // Formula: 1 dmg reduction per defense, caps at 90% reduction.
    public int getDefense();
    public void setDefense(int defense);

    // Dexterity: how fast an entity can attack
    // Formula: Attack/sec = 1.5 + 0.1 * dexterity, where Attack/frame = 60/(Attack/sec)
    public int getDexterity();
    public void setDexterity(int dexterity);

    // Movespeed: how fast an entity can move
    // Formula: 1 tile per 1 movespeed
    public int getSpeed();
    public void setSpeed(int movespeed);

    // Weapon: the weapon (player & hero) or attack (enemy) an entity uses
    public Wieldable getWeapon();
    public void setWeapon(Wieldable weapon);

    // Calculations
    public int calculateFramesPerAttack();
    public void regenerateHealth();
    public int damageEntity(int damage);
    public int healEntity(int heal);
}
