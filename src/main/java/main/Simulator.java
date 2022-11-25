/*  
 *  Simulator.java
 *  
 *  Description: Runs the game loop and handles the main functions of the game.
 *               Is a singleton (only one instance of Simulator can exist at a time).
 *               Is also an adapter (modifies JPanel to fit a Runnable interface which originally is not enough).
 *
*/

package main;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class Simulator extends JPanel implements Runnable
{
    // Attributes
    private static Simulator instance = null;
    private static Thread gameThread;

    // Screen settings
    private static final int originalTileSize = 16;
    private static final int tileScale = 3;
    private static final int tileSize = originalTileSize * tileScale;   // 48 pixels
    private static final int maxScreenCol = 20;
    private static final int maxScreenRow = 15;
    private static final int screenWidth = tileSize * maxScreenCol;     // 960 pixels
    private static final int screenHeight = tileSize * maxScreenRow;    // 720 pixels

    // Constructor (Singletons have a private constructor that creates a global instance on get_instance())
    private Simulator()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    // Getter for Simulator instance, creates a Simulator if it doesn't already exist
    public static Simulator get_instance()
    {
        if(instance == null)
            instance = new Simulator();
        return instance;
    }

    // Creates a thread which executes instruction seperately to the Main class
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Called though gameThread.start(); and executes specified instructions
    @Override
    public void run()
    {
        // Game loop here
    }
}
