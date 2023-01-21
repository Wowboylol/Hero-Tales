/*  
 *  BasicEnemy.java
 *  
 *  Description: A basic enemy extends the Enemy class and imports its data from a JSON file with type = "basic".
 *               Basic enemy has properties:
 *               - Sprite size of Simulator.TILE_SIZE
 *               - A single attack pattern
 *               - Randomized movement
 *               - Hit and death sounds only
 *
*/

package entities.enemies;

import org.json.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.nio.charset.StandardCharsets;

import main.Simulator;
import main.CollisionChecker;
import entities.*;
import entities.stats.EnemyStats;
import graphics.effects.Particle;
import items.enemyattacks.BasicAttack;

public class BasicEnemy extends Enemy implements Damageable
{
    // Attributes
    private Simulator simulator;
    private CollisionChecker collisionChecker;
    private String name;

    // Configurations
    private Rectangle hitboxConfigurations;
    private EnemyStats enemyStats;
    private Color particleColor;
    private int moveAnimationSpeed;
    private int hpBarOffset;
    private int aggroRange;
    private int expDrop;

    // Sounds
    private int soundHitID;
    private int soundDeathID;

    // Constructor
    public BasicEnemy(Simulator simulator, CollisionChecker collisionChecker, Coordinate spawnLocation, String jsonPath)
    {
        super(spawnLocation, simulator);
        this.simulator = simulator;
        this.collisionChecker = collisionChecker;
        loadFromJSON(jsonPath);

        // Super class overloading
        this.setStats(enemyStats);
        this.setHitbox(hitboxConfigurations);
        this.setMoveAnimationSpeed(moveAnimationSpeed);
    }

    // Getters
    public String getName() { return name; }

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

        name = json.getString("name");
        setupConfigurations(json);
        setupSoundID(json);
        setupSprites(json);
        setupAttack(json);
    }

    // Setup enemy configurations from JSON file
    private void setupConfigurations(JSONObject json)
    {
        JSONObject config = json.getJSONObject("config");
        moveAnimationSpeed = config.getInt("moveAnimationSpeed");
        hpBarOffset = config.getInt("hpBarOffset");
        aggroRange = config.getInt("aggroRange");
        expDrop = config.getInt("expDrop");

        JSONArray hitboxConfigJson = config.getJSONArray("hitboxConfig");
        hitboxConfigurations = new Rectangle(
            hitboxConfigJson.getInt(0), 
            hitboxConfigJson.getInt(1), 
            hitboxConfigJson.getInt(2), 
            hitboxConfigJson.getInt(3)
        );

        JSONArray enemyStatsJson = config.getJSONArray("enemyStats");
        enemyStats = new EnemyStats(
            enemyStatsJson.getInt(0), 
            enemyStatsJson.getInt(1), 
            enemyStatsJson.getInt(2), 
            enemyStatsJson.getInt(3), 
            enemyStatsJson.getInt(4)
        );

        JSONArray rgbJson = config.getJSONArray("particleColor");
        particleColor = new Color(
            rgbJson.getInt(0), 
            rgbJson.getInt(1), 
            rgbJson.getInt(2)
        );
    }

    // Setup enemy sound ID from JSON file
    private void setupSoundID(JSONObject json)
    {
        JSONObject soundID = json.getJSONObject("sound");
        soundHitID = soundID.getInt("hitID");
        soundDeathID = soundID.getInt("deathID");
    }

    // Setup enemy sprites from JSON file
    private void setupSprites(JSONObject json)
    {
        JSONObject spritePaths = json.getJSONObject("sprite");
        down1 = this.spriteSetup(spritePaths.getString("down1"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down2 = this.spriteSetup(spritePaths.getString("down2"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down3 = this.spriteSetup(spritePaths.getString("down3"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up1 = this.spriteSetup(spritePaths.getString("up1"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up2 = this.spriteSetup(spritePaths.getString("up2"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up3 = this.spriteSetup(spritePaths.getString("up3"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left1 = this.spriteSetup(spritePaths.getString("left1"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left2 = this.spriteSetup(spritePaths.getString("left2"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left3 = this.spriteSetup(spritePaths.getString("left3"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right1 = this.spriteSetup(spritePaths.getString("right1"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right2 = this.spriteSetup(spritePaths.getString("right2"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right3 = this.spriteSetup(spritePaths.getString("right3"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackUp = this.spriteSetup(spritePaths.getString("attackUp"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackDown = this.spriteSetup(spritePaths.getString("attackDown"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackLeft = this.spriteSetup(spritePaths.getString("attackLeft"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackRight = this.spriteSetup(spritePaths.getString("attackRight"), Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }

    // Setup enemy attack pattern from JSON file
    private void setupAttack(JSONObject json)
    {
        this.enemyStats.setWeapon(new BasicAttack(simulator, json.getString("weapon")));
    }

    @Override
    public void update()
    {
        if(this.onScreen() == false) return;
        this.setIsAttacking(collisionChecker.checkAggro(this, aggroRange) != -1);
        this.setAction();
        this.decreaseAttackCooldown();
        this.animateSprite();

        if(this.getIsAttacking() && this.canAttack())
        {
            this.startAttackCooldown();
            this.getStats().getWeapon().attack(
                new Coordinate(
                    this.getWorldCoordinateX()+getOriginPointX(), 
                    this.getWorldCoordinateY()+getOriginPointY()
                ),
                this.getPlayerCoordinate(),
                collisionChecker.checkAggro(this, aggroRange),
                this.getStats().calculateDamageMultiplier()
            );
        }
        if(this.getIsMoving() && !collisionChecker.checkTileWall(this)) this.moveEnemy();
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        if(this.onScreen() == false) return;
        if(this.getIsAttacking() || this.canAttack() == false) drawAttackSprite(graphics2D);
        else drawMovingSprite(graphics2D);

        this.drawHealthBar(graphics2D, hpBarOffset);
        this.getDamageText().drawDamageText(graphics2D, this.getScreenX(), this.getScreenY());
        if(this.getDebugConsole()) this.debugConsole(graphics2D, aggroRange);
    }

    @Override
    public boolean isDestroyed() 
    { 
        if(this.getStats().getCurrentHealth() <= 0)
        {
            simulator.getPlayerStats().addExp(expDrop);
            return true;
        }
        return false;
    }

    @Override
    public void damageEntity(int damage) 
    { 
        this.getDamageText().addDamageText(this.getStats().damageEntity(damage));
        generateParticle();
        
        if(this.getStats().getCurrentHealth() > 0) simulator.playSoundEffect(soundHitID); 
        else simulator.playSoundEffect(soundDeathID); 
    }

    @Override
    public void healEntity(int heal) { this.getStats().healEntity(heal); }

    // Set and draw image for moving sprite based on spriteNum
    public void drawMovingSprite(Graphics2D graphics2D)
    {
        BufferedImage image = null;

        switch(this.getMoveDirection())
        {
            case DOWNRIGHT:
            case DOWNLEFT:
            case DOWN:
                if(this.getSpriteNum() == 1) image = down2;
                if(this.getSpriteNum() == 2) image = down1;
                if(this.getSpriteNum() == 3) image = down2;
                if(this.getSpriteNum() == 4) image = down3;
                break;
            case UPRIGHT:
            case UPLEFT:
            case UP:
                if(this.getSpriteNum() == 1) image = up2;
                if(this.getSpriteNum() == 2) image = up1;
                if(this.getSpriteNum() == 3) image = up2;
                if(this.getSpriteNum() == 4) image = up3;
                break;
            case LEFT:
                if(this.getSpriteNum() == 1) image = left1;
                if(this.getSpriteNum() == 2) image = left2;
                if(this.getSpriteNum() == 3) image = left1;
                if(this.getSpriteNum() == 4) image = left3;
                break;
            case RIGHT:
                if(this.getSpriteNum() == 1) image = right1;
                if(this.getSpriteNum() == 2) image = right2;
                if(this.getSpriteNum() == 3) image = right1;
                if(this.getSpriteNum() == 4) image = right3;
                break;
        }
        graphics2D.drawImage(image, this.getScreenX(), this.getScreenY(), null);
    }

    // Set and draw image for attack sprite based on spriteNum
    public void drawAttackSprite(Graphics2D graphics2D)
    {
        BufferedImage image = null;

        switch(this.getMoveDirection())
        {
            case DOWNRIGHT:
            case DOWNLEFT:
            case DOWN:
                if(this.getSpriteNum() == 1) image = attackDown;
                if(this.getSpriteNum() == 2) image = down2;
                break;
            case UPRIGHT:
            case UPLEFT:
            case UP:
                if(this.getSpriteNum() == 1) image = attackUp;
                if(this.getSpriteNum() == 2) image = up2;
                break;
            case LEFT:
                if(this.getSpriteNum() == 1) image = attackLeft;
                if(this.getSpriteNum() == 2) image = left1;
                break;
            case RIGHT:
                if(this.getSpriteNum() == 1) image = attackRight;
                if(this.getSpriteNum() == 2) image = right1;
                break;
        }
        graphics2D.drawImage(image, this.getScreenX(), this.getScreenY(), null);
    }

    // Animate particle animation
    private void generateParticle()
    {
        Coordinate origin = new Coordinate(
            this.getWorldCoordinateX() + this.getOriginPointX(), 
            this.getWorldCoordinateY() + this.getOriginPointY()
        );
        simulator.particleHandler.addParticle(new Particle(simulator, origin, particleColor, -1, -1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, particleColor, 1, -1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, particleColor, -1, 1)); 
        simulator.particleHandler.addParticle(new Particle(simulator, origin, particleColor, 1, 1)); 
    }
}
