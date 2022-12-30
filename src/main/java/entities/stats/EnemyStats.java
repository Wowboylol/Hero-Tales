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
    private double health;
    private double attack;
    private int defense;
    private int dexterity;
    private int speed;

    // Default constructor, sets default stats
    public EnemyStats() 
    {
        this.health = 100;
        this.attack = 0;
        this.defense = 0;
        this.dexterity = 0;
        this.speed = 2;
    }

    // Parameterized constructor, sets stats to parameters
    public EnemyStats(double health, double attack, int defense, int dexterity, int speed) 
    {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.dexterity = dexterity;
        this.speed = speed;
    }

    // Getters
    public double getHealth() { return this.health; }
    public double getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getDexterity() { return this.dexterity; }
    public int getSpeed() { return this.speed; }

    // Setters
    public void setHealth(double health) { this.health = health; }
    public void setAttack(double attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public void setSpeed(int speed) { this.speed = speed; }

    // Calculations
    public int calculateFramesPerAttack() { return (int)(60/(0.1*dexterity+1.5)); }
}
