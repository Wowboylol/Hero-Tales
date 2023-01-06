/*  
 *  PlayerStats.java
 *  
 *  Description: A player has additional stats: level, exp, vitality, charisma, intelligence.
 *
*/

package entities.stats;

import main.Simulator;
import items.Wieldable;
import items.playerweapons.*;

public class PlayerStats implements Stats
{
    // Stats
    private int maxHealth;
    private double currentHealth;
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
    private Wieldable weapon;

    // Default constructor, sets default stats
    public PlayerStats(Simulator simulator) 
    {
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.attack = 0;
        this.defense = 0;
        this.dexterity = 0;
        this.speed = 2;
        this.level = 0;
        this.exp = 0;
        this.vitality = 0;
        this.charisma = 0;
        this.intelligence = 0;
        this.weapon = new WoodenSword(simulator);
    }

    // Getters
    public int getMaxHealth() { return this.maxHealth; }
    public double getCurrentHealth() { return this.currentHealth; }
    public double getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getDexterity() { return this.dexterity; }
    public int getSpeed() { return this.speed; }
    public Wieldable getWeapon() { return this.weapon; }

    // Setters
    public void setMaxHealth(int health) { this.maxHealth = health; }
    public void setCurrentHealth(double health) { this.currentHealth = health; }
    public void setAttack(double attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setWeapon(Wieldable weapon) { this.weapon = weapon; }

    // Level: how strong the player is in terms of experience.
    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    // Experience: how much experience the player has.
    public int getExp() { return this.exp; }
    public void setExp(int experience) { this.exp = experience; }

    // Vitality: how fast the player can passively regenerate health.
    // Formula: Regen = 2.5 + 0.2 * vitality
    public double getVitality() { return this.vitality; }
    public void setVitality(double vitality) { this.vitality = vitality; }

    // Charisma: how strong of heroes the player can command in thier active party.
    public int getCharisma() { return this.charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }

    // Intelligence: how much cooldown reduction the player can have on their abilities.
    // Formula: Final cooldown = base cooldown * (100 / (100 + intelligence))
    public int getIntelligence() { return this.intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    // Calculations
    public int calculateFramesPerAttack() { return (int)(60/(0.1*dexterity+1.5)); }

    public void regenerateHealth() 
    { 
        currentHealth += (2.5 + 0.2*vitality)/60; 
        if(currentHealth > maxHealth) currentHealth = maxHealth;
    }

    public int damageEntity(int damage) 
    { 
        double maximumReduction = 0.1*damage;
        double finalDamage = damage - defense;
        if(finalDamage < maximumReduction) finalDamage = maximumReduction;
        currentHealth -= finalDamage;
        if(currentHealth < 0) currentHealth = 0;
        return (int)finalDamage;
    }
    
    public int healEntity(int heal) 
    { 
        this.currentHealth += heal;
        if(this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
        return heal;    
    }
}
