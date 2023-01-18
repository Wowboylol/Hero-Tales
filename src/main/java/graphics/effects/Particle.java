/*  
 *  Particle.java
 *  
 *  Description: Handles particle generation based on parent entity.
 *
*/

package graphics.effects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Simulator;
import main.Utility;
import entities.Updateable;
import entities.Coordinate;

public class Particle implements Updateable
{
    // Attributes
    Simulator simulator;
    Coordinate particlePosition;

    // Appearance
    Color color;
    int size;
    int speed;
    int lifetime;
    int maxLifetime;
    int xDirection;
    int yDirection;

    // Constructors
    public Particle(Simulator simulator, Coordinate parentPosition, Color color, int xDirection, int yDirection)
    {
        this.simulator = simulator;
        this.particlePosition = new Coordinate(parentPosition);
        this.color = color;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.size = 6;
        this.speed = 1;
        this.lifetime = 16;
        this.maxLifetime = 16;
    }
    public Particle(Simulator simulator, Coordinate parentPosition, Color color, 
        int xDirection, int yDirection, int size, int speed, int lifetime)
    {
        this.simulator = simulator;
        this.particlePosition = new Coordinate(parentPosition);
        this.color = color;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.size = size;
        this.speed = speed;
        this.lifetime = lifetime;
        this.maxLifetime = lifetime;
    }

    @Override
    public void update()
    {
        if(lifetime <= 0) return;
        if(lifetime < maxLifetime/2.5) yDirection++;
        particlePosition.setX(particlePosition.getX() + (xDirection * speed));
        particlePosition.setY(particlePosition.getY() + (yDirection * speed));
        lifetime--;
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(color);
        graphics2D.fillRect(
            Utility.getScreenX(simulator, particlePosition.getX()), 
            Utility.getScreenY(simulator, particlePosition.getY()), 
            size, size
        );
    }

    @Override
    public boolean isDestroyed() { return lifetime <= 0; }
}
