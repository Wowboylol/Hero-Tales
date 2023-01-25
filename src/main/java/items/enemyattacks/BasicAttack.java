/*  
 *  BasicWeapon.java
 *  
 *  Description: A basic attack extends the Attack class and imports its data from a JSON file with type = "basic".
 *               Basic weapon has properties:
 *               - Projectile travels in a linear fashion
 *               - Only one type of projectile are spawned (with one sprite)
 *               - Projectiles are size Simulator.TILE_SIZE
 *
*/

package items.enemyattacks;

import org.json.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

import main.Simulator;
import entities.Coordinate;
import entities.enums.EntityType;
import entities.projectiles.Projectile;
import items.Wieldable;

public class BasicAttack extends Attack implements Wieldable
{
    // Attributes
    private Simulator simulator;

    // Configurations
    public static final EntityType user = EntityType.ENEMY;
    private Rectangle hitboxConfigurations;

    // Sprites
    private BufferedImage projectileSprite;

    // Projectile angle offsets
    private int[] projectileAngleOffsets;

    // Constructor
    public BasicAttack(Simulator simulator, String jsonPath)
    {
        this.simulator = simulator;
        loadFromJSON(jsonPath);
    }

    // Get data from JSON file of jsonPath
    private void loadFromJSON(String jsonPath)
    {
        JSONObject json;
        try {
            String jsonToString = new String(getClass().getResourceAsStream(jsonPath).readAllBytes(), StandardCharsets.UTF_8);
            json = new JSONObject(jsonToString);
            if(json.getString("type").equals("basic") == false) throw new IllegalArgumentException("JSON file is not of type \"basic\"." );
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }

        setupConfigurations(json);
        setupSprites(json);
        setupProjectileOffsets(json);
    }

    // Setup weapon configurations from JSON file
    private void setupConfigurations(JSONObject json)
    {
        JSONObject config = json.getJSONObject("config");
        this.damage = config.getInt("damage");
        this.shots = config.getInt("shots");
        this.projectileSpeed = config.getInt("projectileSpeed");
        this.lifetime = config.getInt("lifetime");

        JSONArray hitboxConfigJson = config.getJSONArray("hitboxConfig");
        hitboxConfigurations = new Rectangle(
            hitboxConfigJson.getInt(0), 
            hitboxConfigJson.getInt(1), 
            hitboxConfigJson.getInt(2), 
            hitboxConfigJson.getInt(3)
        );
    }

    // Setup weapon sprites from JSON file
    private void setupSprites(JSONObject json)
    {
        JSONObject spritePaths = json.getJSONObject("sprite");
        projectileSprite = this.spriteSetup(spritePaths.getString("projectile"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }

    // Setup projectile offsets for multiple projectile shooting
    private void setupProjectileOffsets(JSONObject json)
    {
        JSONArray projectileOffsets = json.getJSONArray("projectileOffsets");
        projectileAngleOffsets = new int[shots];
        for(int i = 0; i < shots; i++) {
            projectileAngleOffsets[i] = projectileOffsets.getInt(i);
        }
    }

    @Override
    public void attack(Coordinate spawnPosition, Coordinate playerPosition, int angle, double damageMultiplier)
    {
        int damage = (int)(this.getDamage() * damageMultiplier);

        for(int i = 0; i < this.shots; i++) 
        {
            Projectile projectile = new Projectile(
                new Rectangle(hitboxConfigurations), new Coordinate(spawnPosition), playerPosition, 
                projectileSprite, damage, this.getProjectileSpeed(),
                angle + projectileAngleOffsets[i], this.getLifetime(), user, false
            );
            this.simulator.projectiles.add(projectile);
        }
    }
}
