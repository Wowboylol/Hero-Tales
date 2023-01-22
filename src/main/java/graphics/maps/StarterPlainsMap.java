/*  
 *  StarterPlainsMap.java
 *  
 *  Description: A strategy which implements MapStrategy.
 *               Builds a starter plains map.
 *
*/

package graphics.maps;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Simulator;
import main.Utility;
import graphics.*;
import entities.Coordinate;
import entities.enemies.*;

public class StarterPlainsMap implements MapStrategy
{
    // Attributes
    public final int MAP_TILES = 73;
    public final int MAP_SIZE_TILE_X = 50;
    public final int MAP_SIZE_TILE_Y = 50;
    public final int MAP_SIZE_PIXEL_X = MAP_SIZE_TILE_X * Simulator.TILE_SIZE;
    public final int MAP_SIZE_PIXEL_Y = MAP_SIZE_TILE_Y * Simulator.TILE_SIZE;
    private Tile tileImages[];
    private int mapTileGrid[][];
    private MapLoader mapLoader;
    private Camera camera;
    private boolean enemiesLoaded = false;

    // Default constructor
    public StarterPlainsMap(Camera camera)
    {
        this.camera = camera;
        this.tileImages = new Tile[MAP_TILES];
        this.mapLoader = new MapLoader();
        this.mapTileGrid = this.mapLoader.loadMap("/maps/starter_plains.txt", MAP_SIZE_TILE_X, MAP_SIZE_TILE_Y);
        getTileImages();
    }

    @Override
    public void buildMap(Graphics2D graphics2D)
    {
        int mapColumn = 0;
        int mapRow = 0;

        while(mapColumn < MAP_SIZE_TILE_X && mapRow < MAP_SIZE_TILE_Y)
        {
            int tileIndex = mapTileGrid[mapColumn][mapRow];

            if(camera.isTileOnScreen(mapColumn, mapRow) || camera.isScreenAtMapEdge(MAP_SIZE_PIXEL_X, MAP_SIZE_PIXEL_Y))
            {
                graphics2D.drawImage(
                    tileImages[tileIndex].getImage(), 
                    camera.getTileScreenPositionX(mapColumn, MAP_SIZE_PIXEL_X), 
                    camera.getTileScreenPositionY(mapRow, MAP_SIZE_PIXEL_Y), 
                    null
                );
            }
            mapColumn++;

            if(mapColumn == MAP_SIZE_TILE_X)
            {
                mapColumn = 0;
                mapRow++;
            }
        }
        if(enemiesLoaded == false) 
        {
            buildEnemies();
            enemiesLoaded = true;
        }
    }

    // Build and load enemies
    public void buildEnemies()
    {
        Simulator simulator = Simulator.getInstance();
        int tileSize = Simulator.TILE_SIZE;
        String redMushroom = "/enemies/mobs/red_mushroom/red_mushroom.json";
        String blueMushroom = "/enemies/mobs/blue_mushroom/blue_mushroom.json";
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(43*tileSize, 40*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(43*tileSize, 45*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(35*tileSize, 34*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(45*tileSize, 27*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(37*tileSize, 18*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(37*tileSize, 4*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(23*tileSize, 20*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(31*tileSize, 22*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(3*tileSize, 27*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(10*tileSize, 30*tileSize), blueMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(9*tileSize, 18*tileSize), blueMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(15*tileSize, 17*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(8*tileSize, 9*tileSize), redMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(14*tileSize, 4*tileSize), blueMushroom));
        simulator.enemies.add(new BasicEnemy(simulator, simulator.collisionChecker, new Coordinate(17*tileSize, 12*tileSize), redMushroom));
    }

    // Getters
    @Override
    public int getMapWidth() { return this.MAP_SIZE_PIXEL_X; }

    @Override
    public int getMapHeight() { return this.MAP_SIZE_PIXEL_Y; }

    @Override
    public TileType getTileType(int col, int row) 
    { 
        if(col < 0 || row < 0 || col > MAP_SIZE_TILE_X-1 || row > MAP_SIZE_TILE_Y-1) return TileType.DEFAULT;
        int tileNum = this.mapTileGrid[col][row]; 
        return tileImages[tileNum].getType();
    }

    // Get tile images and set consecutive indexs of tile image array to the loaded images
    private void getTileImages()
    {
        imageSetup(0, "/tilesets/starter_plains/tiles/grass_1.png", TileType.DEFAULT);
        imageSetup(1, "/tilesets/starter_plains/tiles/grass_2.png", TileType.DEFAULT);
        imageSetup(2, "/tilesets/starter_plains/tiles/grass_3.png", TileType.DEFAULT);
        imageSetup(3, "/tilesets/starter_plains/tiles/grass_4.png", TileType.DEFAULT);
        imageSetup(4, "/tilesets/starter_plains/tiles/grass_5.png", TileType.DEFAULT);
        imageSetup(5, "/tilesets/starter_plains/tiles/path.png", TileType.PATH);
        imageSetup(6, "/tilesets/starter_plains/tiles/path_corner_1.png", TileType.PATH);
        imageSetup(7, "/tilesets/starter_plains/tiles/path_corner_2.png", TileType.PATH);
        imageSetup(8, "/tilesets/starter_plains/tiles/path_corner_3.png", TileType.PATH);
        imageSetup(9, "/tilesets/starter_plains/tiles/path_corner_4.png", TileType.PATH);
        imageSetup(10, "/tilesets/starter_plains/tiles/path_inner_1.png", TileType.PATH);
        imageSetup(11, "/tilesets/starter_plains/tiles/path_inner_2.png", TileType.PATH);
        imageSetup(12, "/tilesets/starter_plains/tiles/path_inner_3.png", TileType.PATH);
        imageSetup(13, "/tilesets/starter_plains/tiles/path_inner_4.png", TileType.PATH);
        imageSetup(14, "/tilesets/starter_plains/tiles/path_side_1.png", TileType.PATH);
        imageSetup(15, "/tilesets/starter_plains/tiles/path_side_2.png", TileType.PATH);
        imageSetup(16, "/tilesets/starter_plains/tiles/path_side_3.png", TileType.PATH);
        imageSetup(17, "/tilesets/starter_plains/tiles/path_side_4.png", TileType.PATH);
        imageSetup(18, "/tilesets/starter_plains/walls/wall.png", TileType.WALL);
        imageSetup(19, "/tilesets/starter_plains/walls/wall_corner_1.png", TileType.WALL);
        imageSetup(20, "/tilesets/starter_plains/walls/wall_corner_2.png", TileType.WALL);
        imageSetup(21, "/tilesets/starter_plains/walls/wall_corner_3.png", TileType.WALL);
        imageSetup(22, "/tilesets/starter_plains/walls/wall_corner_4.png", TileType.WALL);
        imageSetup(23, "/tilesets/starter_plains/walls/wall_inner_1.png", TileType.WALL);
        imageSetup(24, "/tilesets/starter_plains/walls/wall_inner_2.png", TileType.WALL);
        imageSetup(25, "/tilesets/starter_plains/walls/wall_inner_3.png", TileType.WALL);
        imageSetup(26, "/tilesets/starter_plains/walls/wall_inner_4.png", TileType.WALL);
        imageSetup(27, "/tilesets/starter_plains/walls/wall_side_1.png", TileType.WALL);
        imageSetup(28, "/tilesets/starter_plains/walls/wall_side_2.png", TileType.WALL);
        imageSetup(29, "/tilesets/starter_plains/walls/wall_side_3.png", TileType.WALL);
        imageSetup(30, "/tilesets/starter_plains/walls/wall_side_4.png", TileType.WALL);
        imageSetup(31, "/tilesets/starter_plains/walls/water.png", TileType.WALL);
        imageSetup(32, "/tilesets/starter_plains/walls/water_corner_1.png", TileType.WALL);
        imageSetup(33, "/tilesets/starter_plains/walls/water_corner_2.png", TileType.WALL);
        imageSetup(34, "/tilesets/starter_plains/walls/water_corner_3.png", TileType.WALL);
        imageSetup(35, "/tilesets/starter_plains/walls/water_corner_4.png", TileType.WALL);
        imageSetup(36, "/tilesets/starter_plains/walls/water_inner_1.png", TileType.WALL);
        imageSetup(37, "/tilesets/starter_plains/walls/water_inner_2.png", TileType.WALL);
        imageSetup(38, "/tilesets/starter_plains/walls/water_inner_3.png", TileType.WALL);
        imageSetup(39, "/tilesets/starter_plains/walls/water_inner_4.png", TileType.WALL);
        imageSetup(40, "/tilesets/starter_plains/walls/water_side_1.png", TileType.WALL);
        imageSetup(41, "/tilesets/starter_plains/walls/water_side_2.png", TileType.WALL);
        imageSetup(42, "/tilesets/starter_plains/walls/water_side_3.png", TileType.WALL);
        imageSetup(43, "/tilesets/starter_plains/walls/water_side_4.png", TileType.WALL);
        imageSetup(44, "/tilesets/starter_plains/tiles/flower_1.png", TileType.DEFAULT);
        imageSetup(45, "/tilesets/starter_plains/tiles/flower_2.png", TileType.DEFAULT);
        imageSetup(46, "/tilesets/starter_plains/tiles/flower_3.png", TileType.DEFAULT);
        imageSetup(47, "/tilesets/starter_plains/tiles/grass_6.png", TileType.DEFAULT);
        imageSetup(48, "/tilesets/starter_plains/tiles/grass_7.png", TileType.DEFAULT);
        imageSetup(49, "/tilesets/starter_plains/tiles/grass_8.png", TileType.DEFAULT);
        imageSetup(50, "/tilesets/starter_plains/tiles/grass_9.png", TileType.DEFAULT);
        imageSetup(51, "/tilesets/starter_plains/tiles/mushroom_1.png", TileType.DEFAULT);
        imageSetup(52, "/tilesets/starter_plains/tiles/mushroom_2.png", TileType.DEFAULT);
        imageSetup(53, "/tilesets/starter_plains/tiles/mushroom_3.png", TileType.DEFAULT);
        imageSetup(54, "/tilesets/starter_plains/tiles/mushroom_4.png", TileType.DEFAULT);
        imageSetup(55, "/tilesets/starter_plains/walls/water_lily_1.png", TileType.WALL);
        imageSetup(56, "/tilesets/starter_plains/walls/water_lily_2.png", TileType.WALL);
        imageSetup(57, "/tilesets/starter_plains/walls/water_lily_3.png", TileType.WALL);
        imageSetup(58, "/tilesets/starter_plains/walls/water_lily_4.png", TileType.WALL);
        imageSetup(59, "/tilesets/starter_plains/walls/water_lily_5.png", TileType.WALL);
        imageSetup(60, "/tilesets/starter_plains/walls/fence.png", TileType.WALL);
        imageSetup(61, "/tilesets/starter_plains/walls/fence_double_1.png", TileType.WALL);
        imageSetup(62, "/tilesets/starter_plains/walls/fence_double_2.png", TileType.WALL);
        imageSetup(63, "/tilesets/starter_plains/walls/fence_double_3.png", TileType.WALL);
        imageSetup(64, "/tilesets/starter_plains/walls/fence_double_4.png", TileType.WALL);
        imageSetup(65, "/tilesets/starter_plains/walls/fence_horizontal.png", TileType.WALL);
        imageSetup(66, "/tilesets/starter_plains/walls/fence_single_1.png", TileType.WALL);
        imageSetup(67, "/tilesets/starter_plains/walls/fence_single_2.png", TileType.WALL);
        imageSetup(68, "/tilesets/starter_plains/walls/fence_single_3.png", TileType.WALL);
        imageSetup(69, "/tilesets/starter_plains/walls/fence_single_4.png", TileType.WALL);
        imageSetup(70, "/tilesets/starter_plains/walls/fence_vertical.png", TileType.WALL);
        imageSetup(71, "/tilesets/starter_plains/tiles/path_diagonal_1.png", TileType.PATH);
        imageSetup(72, "/tilesets/starter_plains/tiles/path_diagonal_2.png", TileType.PATH);
    }

    // Sets up the images for the tiles by resizing them to the correct size
    private void imageSetup(int index, String imagePath, TileType tileType) 
    {
        try {
            tileImages[index] = new Tile(ImageIO.read(getClass().getResourceAsStream(imagePath)), tileType);
            tileImages[index].setImage(Utility.resizeImage(tileImages[index].getImage(), Simulator.TILE_SIZE, Simulator.TILE_SIZE));
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
