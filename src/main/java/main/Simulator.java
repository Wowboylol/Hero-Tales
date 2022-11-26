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

import entities.Direction;

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
    private final int FPS = 60;

    // Attributes
    private static Simulator instance = null;
    private Keyboard keyboard = new Keyboard();
    private Thread gameThread;

    // FIXME: Player attributes (to be removed)
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 4;

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
        // FIXME: Update player position (to be removed)
        if(keyboard.getDirection(Direction.UP) == true)
        {
            playerY -= playerSpeed;
        }
        if(keyboard.getDirection(Direction.DOWN) == true)
        {
            playerY += playerSpeed;
        }
        if(keyboard.getDirection(Direction.LEFT) == true)
        {
            playerX -= playerSpeed;
        }
        if(keyboard.getDirection(Direction.RIGHT) == true)
        {
            playerX += playerSpeed;
        }
    }

    // Draw UI with updated information, called by repaint()
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Graphics is an abstract class so typecast and setup
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
