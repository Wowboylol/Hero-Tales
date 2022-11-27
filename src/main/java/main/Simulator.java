/*  
 *  Simulator.java
 *  
 *  Description: Runs the game loop and handles the main functions of the game.
 *               Is a singleton (only one instance of Simulator can exist at a time).
 *               Is also an adapter (modifies JPanel to fit a Runnable interface which originally is not enough).
 *
*/

package main;
import entities.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

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
    private final int FPS = 60;

    // Attributes
    private static Simulator instance = null;
    private Thread gameThread;
    private Keyboard keyboard = new Keyboard();
    private Player player = new Player(this);

    // Constructor (Singletons have a private constructor that creates a global instance on get_instance())
    private Simulator()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    // Getters
    public Keyboard getKeyboard() { return this.keyboard; }
    public Player getPlayer() { return this.player; }
    public int getTileSize() { return this.tileSize; }

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

    // Called though gameThread.start() and updates game at specified FPS
    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }

    // Update information of the game
    public void update()
    {
        player.update();
    }

    // Draw UI with updated information, called by repaint()
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Graphics is an abstract class so typecast and setup
        Graphics2D g2 = (Graphics2D)g;

        // Draw graphics by passing Graphics2D to various classes
        player.draw(g2);

        // Clean up resources
        g2.dispose();
    }
}
