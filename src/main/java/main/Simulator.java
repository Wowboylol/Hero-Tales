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
import java.util.ArrayList;
import entities.*;
import graphics.*;

public class Simulator extends JPanel implements Runnable
{
    // Screen configurations
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int TILE_SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE;   // 48 pixels
    public static final int MAX_SCREEN_COL = 20;
    public static final int MAX_SCREEN_ROW = 15;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;     // 960 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;    // 720 pixels
    private static final int FPS = 60;

    // Setup
    private static Simulator instance = null;
    private ServiceInjector serviceInjector = new ServiceInjector(this);
    private Thread gameThread;

    // Injectable services
    public final Keyboard keyboard = new Keyboard();
    public final Mouse mouse = new Mouse();
    public final Player player = new Player(this);
    public final Camera camera = new Camera(this);
    public final CollisionChecker collisionChecker = new CollisionChecker(this);

    // Dependent services
    public final MapHandler mapHandler = new MapHandler(camera);

    // Lists
    public final ArrayList<Updateable> projectiles = new ArrayList<Updateable>();

    // Constructor (Singletons have a private constructor that creates a global instance on get_instance())
    private Simulator()
    {
        serviceInjector.injectDependencies();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        this.setFocusable(true);
    }

    // Getters
    public int getMapWidth() { return this.mapHandler.getCurrentMapWidth(); }
    public int getMapHeight() { return this.mapHandler.getCurrentMapHeight(); }

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
        
        // FIXME: This is a temporary solution to updating projectiles in a list
        for(int i = 0; i < projectiles.size(); i++)
        {
            projectiles.get(i).update();
            if(projectiles.get(i).isDestroyed()) projectiles.remove(i);
        }
    }

    // Draw UI with updated information, called by repaint()
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // DEBUG
        long drawStart = System.nanoTime();

        // Graphics is an abstract class so typecast and setup
        Graphics2D graphics2D = (Graphics2D)g;

        // Draw graphics by passing Graphics2D to various classes
        mapHandler.draw(graphics2D);
        player.draw(graphics2D);

        // FIXME: This is a temporary solution to drawing projectiles in a list
        for(int i = 0; i < projectiles.size(); i++) 
        {
            projectiles.get(i).draw(graphics2D);
        }

        // DEBUG
        if(keyboard.getDebugConsole()) debugConsole(graphics2D, drawStart);

        // Clean up resources
        graphics2D.dispose();
    }

    // Debug console
    public void debugConsole(Graphics2D graphics2d, long drawStart)
    {
        long drawTime = System.nanoTime() - drawStart;
        long FPSBound = 1000000000/FPS;
        Coordinate playerPosition = player.getWorldCoordinate();
        int playerOriginX = player.getOriginPointX() + playerPosition.getX();
        int playerOriginY = player.getOriginPointY() + playerPosition.getY();

        if(drawTime > FPSBound) graphics2d.setColor(Color.red);
        else graphics2d.setColor(Color.white);
        
        graphics2d.drawString("Draw time: " + drawTime, 10, 600);
        graphics2d.drawString("Player position: (" + playerPosition.getX() + ", " + playerPosition.getY() + ")", 10, 615);
        graphics2d.drawString("Player grid position: (" + playerOriginX/TILE_SIZE + ", " + playerOriginY/TILE_SIZE + ")", 10, 630);
    }
}
