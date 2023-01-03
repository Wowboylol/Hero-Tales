/*  
 *  WoodenSword.java
 *  
 *  Description: Player's starter weapo (T0).
 * 
*/

package items.playerweapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import main.Simulator;
import entities.Coordinate;
import entities.Projectile;
import entities.enums.EntityType;
import items.Wieldable;

public class WoodenSword extends Weapon implements Wieldable
{
    // Stats
    public static final EntityType user = EntityType.PLAYER;
    public static final int TIER = 0;
    public static final int MIN_DAMAGE = 25;
    public static final int MAX_DAMAGE = 40;
    public static final int SHOTS = 1;
    public static final int PROJECTILE_SPEED = 8;
    public static final int LIFETIME = 25;
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(14, 12, 21, 23);
    public final int SOUND_ID = 0;

    // Attributes
    private Simulator simulator;
    private BufferedImage weaponIcon;
    private BufferedImage projectileSprite;

    // Default constructor
    public WoodenSword(Simulator simulator)
    {
        super(TIER, MIN_DAMAGE, MAX_DAMAGE, SHOTS, PROJECTILE_SPEED, LIFETIME);
        this.simulator = simulator;
        loadSprite();
    }

    // Getters
    public BufferedImage getWeaponIcon() { return this.weaponIcon; }

    @Override
    public void attack(Coordinate spawnPosition, Coordinate playerPosition, int angle)
    {
        int damage = ThreadLocalRandom.current().nextInt(this.getMinDamage(), this.getMaxDamage() + 1);
        Projectile projectile = new Projectile(
            new Rectangle(HITBOX_CONFIGURATIONS), spawnPosition, playerPosition, 
            projectileSprite, damage, this.getProjectileSpeed(),
            angle, this.getLifetime(), user
        );
        this.simulator.projectiles.add(projectile);
        this.simulator.playSoundEffect(SOUND_ID);
    }

    @Override
    public void loadSprite()
    {
        weaponIcon = this.spriteSetup("/weapons/wooden_sword.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        projectileSprite = this.spriteSetup("/projectiles/wooden_slash.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }
}