/*  
 *  Weapon.java
 *  
 *  Description: Abstract class that defines the attributes of a weapon.
 *               A weapon must have a tier, max/min base damage, number of shots, projectile speed, 
 *                  lifetime, and sprite for it's projectiles.
 *
*/

package items.playerweapons;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import main.Utility;

import items.Wieldable;

public abstract class Weapon implements Wieldable
{
    // Tier: how powerful and rare a weapon is
    protected int tier;

    // Min base damage: the minimum damage a weapon can deal
    protected int minDamage;

    // Max base damage: the maximum damage a weapon can deal
    protected int maxDamage;

    // Number of shots: how many projectiles are fired per shot
    protected int shots;

    // Projectile speed: how fast a projectile travels per update (in pixels)
    protected int projectileSpeed;

    // Lifetime: how much frames a shot projectile can last before disappearing
    protected int lifetime;

    // Pierce: detemines if a projectile can pierce through multiple entities
    protected boolean pierce;

    // Getters
    public int getTier() { return this.tier; }
    public int getMinDamage() { return this.minDamage; }
    public int getMaxDamage() { return this.maxDamage; }
    public int getShots() { return this.shots; }
    public int getProjectileSpeed() { return this.projectileSpeed; }
    public int getLifetime() { return this.lifetime; }
    public boolean getPierce() { return this.pierce; }

    // Helper function: Sets up projectile sprites by resizing image from file
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
