/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherits Entity.
 *
*/

package entities;

public abstract class AnimateEntity extends Entity
{
    // Defaults
    private int moveSpeed;

    // Default constructor
    public AnimateEntity(int setX, int setY)
    {
        super(setX, setY);
        this.moveSpeed = 3;
    }

    // Getters
    public int getMoveSpeed() { return this.moveSpeed; }

    // Setters
    public void setMoveSpeed(int speed) { this.moveSpeed = speed; }
}
