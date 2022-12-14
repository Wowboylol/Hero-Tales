/*  
 *  CollisionChecker.java
 *  
 *  Description: Checks collision between entities and tiles.
 *
*/

package main;
import entities.AnimateEntity;
import entities.enums.MoveDirection;
import graphics.MapHandler;
import graphics.TileType;

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

    // Checks if collided tile is a path
    public boolean checkTilePath(AnimateEntity entity)
    {
        int entityOriginCol = (entity.getWorldCoordinateX() + entity.getOriginPointX())/Simulator.TILE_SIZE;
        int entityOriginRow = (entity.getWorldCoordinateY() + entity.getOriginPointY())/Simulator.TILE_SIZE;
        return (mapHandler.getTileType(entityOriginCol, entityOriginRow) == TileType.PATH) ? true : false;
    }
}
