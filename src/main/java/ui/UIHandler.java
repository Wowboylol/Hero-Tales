/*  
 *  UIHandler.java
 *  
 *  Description: Handles all UI operations governed by ui package.
 *
*/

package ui;

import java.awt.Font;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Color;

import ui.elements.*;
import entities.stats.PlayerStats;
import main.GameState;
import main.Mouse;
import main.Simulator;

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
    private UIContainer characterUI;
    private UIState currentUI;

    // Methods
    public void intializeUI(Mouse mouse, PlayerStats playerStats)
    {
        addPauseUI(mouse);
        addPlayUI(playerStats);
        addCharacterUI();
    }

    public void updateUI(GameState gameState) 
    { 
        togglePlayUI(gameState);
        togglePauseUI(gameState);
        toggleCharacterUI(gameState);
        uiContainers.forEach(uiContainer -> uiContainer.update()); 
    }

    public void drawUI(Graphics2D graphics2d) { uiContainers.forEach(uiContainer -> uiContainer.draw(graphics2d)); }

    // Add and toggle pause UI
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

    // Add and toggle play UI
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
    public void addPlayUI(PlayerStats playerStats)
    {
        UIContainer container = new ColumnContainer("transparent");
        container.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.END));

        UIContainer hpContainer = new StaticContainer("hp_bar", 216, 24);
        hpContainer.setPadding(new Spacing(12, 12));

        UIComponent hpBar = new UIPlayerHealthBar(new Color(188, 17, 4), playerStats);
        hpBar.setPadding(new Spacing(108, 12));

        UIContainer expContainer = new StaticContainer("exp_bar", 216, 12);
        expContainer.setPadding(new Spacing(12, 18));
        expContainer.setMargin(new Spacing(-12, -0, -4, 0));

        UIComponent expBar = new UIPlayerExpBar(new Color(99, 128, 49), playerStats);
        expBar.setPadding(new Spacing(108, 6));

        hpContainer.addUIComponent(hpBar);
        expContainer.addUIComponent(expBar);
        container.addUIComponent(hpContainer);
        container.addUIComponent(expContainer);

        playUI = container;
    }

    // Add and toggle character UI
    public void toggleCharacterUI(GameState gameState)
    {
        if(gameState == GameState.CHARACTER && currentUI == null) 
        {
            uiContainers.add(characterUI); 
            currentUI = UIState.CHARACTER;
        }
        else if(gameState != GameState.CHARACTER && currentUI == UIState.CHARACTER) 
        {
            uiContainers.remove(characterUI); 
            currentUI = null;
        }
    }
    public void addCharacterUI()
    {
        UIContainer container = new ColumnContainer("transparent");
        container.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.START));
        container.setMargin(new Spacing(Simulator.TILE_SIZE, 0, 0, Simulator.TILE_SIZE));

        UIContainer background = new ColumnContainer("wood_panel_stats");
        background.setPadding(new Spacing(208, 240));

        container.addUIComponent(background);
        characterUI = container;
    }
}
