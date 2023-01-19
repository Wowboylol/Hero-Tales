/*  
 *  PlayerStats.java
 *  
 *  Description: An enemy has no additional stats beyond the default stats.
 *               Enemies cannot regenerate health.
 *               Altered dexterity formula: Attack/sec = 1.0 + 0.1 * dexterity, where Attack/frame = 60/(Attack/sec)
 *
*/

package entities.stats;

import items.Wieldable;

public class EnemyStats extends Stats
{
    // Enemy stats
    private Wieldable attackPattern;

    // Default constructor, sets default stats
    public EnemyStats() 
    {
        super(100, 0, 0, 0, 2);
    }

    // Parameterized constructor, sets stats to parameters
    public EnemyStats(int maxHealth, double attack, int defense, int dexterity, int speed) 
    {
        super(maxHealth, attack, defense, dexterity, speed);
    }

    // Weapon: Determines the attack pattern and base damage of enemy
    public Wieldable getWeapon() { return this.attackPattern; }
    public void setWeapon(Wieldable attackPattern) { this.attackPattern = attackPattern; }

    // Calculations
    @Override
    public int calculateFramesPerAttack() { return (int)(60/(0.1*dexterity+1)); }

    @Override
    public double calculateDamageMultiplier() { return (25+attack)/50; }
    
    @Override
    public int damageEntity(int damage) 
    { 
        double maximumReduction = 0.1*damage;
        double finalDamage = damage - defense;
        if(finalDamage < maximumReduction) finalDamage = maximumReduction;
        this.currentHealth -= finalDamage;
        return (int)finalDamage;
    }
    
    @Override
    public int healEntity(int heal) 
    { 
        this.currentHealth += heal;
        if(this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
        return heal;    
    }

    @Override
    public void regenerateHealth() {}
}
