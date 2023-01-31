/*  
 *  UIPlayerExpBar.java
 *  
 *  Description: Renders dynamic exp bar with exp and max exp for player.
 *
*/

package ui.groups;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.stats.PlayerStats;
import ui.elements.SolidElement;
import ui.elements.UIText;

public class UIPlayerExpBar extends SolidElement
{
    private PlayerStats playerStats;
    private UIText expText;
    private UIText levelText;

    public UIPlayerExpBar(Color color, PlayerStats playerStats)
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
        expText.draw(graphics2d);
        levelText.draw(graphics2d);
    }

    @Override
    public void calculateSize()
    {
        int barWidth = (int)(this.getPadding().getHorizontal() * ((double)playerStats.getExp() / (double)playerStats.getMaxExp()));
        this.setSize(barWidth, this.getPadding().getVertical());
    }

    public void updateText()
    {
        expText.setText(playerStats.getExp() + "/" + playerStats.getMaxExp());
        expText.setPosition(
            this.getPosition().getX() + (this.getPadding().getHorizontal()/2) - expText.getSize().width/2, 
            this.getPosition().getY() - 6
        );
        levelText.setText("Lv " + playerStats.getLevel());
        levelText.setPosition(
            this.getPosition().getX() + 5, 
            this.getPosition().getY() - 6
        );
    }

    private void setupText()
    {
        expText = new UIText(playerStats.getExp() + "/" + playerStats.getMaxExp());
        expText.setFontSize(16);
        expText.setFontColor(Color.WHITE);
        expText.setDropShadow(false);

        levelText = new UIText("Lv " + playerStats.getLevel());
        levelText.setFontSize(16);
        levelText.setFontColor(Color.WHITE);
        levelText.setDropShadow(false);
    }
}
