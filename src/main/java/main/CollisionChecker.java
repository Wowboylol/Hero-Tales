/*  
 *  CollisionChecker.java
 *  
 *  Description: Checks collision between entities and tiles.
 *
*/

package main;
import entities.*;
import entities.enums.*;
import entities.projectiles.Projectile;
import entities.enemies.*;
import graphics.MapHandler;
import graphics.TileType;

public class CollisionChecker 
{
    // Attributes
    private Simulator simulator;
    private MapHandler mapHandler;

    // Default constructor
    public CollisionChecker(Simulator simulator) 
    {
        this.simulator = simulator;
    }

    // Inject dependencies
    public void inject(MapHandler mapHandler)
    {
        this.mapHandler = mapHandler;
    }

    // Checks if tile is a wall
    public boolean checkTileWall(AnimateEntity entity)
    {
        int entityLeftWorldX = entity.getWorldCoordinateX() + entity.getHitbox().x;
        int entityRightWorldX = entity.getWorldCoordinateX() + entity.getHitbox().x + entity.getHitbox().width;
        int entityTopWorldY = entity.getWorldCoordinateY() + entity.getHitbox().y;
        int entityBottomWorldY = entity.getWorldCoordinateY() + entity.getHitbox().y + entity.getHitbox().height;

        int entityLeftCol = entityLeftWorldX/Simulator.TILE_SIZE;
        int entityRightCol = entityRightWorldX/Simulator.TILE_SIZE;
        int entityTopRow = entityTopWorldY/Simulator.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/Simulator.TILE_SIZE;
        boolean tileCollisionA, tileCollisionB;
        MoveDirection direction = entity.getMoveDirection();

        if(direction == MoveDirection.UP || direction == MoveDirection.UPLEFT || direction == MoveDirection.UPRIGHT)
        {
            entityTopRow = (entityTopWorldY - entity.getStats().getSpeed())/Simulator.TILE_SIZE;
            tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityTopRow);
            tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityTopRow);
            if(tileCollisionA == true || tileCollisionB == true) { return true; }
        }
        if(direction == MoveDirection.DOWN || direction == MoveDirection.DOWNLEFT || direction == MoveDirection.DOWNRIGHT)
        {
            entityBottomRow = (entityBottomWorldY + entity.getStats().getSpeed())/Simulator.TILE_SIZE;
            tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityBottomRow);
            tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityBottomRow);
            if(tileCollisionA == true || tileCollisionB == true) { return true; }
        }
        if(direction == MoveDirection.LEFT || direction == MoveDirection.UPLEFT || direction == MoveDirection.DOWNLEFT)
        {
            entityLeftCol = (entityLeftWorldX - entity.getStats().getSpeed())/Simulator.TILE_SIZE;
            tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityTopRow);
            tileCollisionB = mapHandler.getTileCollision(entityLeftCol, entityBottomRow);
            if(tileCollisionA == true || tileCollisionB == true) { return true; }
        }
        if(direction == MoveDirection.RIGHT || direction == MoveDirection.UPRIGHT || direction == MoveDirection.DOWNRIGHT)
        {
            entityRightCol = (entityRightWorldX + entity.getStats().getSpeed())/Simulator.TILE_SIZE;
            tileCollisionA = mapHandler.getTileCollision(entityRightCol, entityTopRow);
            tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityBottomRow);
            if(tileCollisionA == true || tileCollisionB == true) { return true; }
        }
        return false;
    }

    public boolean checkTileWall(Projectile projectile, int angle)
    {
        if(angle > 360) { angle = angle % 360; }
        else if(angle < 0) { angle = 360 + angle; }
        try {
            if(angle >= 0 && angle < 90)
            {
                int projectileRightWorldX = projectile.getWorldCoordinateX() + projectile.getHitbox().x + projectile.getHitbox().width;
                int projectileBottomWorldY = projectile.getWorldCoordinateY() + projectile.getHitbox().y + projectile.getHitbox().height;
                int projectileRightCol = (projectileRightWorldX - (int)projectile.getXVelocity())/Simulator.TILE_SIZE;
                int projectileBottomRow = (projectileBottomWorldY - (int)projectile.getYVelocity())/Simulator.TILE_SIZE;
                if(mapHandler.getTileCollision(projectileRightCol, projectileBottomRow) == true) { return true; }
            }
            else if(angle >= 90 && angle < 180)
            {
                int projectileLeftWorldX = projectile.getWorldCoordinateX() + projectile.getHitbox().x;
                int projectileBottomWorldY = projectile.getWorldCoordinateY() + projectile.getHitbox().y + projectile.getHitbox().height;
                int projectileLeftCol = (projectileLeftWorldX + (int)projectile.getXVelocity())/Simulator.TILE_SIZE;
                int projectileBottomRow = (projectileBottomWorldY - (int)projectile.getYVelocity())/Simulator.TILE_SIZE;
                if(mapHandler.getTileCollision(projectileLeftCol, projectileBottomRow) == true) { return true; }
            }
            else if(angle >= 180 && angle < 270)
            {
                int projectileLeftWorldX = projectile.getWorldCoordinateX() + projectile.getHitbox().x;
                int projectileTopWorldY = projectile.getWorldCoordinateY() + projectile.getHitbox().y;
                int projectileLeftCol = (projectileLeftWorldX + (int)projectile.getXVelocity())/Simulator.TILE_SIZE;
                int projectileTopRow = (projectileTopWorldY + (int)projectile.getYVelocity())/Simulator.TILE_SIZE;
                if(mapHandler.getTileCollision(projectileLeftCol, projectileTopRow) == true) { return true; }
            }
            else if(angle >= 270 && angle <= 360)
            {
                int projectileRightWorldX = projectile.getWorldCoordinateX() + projectile.getHitbox().x + projectile.getHitbox().width;
                int projectileTopWorldY = projectile.getWorldCoordinateY() + projectile.getHitbox().y;
                int projectileRightCol = (projectileRightWorldX - (int)projectile.getXVelocity())/Simulator.TILE_SIZE;
                int projectileTopRow = (projectileTopWorldY + (int)projectile.getYVelocity())/Simulator.TILE_SIZE;
                if(mapHandler.getTileCollision(projectileRightCol, projectileTopRow) == true) { return true; }
            }
            else throw new IllegalArgumentException("Angle must be between 0 and 360");
        }
        catch(Exception e) { 
            e.printStackTrace();
            System.out.println("Angle: " + angle);
            return true; 
        }
        return false;
    }

    // Checks if collided tile is a path
    public boolean checkTilePath(AnimateEntity entity)
    {
        int entityOriginCol = (entity.getWorldCoordinateX() + entity.getOriginPointX())/Simulator.TILE_SIZE;
        int entityOriginRow = (entity.getWorldCoordinateY() + entity.getOriginPointY())/Simulator.TILE_SIZE;
        return (mapHandler.getTileType(entityOriginCol, entityOriginRow) == TileType.PATH);
    }

    // Check if projectile collides with entity
    public boolean checkProjectileCollision(Projectile projectile)
    {
        boolean collision = false;
        projectile.getHitbox().x = projectile.getWorldCoordinateX() + projectile.getHitbox().x;
        projectile.getHitbox().y = projectile.getWorldCoordinateY() + projectile.getHitbox().y;

        if(projectile.getUser() == EntityType.PLAYER)
        {
            for(int i=0; i<simulator.enemies.size(); i++)
            {
                Damageable enemy = simulator.enemies.get(i);
                enemy.getHitbox().x = enemy.getWorldCoordinateX() + enemy.getHitbox().x;
                enemy.getHitbox().y = enemy.getWorldCoordinateY() + enemy.getHitbox().y;

                if(projectile.getHitbox().intersects(enemy.getHitbox()))
                {
                    if(projectile.getPierce())
                    {
                        if(enemy.isOnHitList(projectile) == false)
                        {
                            enemy.damageEntity(projectile.getDamage());
                            enemy.addToHitList(projectile);
                        }
                    }
                    else
                    {
                        collision = true;
                        enemy.damageEntity(projectile.getDamage());
                    }
                }
                enemy.getHitbox().x = enemy.getDefaultHitboxX();
                enemy.getHitbox().y = enemy.getDefaultHitboxY();
            }
        }
        else
        {
            Damageable player = simulator.player;
            player.getHitbox().x = player.getWorldCoordinateX() + player.getHitbox().x;
            player.getHitbox().y = player.getWorldCoordinateY() + player.getHitbox().y;

            if(projectile.getHitbox().intersects(player.getHitbox()))
            {
                collision = true;
                player.damageEntity(projectile.getDamage());
            }
            player.getHitbox().x = player.getDefaultHitboxX();
            player.getHitbox().y = player.getDefaultHitboxY();
        }
        projectile.getHitbox().x = projectile.getDefaultHitboxX();
        projectile.getHitbox().y = projectile.getDefaultHitboxY();
        return collision;
    }

    // Check if given enemy is in aggro range of player, and returns angle to the player if true
    public int checkAggro(Enemy enemy, double aggroRange)
    {
        int enemyOriginX = (enemy.getWorldCoordinateX() + enemy.getOriginPointX());
        int enemyOriginY = (enemy.getWorldCoordinateY() + enemy.getOriginPointY());
        int playerOriginX = (simulator.player.getWorldCoordinateX() + simulator.player.getOriginPointX());
        int playerOriginY = (simulator.player.getWorldCoordinateY() + simulator.player.getOriginPointY());
        int distanceY = Math.abs((enemyOriginY - playerOriginY));
        int distanceX = Math.abs((enemyOriginX - playerOriginX));

        if(Math.hypot(distanceY, distanceX) <= aggroRange) return Utility.calculateAngle(enemyOriginX, enemyOriginY, playerOriginX, playerOriginY); 
        return -1;
    }
}
