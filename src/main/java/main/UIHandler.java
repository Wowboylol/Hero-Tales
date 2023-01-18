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
    private UIContainer playUI;
    private UIState currentUI;

    // Methods
    public void intializeUI(Mouse mouse)
    {
        addPauseUI(mouse);
        addPlayUI();
    }

    public void updateUI(GameState gameState) 
    { 
        togglePlayUI(gameState);
        togglePauseUI(gameState);
        uiContainers.forEach(uiContainer -> uiContainer.update()); 
    }

    public void drawUI(Graphics2D graphics2d) { uiContainers.forEach(uiContainer -> uiContainer.draw(graphics2d)); }

    // Pause UI add and toggle
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
    public void addPauseUI(Mouse mouse)
    {
        UIContainer container = new ColumnContainer("wood_panel");
        container.setPadding(new Spacing(32));
        container.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));

        UIComponent menuButton = new UIButton(
            mouse, 
            "wood_menu_button", 
            "wood_menu_button_hover", 
            () -> { System.out.println("Menu button clicked!"); }
        );
        menuButton.setPadding(new Spacing(128, 32));

        UIComponent settingsButton = new UIButton(
            mouse, 
            "wood_settings_button", 
            "wood_settings_button_hover", 
            () -> { System.out.println("Settings button clicked!"); }
        );
        settingsButton.setPadding(new Spacing(128, 32));
        settingsButton.setMargin(new Spacing(0, 16));

        UIComponent quitButton = new UIButton(
            mouse, 
            "wood_quit_button", 
            "wood_quit_button_hover", 
            () -> { System.exit(0); }
        );
        quitButton.setPadding(new Spacing(128, 32));

        container.addUIComponent(menuButton);
        container.addUIComponent(settingsButton);
        container.addUIComponent(quitButton);
        pauseUI = container;
    }

    // Play UI add and toggle
    public void togglePlayUI(GameState gameState)
    {
        if(gameState == GameState.PLAY && currentUI == null) 
        {
            uiContainers.add(playUI); 
            currentUI = UIState.PLAY;
        }
        else if(gameState != GameState.PLAY && currentUI == UIState.PLAY) 
        {
            uiContainers.remove(playUI); 
            currentUI = null;
        }
    }
    public void addPlayUI()
    {
        UIContainer container = new ColumnContainer("transparent");
        container.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.END));

        UIContainer hpBar = new ColumnContainer("hp_bar");
        hpBar.setPadding(new Spacing(160, 32));

        UIContainer expBar = new ColumnContainer("exp_bar");
        expBar.setPadding(new Spacing(160, 32));
        expBar.setMargin(new Spacing(0, -8));

        container.addUIComponent(hpBar);
        container.addUIComponent(expBar);

        playUI = container;
    }
}
