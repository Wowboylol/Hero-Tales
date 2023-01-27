/*  
 *  MultiWeapon.java
 *  
 *  Description: A Multiweapon composes of multiple Basic/Advanced weapons and imports its data from a JSON file with type = "multi".
 *               Multiweapon has properties:
 *               - Projectile travels in a linear or arc fashion
 *               - One or more types of projectile are spawned (multi-sprite compatibility)
 *               - One or more sound effects on attack
 *               - Projectiles can be any size
 *
*/

package items.playerweapons;

import org.json.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

import main.Simulator;
import entities.Coordinate;
import entities.enums.EntityType;

public class MultiWeapon extends Weapon
{
    // Attributes
    private Simulator simulator;
    private String name;

    // Configurations
    public static final EntityType user = EntityType.PLAYER;

    // Weapon data
    private BufferedImage weaponIcon;
    private Weapon[] weapons;
    private int weaponAmount;

    // Attack sequence
    private boolean[][] attackSequence;
    private int sequenceCounter = 0;
    private int sequenceLength;

    // Constructor
    public MultiWeapon(Simulator simulator, String jsonPath)
    {
        this.simulator = simulator;
        loadFromJSON(jsonPath);
    }

    // Getters
    public String getName() { return this.name; }
    public BufferedImage getWeaponIcon() { return this.weaponIcon; }

    // Get data from JSON file of jsonPath
    private void loadFromJSON(String jsonPath)
    {
        JSONObject json;
        try {
            String jsonToString = new String(getClass().getResourceAsStream(jsonPath).readAllBytes(), StandardCharsets.UTF_8);
            json = new JSONObject(jsonToString);
            if(json.getString("type").equals("multi") == false) throw new IllegalArgumentException("JSON file is not of type \"advanced\"." );
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }

        name = json.getString("name");
        this.tier = json.getInt("tier");
        weaponIcon = this.spriteSetup(json.getString("icon"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        sequenceLength = json.getInt("attackSequenceLength");
        setupWeaponComponents(json);
    }

    // Setup weapon components from JSON file
    private void setupWeaponComponents(JSONObject json)
    {
        JSONArray weaponComponents = json.getJSONArray("weapons");
        weaponAmount = weaponComponents.length();
        weapons = new Weapon[weaponAmount];
        attackSequence = new boolean[weaponAmount][sequenceLength];

        for(int i=0; i<weaponAmount; i++)
        {
            JSONObject currentWeapon = weaponComponents.getJSONObject(i);
            if(currentWeapon.getString("type").equals("basic"))
            {
                weapons[i] = new BasicWeapon(simulator, currentWeapon);
                setupAttackSequence(currentWeapon, i);
            }
            else if(currentWeapon.getString("type").equals("advanced"))
            {
                weapons[i] = new AdvancedWeapon(simulator, currentWeapon);
                setupAttackSequence(currentWeapon, i);
            }
            else throw new IllegalArgumentException("Weapon type is not valid.");
        }
    }

    // Helper: extract attack sequence from weapon JSONObject
    private void setupAttackSequence(JSONObject weapon, int index)
    {
        JSONArray sequence = weapon.getJSONArray("attackSequence");

        for(int j=0; j<sequenceLength; j++)
        {
            attackSequence[index][j] = sequence.getBoolean(j);
        }
    }

    @Override
    public void attack(Coordinate spawnPosition, Coordinate playerPosition, int angle, double damageMultiplier)
    {
        for(int i=0; i<weaponAmount; i++)
        {
            if(attackSequence[i][sequenceCounter] == true)
            {
                weapons[i].attack(spawnPosition, playerPosition, angle, damageMultiplier);
            }
        }
        sequenceCounter++;
        if(sequenceCounter == sequenceLength) sequenceCounter = 0;
    }
}
