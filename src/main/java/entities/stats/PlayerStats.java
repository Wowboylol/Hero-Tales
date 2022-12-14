/*  
 *  Stats.java
 *  
 *  Description: A player has additional stats: level, exp, vitality, charisma, intelligence.
 *
*/

package entities.stats;

public class PlayerStats implements Stats
{
    // Stats
    private double health;
    private double attack;
    private int defense;
    private int dexterity;
    private int speed;

    // Player stats
    private int level;
    private int exp;
    private double vitality;
    private int charisma;
    private int intelligence;

    // Default constructor, sets default stats
    public PlayerStats() 
    {
        this.health = 100;
        this.attack = 0;
        this.defense = 0;
        this.dexterity = 0;
        this.speed = 2;
        this.level = 0;
        this.exp = 0;
        this.vitality = 0;
        this.charisma = 0;
        this.intelligence = 0;
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

    // Level: how strong the player is in terms of experience.
    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    // Experience: how much experience the player has.
    public int getExp() { return this.exp; }
    public void setExp(int experience) { this.exp = experience; }

    // Vitality: how fast the player can passively regenerate health.
    // Formula: Regen = 2 + 0.2 * vitality
    public double getVitality() { return this.vitality; }
    public void setVitality(double vitality) { this.vitality = vitality; }

    // Charisma: how strong of heroes the player can command in thier active party.
    public int getCharisma() { return this.charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }

    // Intelligence: how much cooldown reduction the player can have on their abilities.
    // Formula: Final cooldown = base cooldown * (100 / (100 + intelligence))
    public int getIntelligence() { return this.intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
}
