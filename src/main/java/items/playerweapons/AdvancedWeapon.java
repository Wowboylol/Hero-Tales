/*  
 *  AdvancedWeapon.java
 *  
 *  Description: An advanced weapon extends the Weapon class and imports its data from a JSON file with type = "advanced".
 *               Advanced weapon has properties:
 *               - Projectile travels in an arc fashion (specfically sinusoidal)
 *               - Only one type of projectile are spawned (with one sprite)
 *               - Have only one sound effect on attack
 *               - Projectiles can be any size
 *
*/

package items.playerweapons;

import org.json.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
import java.nio.charset.StandardCharsets;

import main.Simulator;
import main.Utility;
import entities.Coordinate;
import entities.enums.EntityType;
import entities.projectiles.SinusoidalProjectile;

public class AdvancedWeapon extends Weapon
{
    // Attributes
    private Simulator simulator;
    private String name;

    // Configurations
    public static final EntityType user = EntityType.PLAYER;
    private Rectangle hitboxConfigurations;

    // Sinoisoidal transformation data
    private int amplitude;
    private int frequency;
    private boolean inverted;

    // Sound
    private int soundAttackID;

    // Sprites
    private BufferedImage weaponIcon;
    private BufferedImage projectileSprite;

    // Projectile angle offsets
    private int[] projectileAngleOffsets;

    // Constructor
    public AdvancedWeapon(Simulator simulator, String jsonPath)
    {
        this.simulator = simulator;
        loadFromJSON(jsonPath);
    }

    // Constructor (called from MultiWeapon class)
    public AdvancedWeapon(Simulator simulator, JSONObject json)
    {
        this.simulator = simulator;
        loadFromJSON(json);
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
            if(json.getString("type").equals("advanced") == false) throw new IllegalArgumentException("JSON file is not of type \"advanced\"." );
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }

        name = json.getString("name");
        this.tier = json.getInt("tier");
        setupConfigurations(json);
        setupProjectilePath(json);
        setupSoundID(json);
        setupSprites(json);
        setupProjectileOffsets(json);
    }

    // Get data from given JSON object
    private void loadFromJSON(JSONObject json)
    {
        try {
            if(json.getString("type").equals("advanced") == false) throw new IllegalArgumentException("JSON file is not of type \"advanced\"." );
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }

        name = "Weapon component";
        this.tier = -1;
        setupConfigurations(json);
        setupProjectilePath(json);
        setupSoundID(json);
        setupSprites(json);
        setupProjectileOffsets(json);
    }

    // Setup weapon configurations from JSON file
    private void setupConfigurations(JSONObject json)
    {
        JSONObject config = json.getJSONObject("config");
        this.minDamage = config.getInt("minDamage");
        this.maxDamage = config.getInt("maxDamage");
        this.shots = config.getInt("shots");
        this.projectileSpeed = config.getInt("projectileSpeed");
        this.lifetime = config.getInt("lifetime");
        this.pierce = config.getBoolean("pierce");

        JSONArray hitboxConfigJson = config.getJSONArray("hitboxConfig");
        hitboxConfigurations = new Rectangle(
            hitboxConfigJson.getInt(0), 
            hitboxConfigJson.getInt(1), 
            hitboxConfigJson.getInt(2), 
            hitboxConfigJson.getInt(3)
        );
    }

    // Setup weapon projectile path (sinusoidal transformation) from JSON file
    private void setupProjectilePath(JSONObject json)
    {
        JSONObject projectilePath = json.getJSONObject("projectilePath");
        amplitude = projectilePath.getInt("amplitude");
        frequency = projectilePath.getInt("frequency");
        inverted = projectilePath.getBoolean("inverted");
    }

    // Setup weapon sound ID from JSON file
    private void setupSoundID(JSONObject json)
    {
        soundAttackID = json.getInt("soundID");
    }

    // Setup weapon sprites from JSON file
    private void setupSprites(JSONObject json)
    {
        JSONObject spritePaths = json.getJSONObject("sprite");
        weaponIcon = this.spriteSetup(spritePaths.getString("icon"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);

        int spriteWidth = spritePaths.getJSONArray("spriteSize").getInt(0);
        int spriteHeight = spritePaths.getJSONArray("spriteSize").getInt(1);
        projectileSprite = this.spriteSetup(spritePaths.getString("projectile"), spriteWidth, spriteHeight);

        if(spritePaths.getBoolean("diagonal_orientation")) projectileSprite = Utility.rotateImage(projectileSprite, -45);
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
        int damage = (int)(ThreadLocalRandom.current().nextInt(this.minDamage, this.maxDamage + 1) * damageMultiplier);

        for(int i = 0; i < this.shots; i++) 
        {
            SinusoidalProjectile projectile = new SinusoidalProjectile(
                new Rectangle(hitboxConfigurations), new Coordinate(spawnPosition), playerPosition, 
                projectileSprite, damage, this.getProjectileSpeed(), angle + projectileAngleOffsets[i],
                this.getLifetime(), user, pierce, amplitude, frequency, inverted
            );
            this.simulator.projectiles.add(projectile);
        }
        this.simulator.playSoundEffect(soundAttackID);
    }
}
