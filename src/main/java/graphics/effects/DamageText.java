/*  
 *  DamageText.java
 *  
 *  Description: Renders damage text at specific location on screen.
 *
*/

package graphics.effects;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.AbstractMap.SimpleEntry;

import main.Simulator;
import main.UIHandler;

public class DamageText 
{
    private ArrayList<Entry<String, Integer>> damageText = new ArrayList<Entry<String, Integer>>();

    public void addDamageText(int damageText)
    {
        this.damageText.add(new SimpleEntry<String, Integer>("-" + Integer.toString(damageText), -5));
    }

    public void drawDamageText(Graphics2D graphics2d, int screenPositionX, int screenPositionY)
    {
        if(damageText.size() == 0) return;

        FontMetrics metrics = graphics2d.getFontMetrics(UIHandler.DAMAGE_TEXT_FONT);
        graphics2d.setColor(new Color(110, 19, 5));
        graphics2d.setFont(UIHandler.DAMAGE_TEXT_FONT);

        for(int i=0; i<damageText.size(); i++)
        {
            int damageOffset = damageText.get(i).getValue();
            String text = damageText.get(i).getKey();

            damageText.get(i).setValue(damageOffset-1);
            if(damageOffset < -45) damageText.remove(i);
            else 
            {
                graphics2d.drawString(
                    text, 
                    screenPositionX+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-1, 
                    screenPositionY+damageOffset+1
                );
                graphics2d.setColor(new Color(240, 52, 24));
                graphics2d.drawString(
                    text, 
                    screenPositionX+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-2, 
                    screenPositionY+damageOffset
                );
            }
        }
    }
}
