/*  
 *  CollisionChecker.java
 *  
 *  Description: Checks collision between entities and tiles.
 *
*/

package main;
import entities.AnimateEntity;
import graphics.MapHandler;

public class CollisionChecker 
{
    // Attributes
    private MapHandler mapHandler;

    // Default constructor
    public CollisionChecker(Simulator simulator) {}

    // Inject dependencies
    public void inject(MapHandler mapHandler)
    {
        this.mapHandler = mapHandler;
    }

    // Checks collision between animateEntity and tile
    public boolean checkTileCollision(AnimateEntity entity)
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

        switch(entity.getDirection())
        {
            case UP:
                entityTopRow = (entityTopWorldY - entity.getMoveSpeed())/Simulator.TILE_SIZE;
                tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityTopRow);
                tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityTopRow);
                if(tileCollisionA == true || tileCollisionB == true) { return true; }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.getMoveSpeed())/Simulator.TILE_SIZE;
                tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityBottomRow);
                tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityBottomRow);
                if(tileCollisionA == true || tileCollisionB == true) { return true; }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.getMoveSpeed())/Simulator.TILE_SIZE;
                tileCollisionA = mapHandler.getTileCollision(entityLeftCol, entityTopRow);
                tileCollisionB = mapHandler.getTileCollision(entityLeftCol, entityBottomRow);
                if(tileCollisionA == true || tileCollisionB == true) { return true; }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.getMoveSpeed())/Simulator.TILE_SIZE;
                tileCollisionA = mapHandler.getTileCollision(entityRightCol, entityTopRow);
                tileCollisionB = mapHandler.getTileCollision(entityRightCol, entityBottomRow);
                if(tileCollisionA == true || tileCollisionB == true) { return true; }
                break;
        }
        return false;
    }
}
