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
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Simulator extends JPanel implements Runnable
{
    // Screen settings
    private final int originalTileSize = 16;
    private final int tileScale = 3;
    private final int tileSize = originalTileSize * tileScale;   // 48 pixels
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 15;
    private final int screenWidth = tileSize * maxScreenCol;     // 960 pixels
    private final int screenHeight = tileSize * maxScreenRow;    // 720 pixels

    // Attributes
    private static Simulator instance = null;
    private Thread gameThread;
    private Keyboard keyboard = new Keyboard();

    // Constructor (Singletons have a private constructor that creates a global instance on get_instance())
    private Simulator()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    // Getter for Simulator instance, creates a Simulator if it doesn't already exist
    public static Simulator getInstance()
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

    // Called though gameThread.start() and executes specified instructions repeatedly until stopped
    @Override
    public void run()
    {
        while(gameThread != null)
        {
            // Update information of the game
            update();

            // Draw UI with updated information
            repaint();
        }
    }

    // Update information of the game
    public void update()
    {

    }

    // Draw UI with updated information, called by repaint()
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Graphics is an abstract class so we typecast and setup
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }
}
