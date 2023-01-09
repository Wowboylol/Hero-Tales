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
        UIContainer container = new ColumnContainer("wood_panel");
        container.setPadding(new Spacing(32));
        container.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));

        UIComponent menuButton = new RowContainer("wood_menu_button");
        menuButton.setPadding(new Spacing(128, 32));

        UIComponent settingsButton = new RowContainer("wood_settings_button");
        settingsButton.setPadding(new Spacing(128, 32));
        settingsButton.setMargin(new Spacing(0, 16));

        UIComponent quitButton = new RowContainer("wood_quit_button");
        quitButton.setPadding(new Spacing(128, 32));

        container.addUIComponent(menuButton);
        container.addUIComponent(settingsButton);
        container.addUIComponent(quitButton);
        simulator.uiContainers.add(container);
    }
}
