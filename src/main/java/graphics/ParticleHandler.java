/*  
 *  particleHandler.java
 *  
 *  Description: Handles updating and animation of particles.
 *
*/

package graphics;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;

import graphics.effects.Particle;

public class ParticleHandler 
{
    // Attributes
    private List<Particle> particles = new ArrayList<Particle>();

    // Add a particle to particle list
    public void addParticle(Particle particle) { particles.add(particle); }

    // Update all particles
    public void updateParticles()
    {
        for(int i = 0; i < particles.size(); i++)
        {
            particles.get(i).update();
            if(particles.get(i).isDestroyed()) particles.remove(i);
        }
    }

    // Render all particles
    public void drawParticles(Graphics2D graphics2D)
    {
        for(int i = 0; i < particles.size(); i++) { particles.get(i).draw(graphics2D); }
    }
}
