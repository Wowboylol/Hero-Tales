/*  
 *  Weapon.java
 *  
 *  Description: Abstract class that defines the attributes of a weapon.
 *               A weapon must have a tier, max/min base damage, number of shots, projectile speed, 
 *                  lifetime, and sprite for it's projectiles.
 *
*/

package items.weapons;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import main.Utility;

public abstract class Weapon 
{
    // Tier: how powerful and rare a weapon is
    private int tier;

    // Min base damage: the minimum damage a weapon can deal
    private int minDamage;

    // Max base damage: the maximum damage a weapon can deal
    private int maxDamage;

    // Number of shots: how many projectiles are fired per shot
    private int shots;

    // Projectile speed: how fast a projectile travels per update
    private int projectileSpeed;

    // Lifetime: how much frames a shot projectile can last before disappearing
    private int lifetime;

    // Default constructor
    public Weapon(int tier, int minDamage, int maxDamage, int shots, int projectileSpeed, int lifetime)
    {
        this.tier = tier;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.shots = shots;
        this.projectileSpeed = projectileSpeed;
        this.lifetime = lifetime;
    }

    // Getters
    public int getTier() { return this.tier; }
    public int getMinDamage() { return this.minDamage; }
    public int getMaxDamage() { return this.maxDamage; }
    public int getShots() { return this.shots; }
    public int getProjectileSpeed() { return this.projectileSpeed; }
    public int getLifetime() { return this.lifetime; }

    // Helper function: Sets up player sprites by resizing image from file
    protected BufferedImage spriteSetup(String imageName, int width, int height)
    {
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName));
            image = Utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
