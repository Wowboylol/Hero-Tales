package items.weapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import main.Simulator;
import entities.Coordinate;
import entities.Projectile;
import entities.enums.EntityType;
import items.Wieldable;

public class Combustion extends Weapon implements Wieldable
{
    // Stats
    public static final EntityType user = EntityType.PLAYER;
    public static final int TIER = 10;
    public static final int MIN_DAMAGE = 70;
    public static final int MAX_DAMAGE = 85;
    public static final int SHOTS = 8;
    public static final int PROJECTILE_SPEED = 8;
    public static final int LIFETIME = 15;
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(11, 9, 26, 30);
    public final int SOUND_ID = 0;

    // Attributes
    private Simulator simulator;
    private BufferedImage weaponIcon;
    private BufferedImage projectileSprite;

    // Default constructor
    public Combustion(Simulator simulator)
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
        
        for(int i=0; i<8; i++)
        {
            Projectile projectile = new Projectile(
                new Rectangle(HITBOX_CONFIGURATIONS), new Coordinate(spawnPosition), playerPosition, 
                projectileSprite, damage, this.getProjectileSpeed(),
                i*45, this.getLifetime(), user
            );
            this.simulator.projectiles.add(projectile);
        }
        this.simulator.playSoundEffect(SOUND_ID);
    }

    @Override
    public void loadSprite()
    {
        weaponIcon = this.spriteSetup("/weapons/combustion_spear.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        projectileSprite = this.spriteSetup("/projectiles/flame_thrust.png", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }
}
