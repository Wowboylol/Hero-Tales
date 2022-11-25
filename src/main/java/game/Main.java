/*  
 *  Main.java
 *  
 *  Description: Sets up window of game and runs Simulator.
 *
*/

package game;
import javax.swing.JFrame;

public class Main 
{
    public static void main( String[] args )
    {
        // Setting up window properties
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hero Tales");

        // Integrate Simulator into the window
        Simulator simulator = Simulator.get_instance();
        window.add(simulator);
        window.pack();

        // Center and display window
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
