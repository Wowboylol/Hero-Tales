/*  
 *  PlayerStats.java
 *  
 *  Description: An enemy has additional stats...
 *
*/

package entities.stats;

public class EnemyStats implements Stats
{
    // Stats
    private int maxHealth;
    private double currentHealth;
    private double attack;
    private int defense;
    private int dexterity;
    private int speed;

    // Default constructor, sets default stats
    public EnemyStats() 
    {
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.attack = 0;
        this.defense = 0;
        this.dexterity = 0;
        this.speed = 2;
    }

    // Parameterized constructor, sets stats to parameters
    public EnemyStats(int maxHealth, double attack, int defense, int dexterity, int speed) 
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.dexterity = dexterity;
        this.speed = speed;
    }

    // Getters
    public int getMaxHealth() { return this.maxHealth; }
    public double getCurrentHealth() { return this.currentHealth; }
    public double getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getDexterity() { return this.dexterity; }
    public int getSpeed() { return this.speed; }

    // Setters
    public void setMaxHealth(int health) { this.maxHealth = health; }
    public void setCurrentHealth(double health) { this.currentHealth = health; }
    public void setAttack(double attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public void setSpeed(int speed) { this.speed = speed; }

    // Calculations
    public int calculateFramesPerAttack() { return (int)(60/(0.1*dexterity+1.5)); }
    
    public int damageEntity(int damage) 
    { 
        double maximumReduction = 0.1*damage;
        double finalDamage = damage - defense;
        if(finalDamage < maximumReduction) finalDamage = maximumReduction;
        this.currentHealth -= finalDamage;
        return (int)finalDamage;
    }
    
    public int healEntity(int heal) 
    { 
        this.currentHealth += heal;
        if(this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
        return heal;    
    }
}
