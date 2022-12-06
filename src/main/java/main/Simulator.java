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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import entities.*;
import graphics.*;

public class Simulator extends JPanel implements Runnable
{
    // Screen settings
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int TILE_SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE;   // 48 pixels
    public static final int MAX_SCREEN_COL = 20;
    public static final int MAX_SCREEN_ROW = 15;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;     // 960 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;    // 720 pixels
    private static final int FPS = 60;

    // Class storage
    private static Simulator instance = null;
    private Thread gameThread;
    private Keyboard keyboard = new Keyboard();
    private Player player = new Player(this, keyboard);
    private Camera camera = new Camera(player);
    private TileHandler tileHandler = new TileHandler(camera);

    // Constructor (Singletons have a private constructor that creates a global instance on get_instance())
    private Simulator()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    // Getters
    public Player getPlayer() { return this.player; }

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
        Graphics2D graphics2D = (Graphics2D)g;

        // Draw graphics by passing Graphics2D to various classes
        tileHandler.draw(graphics2D);
        player.draw(graphics2D);

        // Clean up resources
        graphics2D.dispose();
    }
}
