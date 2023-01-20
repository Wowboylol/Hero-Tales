/*  
 *  PlayerStats.java
 *  
 *  Description: A player has additional stats: level, exp, vitality, charisma, intelligence.
 *
*/

package entities.stats;

import java.util.concurrent.ThreadLocalRandom;

import main.Simulator;
import items.Wieldable;
import items.playerweapons.*;

public class PlayerStats extends Stats
{
    // Player stats
    private int level;
    private int exp;
    private int maxExp;
    private double vitality;
    private int charisma;
    private int intelligence;
    private Wieldable weapon;

    // Default constructor, sets default stats
    public PlayerStats(Simulator simulator) 
    {
        super(100, 0, 0, 0, 2);
        this.level = 1;
        this.maxExp = calculateMaxExp();
        this.exp = 0;
        this.vitality = 0;
        this.charisma = 0;
        this.intelligence = 0;
        this.weapon = new BasicWeapon(simulator, "/weapons/wooden_sword.json");
    }

    // Weapon: Determines the attack pattern and base damage of player.
    public Wieldable getWeapon() { return this.weapon; }
    public void setWeapon(Wieldable weapon) { this.weapon = weapon; }

    // Level: how strong the player is in terms of experience.
    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    // Max experience: how much experience the player needs to level up.
    public int getMaxExp() { return this.maxExp; }
    public void setMaxExp(int maxExperience) { this.maxExp = maxExperience; }

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
    @Override
    public int calculateFramesPerAttack() { return (int)(60/(0.1*dexterity+1.5)); }

    @Override
    public double calculateDamageMultiplier() { return (25+attack)/50; }

    @Override
    public void regenerateHealth() 
    { 
        currentHealth += (2.5 + 0.2*vitality)/60; 
        if(currentHealth > maxHealth) currentHealth = maxHealth;
    }

    @Override
    public int damageEntity(int damage) 
    { 
        double maximumReduction = 0.1*damage;
        double finalDamage = damage - defense;
        if(finalDamage < maximumReduction) finalDamage = maximumReduction;
        currentHealth -= finalDamage;
        if(currentHealth < 0) currentHealth = 0;
        return (int)finalDamage;
    }
    
    @Override
    public int healEntity(int heal) 
    { 
        this.currentHealth += heal;
        if(this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
        return heal;    
    }

    // Calculate maxExp needed based on player level
    public int calculateMaxExp() { return (100 * (level-1)) + 50; }

    // Add experience by given amount, which cannot exceed 10% of maxExp
    public void addExp(int exp) 
    { 
        int expCap = this.maxExp/10;
        if(exp > expCap) exp = expCap;
        this.exp += exp; 
        if(this.exp >= this.maxExp) 
        {
            levelUp();
            this.maxExp = calculateMaxExp();
            this.exp = 0;
        }
    }

    // Level up character and randomly increase stats while refreshing current health to max
    private void levelUp()
    {
        level++;
        this.maxHealth += ThreadLocalRandom.current().nextInt(20, 30 + 1);
        this.currentHealth = maxHealth;
        this.attack += ThreadLocalRandom.current().nextInt(0, 2 + 1);
        this.defense += ThreadLocalRandom.current().nextInt(0, 2 + 1);
        this.dexterity += ThreadLocalRandom.current().nextInt(0, 2 + 1);
        vitality += ThreadLocalRandom.current().nextInt(0, 2 + 1);
        charisma += ThreadLocalRandom.current().nextInt(0, 2 + 1);
        intelligence += ThreadLocalRandom.current().nextInt(0, 2 + 1);
    }
}
