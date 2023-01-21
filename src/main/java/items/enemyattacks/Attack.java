/*  
 *  Attack.java
 *  
 *  Description: Abstract class that defines the attributes of an attack (from enemy or boss).
 *               An attack must have a fixed damage, projectile speed, lifetime, and sprite for it's projectiles.
 *
*/

package items.enemyattacks;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import main.Utility;

public abstract class Attack 
{
    // Damage: the damage an attack deals
    protected int damage;

    // Number of shots: how many projectiles are fired per attack
    protected int shots;

    // Projectile speed: how fast a projectile travels per update (in pixels)
    protected int projectileSpeed;

    // Lifetime: how much frames a shot projectile can last before disappearing
    protected int lifetime;

    // Getters
    public int getDamage() { return this.damage; }
    public int getProjectileSpeed() { return this.projectileSpeed; }
    public int getLifetime() { return this.lifetime; }

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
