/*  
 *  UIPlayerHealthBar.java
 *  
 *  Description: Renders dynamic health bar with current health and max health for player.
 *
*/

package ui.elements;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.stats.Stats;

public class UIPlayerHealthBar extends SolidElement
{
    private Stats playerStats;
    private UIText healthText;

    public UIPlayerHealthBar(Color color, Stats playerStats)
    {
        super(color);
        this.playerStats = playerStats;
        setupText();
    }

    @Override
    public void update() 
    {
        calculateSize();
        updateText();
    }

    @Override
    public void draw(Graphics2D graphics2d)
    {
        super.draw(graphics2d);
        healthText.draw(graphics2d);
    }

    @Override
    public void calculateSize()
    {
        int barWidth = (int)(this.getPadding().getHorizontal() * (playerStats.getCurrentHealth() / playerStats.getMaxHealth()));
        this.setSize(barWidth, this.getPadding().getVertical());
    }

    public void updateText()
    {
        healthText.setText((int)playerStats.getCurrentHealth() + "/" + playerStats.getMaxHealth());
        healthText.setPosition(
            this.getPosition().getX() + (this.getPadding().getHorizontal()/2) - healthText.getSize().width/2, 
            this.getPosition().getY() - 6
        );
    }

    private void setupText()
    {
        healthText = new UIText((int)playerStats.getCurrentHealth() + "/" + playerStats.getMaxHealth());
        healthText.setFontSize(24);
        healthText.setFontColor(Color.WHITE);
        healthText.setDropShadow(false);
    }
}
