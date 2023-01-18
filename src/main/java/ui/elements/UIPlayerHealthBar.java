/*  
 *  UIPlayerHealthBar.java
 *  
 *  Description: Renders dynamic health bar with current health and max health for player.
 *
*/

package ui.elements;

import java.awt.Color;

import entities.stats.Stats;

public class UIPlayerHealthBar extends SolidElement
{
    private Stats playerStats;

    public UIPlayerHealthBar(Color color, Stats playerStats)
    {
        super(color);
        this.playerStats = playerStats;
    }

    @Override
    public void calculateSize()
    {
        int barWidth = (int)(this.getPadding().getHorizontal() * (playerStats.getCurrentHealth() / playerStats.getMaxHealth()));
        this.setSize(barWidth, this.getPadding().getVertical());
    }
}
