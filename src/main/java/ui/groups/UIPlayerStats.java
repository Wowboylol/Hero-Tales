/*  
 *  UIPlayerStats.java
 *  
 *  Description: Renders player stats when character screen is opened.
 *
*/

package ui.groups;

import java.awt.Color;

import ui.*;
import ui.elements.*;
import entities.stats.PlayerStats;

public class UIPlayerStats extends RowContainer
{
    // Children container
    private PlayerStats playerStats;
    private UIContainer columnOne = new ColumnContainer("transparent");
    private UIContainer columnTwo = new ColumnContainer("transparent");

    // Text
    private UIText[] stats = new UIText[8];

    // Constructor
    public UIPlayerStats(PlayerStats playerStats)
    {
        super("transparent");
        this.playerStats = playerStats;
        setupText();
        setupContainers();
    }

    @Override
    public void update()
    {
        super.update();
        columnOne.update();
        columnTwo.update();
        updateText();
    }

    public void updateText()
    {
        stats[0].setText(Integer.toString(playerStats.getMaxHealth()));
        stats[1].setText(Integer.toString((int)playerStats.getAttack()));
        stats[2].setText(Integer.toString((int)playerStats.getDexterity()));
        stats[3].setText(Integer.toString((int)playerStats.getSpeed()));
        stats[4].setText(Integer.toString((int)playerStats.getDefense()));
        stats[5].setText(Integer.toString((int)playerStats.getVitality()));
        stats[6].setText(Integer.toString((int)playerStats.getIntelligence()));
        stats[7].setText(Integer.toString((int)playerStats.getCharisma()));
    }

    private void setupText()
    {
        stats[0] = new UIText(Integer.toString(playerStats.getMaxHealth()));
        stats[1] = new UIText(Integer.toString((int)playerStats.getAttack()));
        stats[2] = new UIText(Integer.toString((int)playerStats.getDexterity()));
        stats[3] = new UIText(Integer.toString((int)playerStats.getSpeed()));
        stats[4] = new UIText(Integer.toString((int)playerStats.getDefense()));
        stats[5] = new UIText(Integer.toString((int)playerStats.getVitality()));
        stats[6] = new UIText(Integer.toString((int)playerStats.getIntelligence()));
        stats[7] = new UIText(Integer.toString((int)playerStats.getCharisma()));

        for(int i = 0; i < stats.length; i++)
        {
            stats[i].setFontSize(30);
            stats[i].setFontColor(new Color(153, 73, 65));
            stats[i].setDropShadow(true);
            stats[i].setDropShadowColor(new Color(84, 41, 39));
            if(i != stats.length-1) stats[i].setMargin(new Spacing(0, 0, 8, 0));
        }
        for(int i = 0; i < stats.length/2; i++) {columnOne.addUIComponent(stats[i]);}
        for(int i = stats.length/2; i < stats.length; i++) columnTwo.addUIComponent(stats[i]);
    }

    private void setupContainers()
    {
        // this.setMargin(new Spacing(24, 0, 0, 0));
        columnOne.setMargin(new Spacing(0, 0, 0, 76));
        columnTwo.setMargin(new Spacing(0, 0, 0, 104));
        this.addUIComponent(columnOne);
        this.addUIComponent(columnTwo);
    }
}
