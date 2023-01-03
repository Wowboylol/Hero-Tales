/*  
 *  RedMushroomAttack.java
 *  
 *  Description: Attack pattern for RedMushroom enemy.
 * 
*/


package items.enemyattacks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Simulator;
import entities.Coordinate;
import entities.Projectile;
import entities.enums.EntityType;
import items.Wieldable;

public class RedMushroomAttack extends Attack implements Wieldable
{
    // Stats
    public static final EntityType user = EntityType.ENEMY;
    public static final int DAMAGE = 10;
    public static final int PROJECTILE_SPEED = 5;
    public static final int LIFETIME = 80;
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(13, 15, 21, 15);

    // Attributes
    private Simulator simulator;
    private BufferedImage projectileSprite;

    // Default constructor
    public RedMushroomAttack(Simulator simulator)
    {
        super(DAMAGE, PROJECTILE_SPEED, LIFETIME);
        this.simulator = simulator;
        loadSprite();
    }

    @Override
    public void attack(Coordinate spawnPosition, Coordinate playerPosition, int angle)
    {
        Projectile projectile = new Projectile(
            new Rectangle(HITBOX_CONFIGURATIONS), spawnPosition, playerPosition, 
            projectileSprite, this.getDamage(), this.getProjectileSpeed(),
            angle, this.getLifetime(), user
        );
        this.simulator.projectiles.add(projectile);
    }

    @Override
    public void loadSprite()
    {
        projectileSprite = this.spriteSetup("/projectiles/red_orb.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }
}
