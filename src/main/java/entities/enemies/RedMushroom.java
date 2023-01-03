
/*  
 *  RedMushroom.java
 *  
 *  Description: Placeholder.
 *
*/

package entities.enemies;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Simulator;
import main.CollisionChecker;
import entities.*;
import entities.stats.EnemyStats;
import items.Wieldable;
import items.enemyattacks.RedMushroomAttack;

public class RedMushroom extends Enemy implements Damageable
{
    // Configurations
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(3, 9, 42, 36);
    public final EnemyStats ENEMY_STATS = new EnemyStats(200, 0, 0, 0, 2);
    public final int MOVE_ANIMATION_SPEED = 12;
    public final int HP_BAR_OFFSET = 55;
    public final int AGGRO_RANGE = 240;

    // Attributes
    private CollisionChecker collisionChecker;
    private Wieldable attack;

    // Default constructor
    public RedMushroom(Simulator simulator, CollisionChecker collisionChecker, Coordinate spawnLocation)
    {
        super(spawnLocation, simulator);
        this.collisionChecker = collisionChecker;
        this.attack = new RedMushroomAttack(simulator);
        loadSprite();

        // Super class overloading
        this.setStats(ENEMY_STATS);
        this.setHitbox(HITBOX_CONFIGURATIONS);
        this.setMoveAnimationSpeed(MOVE_ANIMATION_SPEED);
    }

    @Override
    public void update()
    {
        if(this.onScreen() == false) return;
        this.setAction();
        this.decreaseAttackCooldown();
        this.animateSprite();

        int aggroAngle = collisionChecker.checkAggro(this, AGGRO_RANGE);
        if(aggroAngle != -1 && this.canAttack())
        {
            this.startAttackCooldown();
            this.attack.attack(
                new Coordinate(
                    this.getWorldCoordinateX()+getOriginPointX(), 
                    this.getWorldCoordinateY()+getOriginPointY()
                ),
                this.getPlayerCoordinate(),
                aggroAngle
            );
        }
        if(this.getIsMoving() && !collisionChecker.checkTileWall(this)) this.moveEnemy();
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        if(this.onScreen() == false) return;
        drawMovingSprite(graphics2D);
        this.drawHealthBar(graphics2D, HP_BAR_OFFSET);
        this.drawDamageText(graphics2D);
        if(this.getDebugConsole()) this.debugConsole(graphics2D, AGGRO_RANGE);
    }

    @Override
    public boolean isDestroyed() { return this.getStats().getCurrentHealth() <= 0; }

    @Override
    public void damageEntity(int damage) { this.addDamageText(this.getStats().damageEntity(damage)); }

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

    // Load player sprites into BufferedImage
    public void loadSprite()
    {
        down1 = this.spriteSetup("walk_down_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down2 = this.spriteSetup("walk_down_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down3 = this.spriteSetup("walk_down_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up1 = this.spriteSetup("walk_up_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up2 = this.spriteSetup("walk_up_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up3 = this.spriteSetup("walk_up_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left1 = this.spriteSetup("walk_left_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left2 = this.spriteSetup("walk_left_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left3 = this.spriteSetup("walk_left_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right1 = this.spriteSetup("walk_right_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right2 = this.spriteSetup("walk_right_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right3 = this.spriteSetup("walk_right_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
    }
}
