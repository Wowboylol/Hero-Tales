/*  
 *  Stats.java
 *  
 *  Description: Interface that defines the stats of an animate entity.
 *               An animate entity must have stats: health, attack, defense, dexterity, and movespeed.
 *
*/

package entities.stats;

import items.Wieldable;

public abstract class Stats 
{
    // Stats
    protected int maxHealth;
    protected double currentHealth;
    protected double attack;
    protected int defense;
    protected int dexterity;
    protected int speed;

    // Constructor
    public Stats(int maxHealth, double attack, int defense, int dexterity, int speed)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.dexterity = dexterity;
        this.speed = speed;
    }

    // Max Health: how much health an entity can have at most.
    public int getMaxHealth() { return this.maxHealth; };
    public void setMaxHealth(int health) { this.maxHealth = health; };

    // Current Health: how much damage an entity can take before dying.
    public double getCurrentHealth() { return this.currentHealth; };
    public void setCurrentHealth(double health) { this.currentHealth = health; };

    // Attack: how much damage an entities attack and abilities deal.
    // Formula: Dmg = Weapon dmg * ((25+attack)/50)
    public double getAttack() { return this.attack; };
    public void setAttack(double attack) { this.attack = attack; };

    // Defense: how much damage an entity can take before taking damage.
    // Formula: 1 dmg reduction per defense, caps at 90% reduction.
    public int getDefense() { return this.defense; };
    public void setDefense(int defense) { this.defense = defense; };

    // Dexterity: how fast an entity can attack
    // Formula: Attack/sec = 1.5 + 0.1 * dexterity, where Attack/frame = 60/(Attack/sec)
    public int getDexterity() { return this.dexterity; };
    public void setDexterity(int dexterity) { this.dexterity = dexterity; };

    // Movespeed: how fast an entity can move
    // Formula: 1 tile per 1 movespeed
    public int getSpeed() { return this.speed; };
    public void setSpeed(int movespeed) { this.speed = movespeed; };

    // Weapon: the weapon (player & hero) or attack (enemy) an entity uses
    public abstract Wieldable getWeapon();
    public abstract void setWeapon(Wieldable weapon);

    // Calculations
    public abstract int calculateFramesPerAttack();
    public abstract double calculateDamageMultiplier();
    public abstract void regenerateHealth();
    public abstract int damageEntity(int damage);
    public abstract int healEntity(int heal);
}
