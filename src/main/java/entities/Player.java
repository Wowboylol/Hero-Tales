/*  
 *  Player.java
 *  
 *  Description: Controls player movement and stats.
 *
*/

package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Map.Entry;

import main.*;
import entities.enums.MoveDirection;
import entities.stats.PlayerStats;

public class Player extends AnimateEntity implements Damageable
{
    // Configurations
    public static final Coordinate PLAYER_SPAWN_POSITION = new Coordinate(Simulator.TILE_SIZE*6, Simulator.TILE_SIZE*42);
    public static final int PLAYER_SCREEN_X = Simulator.SCREEN_WIDTH/2 - (Simulator.TILE_SIZE/2);
    public static final int PLAYER_SCREEN_Y = Simulator.SCREEN_HEIGHT/2 - (Simulator.TILE_SIZE/2);
    public final Rectangle HITBOX_CONFIGURATIONS = new Rectangle(12, 12, 24, 30);
    public final int MOVE_ANIMATION_SPEED = 14;
    public final int HP_BAR_OFFSET = 55;

    // Sounds
    public final int SOUND_HIT_ID = 1;
    public final int SOUND_DEATH_ID = 2;

    // Attributes
    private Simulator simulator;
    private Keyboard keyboard;
    private Mouse mouse;
    private CollisionChecker collisionChecker;

    // Default constructor (starting coordinate based on defaults)
    public Player(Simulator simulator)
    {
        super(PLAYER_SPAWN_POSITION);
        this.simulator = simulator;
        loadSprite();
        
        // Super class overriding
        this.setStats(new PlayerStats(simulator));
        this.setHitbox(HITBOX_CONFIGURATIONS);
        this.setMoveAnimationSpeed(MOVE_ANIMATION_SPEED);
    }

    // External injector injects required dependencies
    public void inject(Keyboard keyboard, Mouse mouse, CollisionChecker collisionCheck)
    {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.collisionChecker = collisionCheck;
    }

    @Override
    public void update()
    {
        actionStateSetter();
        changeDirection();
        this.decreaseAttackCooldown();
        this.getStats().regenerateHealth();
        this.animateSprite();
        
        if(this.getIsAttacking() && this.canAttack()) 
        {
            this.startAttackCooldown();
            this.getStats().getWeapon().attack(
                new Coordinate(
                    this.getWorldCoordinateX()+getOriginPointX(), 
                    this.getWorldCoordinateY()+getOriginPointY()
                ),
                this.getWorldCoordinate(),
                this.mouse.getAttackAngle(
                    playerScreenPositionX()+getOriginPointX(), 
                    playerScreenPositionY()+getOriginPointY()
                )
            );
        }
        if(this.getIsMoving() && !collisionChecker.checkTileWall(this)) movePlayer(collisionChecker.checkTilePath(this));
    }

    // Set player direction based on which directional (WASD) key is pressed
    public void changeDirection()
    {
        if(keyboard.getDirection(MoveDirection.UPLEFT) == true) this.setMoveDirection(MoveDirection.UPLEFT);
        else if(keyboard.getDirection(MoveDirection.UPRIGHT) == true) this.setMoveDirection(MoveDirection.UPRIGHT);
        else if(keyboard.getDirection(MoveDirection.DOWNLEFT) == true) this.setMoveDirection(MoveDirection.DOWNLEFT);
        else if(keyboard.getDirection(MoveDirection.DOWNRIGHT) == true) this.setMoveDirection(MoveDirection.DOWNRIGHT);
        else if(keyboard.getDirection(MoveDirection.LEFT) == true) this.setMoveDirection(MoveDirection.LEFT);
        else if(keyboard.getDirection(MoveDirection.RIGHT) == true) this.setMoveDirection(MoveDirection.RIGHT);
        else if(keyboard.getDirection(MoveDirection.UP) == true) this.setMoveDirection(MoveDirection.UP);
        else if(keyboard.getDirection(MoveDirection.DOWN) == true) this.setMoveDirection(MoveDirection.DOWN);
    }

    // Move player based on which directional (WASD) key is pressed, increasing movespeed if player is on a path
    public void movePlayer(boolean isOnPath)
    {
        int velocity = this.getStats().getSpeed();
        if(isOnPath) velocity++;

        if(keyboard.getDirection(MoveDirection.LEFT))
        {
            this.setWorldCoordinateX(getWorldCoordinateX() - velocity);
        }
        if(keyboard.getDirection(MoveDirection.RIGHT))
        {
            this.setWorldCoordinateX(getWorldCoordinateX() + velocity);
        }
        if(keyboard.getDirection(MoveDirection.UP))
        {
            this.setWorldCoordinateY(getWorldCoordinateY() - velocity);
        }
        if(keyboard.getDirection(MoveDirection.DOWN))
        {
            this.setWorldCoordinateY(getWorldCoordinateY() + velocity);
        }
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        if(this.getIsAttacking() || this.canAttack() == false) drawAttackSprite(graphics2D);
        else drawMovingSprite(graphics2D);

        drawHealthBar(graphics2D, HP_BAR_OFFSET);
        drawDamageText(graphics2D);

        // DEBUG
        if(keyboard.getDebugConsole()) debugConsole(graphics2D);
    }

    @Override
    public boolean isDestroyed() { return false; }

    @Override
    public void damageEntity(int damage) 
    { 
        this.addDamageText(this.getStats().damageEntity(damage));

        if(this.getStats().getCurrentHealth() > 0) simulator.playSoundEffect(SOUND_HIT_ID); 
        else simulator.playSoundEffect(SOUND_DEATH_ID); 
    }

    @Override
    public void healEntity(int heal) { this.getStats().healEntity(heal); }

    // Set and draw image for attack sprite based on spriteNum
    public void drawAttackSprite(Graphics2D graphics2D)
    {
        BufferedImage image = null;
        int drawPositionX = playerScreenPositionX();
        int drawPositionY = playerScreenPositionY();

        switch(mouse.getAttackDirection(playerScreenPositionX()+getOriginPointX(), playerScreenPositionY()+getOriginPointY()))
        {
            case UP:
                if(this.getSpriteNum()%2 != 0) {
                    drawPositionY = playerScreenPositionY() - Simulator.TILE_SIZE;
                    image = attackUp;
                }
                else image = up1;
                this.setMoveDirection(MoveDirection.UP);
                break;
            case DOWN:
                if(this.getSpriteNum()%2 != 0) image = attackDown;
                else image = down1;
                this.setMoveDirection(MoveDirection.DOWN);
                break;
            case LEFT:
                if(this.getSpriteNum()%2 != 0) {
                    drawPositionX = playerScreenPositionX() - Simulator.TILE_SIZE;
                    image = attackLeft;
                }
                else image = left1;
                this.setMoveDirection(MoveDirection.LEFT);
                break;
            case RIGHT:
                if(this.getSpriteNum()%2 != 0) image = attackRight;
                else image = right1;
                this.setMoveDirection(MoveDirection.RIGHT);
                break;
        }
        graphics2D.drawImage(image, drawPositionX, drawPositionY, null);
    }

    // Set and draw image for moving sprite based on spriteNum
    public void drawMovingSprite(Graphics2D graphics2D)
    {
        BufferedImage image = null;

        switch(this.getMoveDirection())
        {
            case UPRIGHT:
            case UPLEFT:
            case UP:
                if(this.getSpriteNum() == 1) image = up1;
                if(this.getSpriteNum() == 2) image = up2;
                if(this.getSpriteNum() == 3) image = up1;
                if(this.getSpriteNum() == 4) image = up3;
                break;
            case DOWNRIGHT:
            case DOWNLEFT:
            case DOWN:
                if(this.getSpriteNum() == 1) image = down1;
                if(this.getSpriteNum() == 2) image = down2;
                if(this.getSpriteNum() == 3) image = down1;
                if(this.getSpriteNum() == 4) image = down3;
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
        graphics2D.drawImage(image, playerScreenPositionX(), playerScreenPositionY(), null);
    }

    // Draw health bar
    public void drawHealthBar(Graphics2D graphics2d, int yOffset)
    {
        double healthPercentage = (double)this.getStats().getCurrentHealth() / (double)this.getStats().getMaxHealth();

        graphics2d.setColor(new Color(35, 35, 35));
        graphics2d.fillRect(playerScreenPositionX()-1, playerScreenPositionY()+yOffset-1, Simulator.TILE_SIZE+2, 8);

        if(healthPercentage > 0.5) graphics2d.setColor(new Color(21, 183, 11));
        else if(healthPercentage > 0.25) graphics2d.setColor(new Color(238, 119, 7));
        else graphics2d.setColor(new Color(211, 59, 50));

        graphics2d.fillRect(playerScreenPositionX(), playerScreenPositionY()+yOffset, (int)(Simulator.TILE_SIZE*healthPercentage), 6);
    }

    // Draw damage text
    public void drawDamageText(Graphics2D graphics2d)
    {
        ArrayList<Entry<String, Integer>> damageText = this.getDamageText();
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
                    playerScreenPositionX()+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-1, 
                    playerScreenPositionY()+damageOffset+1
                );
                graphics2d.setColor(new Color(240, 52, 24));
                graphics2d.drawString(
                    text, 
                    playerScreenPositionX()+(Simulator.TILE_SIZE/2-metrics.stringWidth(text)/2)-2, 
                    playerScreenPositionY()+damageOffset
                );
            }
        }
    }

    // Load player sprites into BufferedImage
    public void loadSprite()
    {
        up1 = spriteSetup("player_up_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up2 = spriteSetup("player_up_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        up3 = spriteSetup("player_up_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down1 = spriteSetup("player_down_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down2 = spriteSetup("player_down_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        down3 = spriteSetup("player_down_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left1 = spriteSetup("player_left_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left2 = spriteSetup("player_left_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        left3 = spriteSetup("player_left_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right1 = spriteSetup("player_right_1", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right2 = spriteSetup("player_right_2", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        right3 = spriteSetup("player_right_3", Simulator.TILE_SIZE, Simulator.TILE_SIZE);
        attackUp = spriteSetup("player_attack_up", Simulator.TILE_SIZE, Simulator.TILE_SIZE*2);
        attackDown = spriteSetup("player_attack_down", Simulator.TILE_SIZE, Simulator.TILE_SIZE*2);
        attackLeft = spriteSetup("player_attack_left", Simulator.TILE_SIZE*2, Simulator.TILE_SIZE);
        attackRight = spriteSetup("player_attack_right", Simulator.TILE_SIZE*2, Simulator.TILE_SIZE);
    }

    // Draw debug information
    public void debugConsole(Graphics2D graphics2D)
    {
        Rectangle hitbox = this.getHitbox();
        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(
            this.getDefaultHitboxX() + playerScreenPositionX(), 
            this.getDefaultHitboxY() + playerScreenPositionY(), 
            hitbox.width, 
            hitbox.height
        );
    }

    // Determines player horizontal screen position
    public int playerScreenPositionX()
    {
        int x = PLAYER_SCREEN_X;
        int rightOffset = Simulator.SCREEN_WIDTH - PLAYER_SCREEN_X;

        if(PLAYER_SCREEN_X > this.getWorldCoordinateX()) x = this.getWorldCoordinateX();
        if(rightOffset > simulator.getMapWidth() - this.getWorldCoordinateX())
        {
            x = Simulator.SCREEN_WIDTH - (simulator.getMapWidth() - this.getWorldCoordinateX());
        }
        return x;
    }

    // Determines player vertical screen position
    public int playerScreenPositionY()
    {
        int y = PLAYER_SCREEN_Y;
        int bottomOffset = Simulator.SCREEN_HEIGHT - PLAYER_SCREEN_Y;

        if(PLAYER_SCREEN_Y > this.getWorldCoordinateY()) y = this.getWorldCoordinateY();
        if(bottomOffset > simulator.getMapHeight() - this.getWorldCoordinateY()) 
        {
            y = Simulator.SCREEN_HEIGHT - (simulator.getMapHeight() - this.getWorldCoordinateY());
        }
        return y;
    }

    // Helper function: Sets up player sprites by resizing image from file
    private BufferedImage spriteSetup(String imageName, int width, int height)
    {
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = Utility.resizeImage(image, width, height);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    // Helper function: Sets player action state
    private void actionStateSetter()
    {
        this.setIsAttacking(mouse.getMousePressed());
        this.setIsMoving(keyboard.isWASDPressed());
    }
}
