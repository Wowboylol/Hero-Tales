/*  
 *  Projectile.java
 *  
 *  Description: This is the abstract class for all projectiles.
 *
*/

package entities;

public abstract class Projectile extends Entity
{
    // Attributes
    private int damage;
    private int speed;
    private int angle;
    private int lifetime;

    // Default constructor
    public Projectile(Coordinate spawnPosition, int damage, int speed, int angle, int lifetime)
    {
        super(spawnPosition);
        this.damage = damage;
        this.speed = speed;
        this.angle = angle;
        this.lifetime = lifetime;
    }

    // Getters
    public int getDamage() { return this.damage; }
}
