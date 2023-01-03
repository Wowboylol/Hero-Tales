/*  
 *  Sound.java
 *  
 *  Description: Handles all music and sound effects.
 *
*/

package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound 
{
    // Configurations
    public final int SOUNDS = 10;

    // Attributes
    private Clip clip;
    private URL soundURL[] = new URL[SOUNDS];

    // Default constructor
    public Sound()
    {
        soundURL[0] = getClass().getResource("/sounds/effects/sword_swing.wav");
        soundURL[1] = getClass().getResource("/sounds/effects/player_hit.wav");
        soundURL[2] = getClass().getResource("/sounds/effects/player_death.wav");
        soundURL[3] = getClass().getResource("/sounds/effects/humanoid_hit.wav");
        soundURL[4] = getClass().getResource("/sounds/effects/humanoid_death.wav");
    }

    // Get audio from file
    public void setFile(int soundID)
    {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[soundID]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Play audio
    public void play()
    {
        clip.setFramePosition(0);
        clip.start();
    }

    // Loop audio
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stop audio
    public void stop()
    {
        clip.stop();
    }
}
