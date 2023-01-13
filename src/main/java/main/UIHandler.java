/*  
 *  UIHandler.java
 *  
 *  Description: Handles all UI operations governed by ui package.
 *
*/

package main;

import java.awt.Font;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Graphics2D;

import ui.*;
import ui.elements.*;

public class UIHandler 
{
    // Fonts
    public static final Font DEFAULT_FONT = new JLabel().getFont();
    public static final Font DAMAGE_TEXT_FONT = new Font("Arial", Font.BOLD, 25);

    // Attributes
    public final ArrayList<UIComponent> uiContainers = new ArrayList<UIComponent>();

    // UI Display
    private UIContainer pauseUI;
    private UIState currentUI;

    // Methods
    public void intializeUI()
    {
        addPauseUI();
    }

    public void updateUI(GameState gameState) 
    { 
        togglePauseUI(gameState);
        uiContainers.forEach(uiContainer -> uiContainer.update()); 
    }

    public void drawUI(Graphics2D graphics2d) { uiContainers.forEach(uiContainer -> uiContainer.draw(graphics2d)); }

    public void togglePauseUI(GameState gameState)
    {
        if(gameState == GameState.PAUSE && currentUI == null) 
        {
            uiContainers.add(pauseUI); 
            currentUI = UIState.PAUSE;
        }
        else if(gameState != GameState.PAUSE && currentUI == UIState.PAUSE) 
        {
            uiContainers.remove(pauseUI); 
            currentUI = null;
        }
    }
    public void addPauseUI()
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
        pauseUI = container;
    }
}
