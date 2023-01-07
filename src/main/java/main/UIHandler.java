/*  
 *  UIHandler.java
 *  
 *  Description: Handles all UI operations governed by ui package.
 *
*/

package main;

import java.awt.Font;
import javax.swing.JLabel;

import ui.*;

public class UIHandler 
{
    // Fonts
    public static final Font DEFAULT_FONT = new JLabel().getFont();
    public static final Font DAMAGE_TEXT_FONT = new Font("Arial", Font.BOLD, 25);

    // Attributes
    private Simulator simulator;

    // Constructor
    public UIHandler(Simulator simulator)
    {
        this.simulator = simulator;
    }

    // Create and add UI components
    public void intializeUI()
    {
        UIContainer uiContainer = new VerticalContainer();
        uiContainer.setPadding(new Spacing(20));

        uiContainer.addUIComponent(new HorizontalContainer());
        uiContainer.addUIComponent(new HorizontalContainer());
        uiContainer.addUIComponent(new HorizontalContainer());

        simulator.uiContainers.add(uiContainer);
    }
}