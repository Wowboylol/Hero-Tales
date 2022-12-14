/*  
 *  Stats.java
 *  
 *  Description: Interface that defines the stats of an animate entity.
 *               An animate entity must have stats: health, attack, defense, dexterity, and movespeed.
 *
*/

package entities.stats;

public interface Stats 
{
    // Health: how much damage an entity can take before dying.
    public double getHealth();
    public void setHealth(double health);

    // Attack: how much damage an entities attack and abilities deal.
    // Formula: Dmg = Weapon dmg * ((25+attack)/50)
    public double getAttack();
    public void setAttack(double attack);

    // Defense: how much damage an entity can take before taking damage.
    // Formula: 1 dmg reduction per defense, caps at 90% reduction.
    public int getDefense();
    public void setDefense(int defense);

    // Dexterity: how fast an entity can attack
    public int getDexterity();
    public void setDexterity(int dexterity);

    // Movespeed: how fast an entity can move
    public int getMovespeed();
    public void setMovespeed(int movespeed);
}
