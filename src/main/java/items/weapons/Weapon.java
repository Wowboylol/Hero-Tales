/*  
 *  Weapon.java
 *  
 *  Description: Interface that defines the methods of a weapon.
 *               A weapon must have a tier, max/min base damage, number of shots, projectile speed, 
 *                  lifetime, and sprite for it's projectiles.
 *               A weapon must be able to create new projectiles and shoot them.
 *
*/

package items.weapons;

import entities.Coordinate;

public interface Weapon 
{
    // Tier: how powerful and rare a weapon is
    public int getTier();
    public void setTier(int tier);

    // Min base damage: the minimum damage a weapon can deal
    public int getMinDamage();
    public void setMinDamage(int minDamage);

    // Max base damage: the maximum damage a weapon can deal
    public int getMaxDamage();
    public void setMaxDamage(int maxDamage);

    // Number of shots: how many projectiles are fired per shot
    public int getShots();
    public void setShots(int shots);

    // Projectile speed: how fast a projectile travels per update
    public int getProjectileSpeed();
    public void setProjectileSpeed(int speed);

    // Lifetime: how much frames a shot projectile can last before disappearing
    public int getLifetime();
    public void setLifetime(int lifetime);

    // Load weapon sprites into BufferedImage
    public void loadSprite();

    // Shoot a projectile
    public void attack(Coordinate spawnPosition, int angle);
}
