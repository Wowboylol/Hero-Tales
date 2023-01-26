/*  
 *  PopUpText.java
 *  
 *  Description: Renders floating pop up at specific location on screen.
 *               Entries are stored with color, text content, and lifetime
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

public class PopUpText 
{
    // Constants
    public static final Color DAMAGE_COLOR = new Color(240, 52, 24);
    public static final Color HEAL_COLOR = new Color(114, 243, 95);

    // Attributes
    private ArrayList<Entry<Color, Entry<String, Integer>>> popUpText = new ArrayList<Entry<Color, Entry<String, Integer>>>();

    // Methods
    public void addPopUpText(Color color, String textContent)
    {
        SimpleEntry<String, Integer> content = new SimpleEntry<String, Integer>(textContent, -5);
        this.popUpText.add(new SimpleEntry<Color, Entry<String, Integer>>(color, content));
    }

    public void addDamageText(int damageNumber)
    {
        SimpleEntry<String, Integer> content = new SimpleEntry<String, Integer>("-" + Integer.toString(damageNumber), -5);
        this.popUpText.add(new SimpleEntry<Color, Entry<String, Integer>>(DAMAGE_COLOR, content));
    }

    public void addHealText(int healNumber)
    {
        SimpleEntry<String, Integer> content = new SimpleEntry<String, Integer>("+" + Integer.toString(healNumber), -5);
        this.popUpText.add(new SimpleEntry<Color, Entry<String, Integer>>(HEAL_COLOR, content));
    }

    public void drawPopUpText(Graphics2D graphics2d, int screenPositionX, int screenPositionY)
    {
        if(popUpText.size() == 0) return;

        FontMetrics metrics = graphics2d.getFontMetrics(UIHandler.DAMAGE_TEXT_FONT);
        graphics2d.setColor(new Color(42, 39, 39));
        graphics2d.setFont(UIHandler.DAMAGE_TEXT_FONT);

        for(int i=0; i<popUpText.size(); i++)
        {
            int popUpOffset = popUpText.get(i).getValue().getValue();
            String text = popUpText.get(i).getValue().getKey();

            popUpText.get(i).getValue().setValue(popUpOffset-1);
            if(popUpOffset < -45) popUpText.remove(i);
            else 
            {
                graphics2d.drawString(
                    text, 
                    screenPositionX+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-1, 
                    screenPositionY+popUpOffset+1
                );
                graphics2d.setColor(popUpText.get(i).getKey());
                graphics2d.drawString(
                    text, 
                    screenPositionX+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-2, 
                    screenPositionY+popUpOffset
                );
            }
        }
    }
}
