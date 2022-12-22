/*  
 *  DullIronSword.java
 *  
 *  Description: Player's starter weapon.
 * 
*/

package items.weapons;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import main.Simulator;
import main.Utility;
import entities.Coordinate;
import entities.Projectile;

public class WoodenSword implements Weapon
{
    // Stats
    private int tier;
    private int minDamage;
    private int maxDamage;
    private int shots;
    private int projectileSpeed;
    private int lifetime;

    // Attributes
    private Simulator simulator;
    private BufferedImage weaponIcon;
    private BufferedImage projectileSprite;

    // Default constructor
    public WoodenSword(Simulator simulator)
    {
        this.simulator = simulator;
        loadSprite();
        this.tier = 0;
        this.minDamage = 25;
        this.maxDamage = 40;
        this.shots = 1;
        this.projectileSpeed = 7;
        this.lifetime = 40;
    }

    // Getters
    public int getTier() { return this.tier; }
    public int getMinDamage() { return this.minDamage; }
    public int getMaxDamage() { return this.maxDamage; }
    public int getShots() { return this.shots; }
    public int getProjectileSpeed() { return this.projectileSpeed; }
    public int getLifetime() { return this.lifetime; }
    public BufferedImage getWeaponIcon() { return this.weaponIcon; }

    // Setters
    public void setTier(int tier) { this.tier = tier; }
    public void setMinDamage(int minDamage) { this.minDamage = minDamage; }
    public void setMaxDamage(int maxDamage) { this.maxDamage = maxDamage; }
    public void setShots(int shots) { this.shots = shots; }
    public void setProjectileSpeed(int speed) { this.projectileSpeed = speed; }
    public void setLifetime(int lifetime) { this.lifetime = lifetime; }

    @Override
    public void attack(Coordinate spawnPosition, int angle)
    {
        int damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);
        Projectile projectile = new Projectile(spawnPosition, projectileSprite, damage, projectileSpeed, angle, lifetime);
        this.simulator.projectiles.add(projectile);
    }

    @Override
    public void loadSprite()
    {
        weaponIcon = spriteSetup("/weapons/wooden_sword.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        projectileSprite = spriteSetup("/projectiles/test_slash.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }

    // Helper function: Sets up player sprites by resizing image from file
    private BufferedImage spriteSetup(String imageName, int width, int height)
    {
        Utility utility = new Utility();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName));
            image = utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
