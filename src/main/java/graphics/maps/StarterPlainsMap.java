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
import graphics.Tile;
import graphics.Camera;

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
    }

    // Getters
    @Override
    public int getMapWidth() { return this.MAP_SIZE_PIXEL_X; }

    @Override
    public int getMapHeight() { return this.MAP_SIZE_PIXEL_Y; }

    @Override
    public boolean getTileCollision(int col, int row) 
    { 
        if(col < 0 || row < 0 || col > MAP_SIZE_TILE_X-1 || row > MAP_SIZE_TILE_Y-1) return false;
        int tileNum = this.mapTileGrid[col][row]; 
        return tileImages[tileNum].getCanCollide();
    }

    // Get tile images and set consecutive indexs of tile image array to the loaded images
    private void getTileImages()
    {
        imageSetup(0, "/tilesets/starter_plains/tiles/grass_1.png", false);
        imageSetup(1, "/tilesets/starter_plains/tiles/grass_2.png", false);
        imageSetup(2, "/tilesets/starter_plains/tiles/grass_3.png", false);
        imageSetup(3, "/tilesets/starter_plains/tiles/grass_4.png", false);
        imageSetup(4, "/tilesets/starter_plains/tiles/grass_5.png", false);
        imageSetup(5, "/tilesets/starter_plains/tiles/path.png", false);
        imageSetup(6, "/tilesets/starter_plains/tiles/path_corner_1.png", false);
        imageSetup(7, "/tilesets/starter_plains/tiles/path_corner_2.png", false);
        imageSetup(8, "/tilesets/starter_plains/tiles/path_corner_3.png", false);
        imageSetup(9, "/tilesets/starter_plains/tiles/path_corner_4.png", false);
        imageSetup(10, "/tilesets/starter_plains/tiles/path_inner_1.png", false);
        imageSetup(11, "/tilesets/starter_plains/tiles/path_inner_2.png", false);
        imageSetup(12, "/tilesets/starter_plains/tiles/path_inner_3.png", false);
        imageSetup(13, "/tilesets/starter_plains/tiles/path_inner_4.png", false);
        imageSetup(14, "/tilesets/starter_plains/tiles/path_side_1.png", false);
        imageSetup(15, "/tilesets/starter_plains/tiles/path_side_2.png", false);
        imageSetup(16, "/tilesets/starter_plains/tiles/path_side_3.png", false);
        imageSetup(17, "/tilesets/starter_plains/tiles/path_side_4.png", false);
        imageSetup(18, "/tilesets/starter_plains/walls/wall.png", true);
        imageSetup(19, "/tilesets/starter_plains/walls/wall_corner_1.png", true);
        imageSetup(20, "/tilesets/starter_plains/walls/wall_corner_2.png", true);
        imageSetup(21, "/tilesets/starter_plains/walls/wall_corner_3.png", true);
        imageSetup(22, "/tilesets/starter_plains/walls/wall_corner_4.png", true);
        imageSetup(23, "/tilesets/starter_plains/walls/wall_inner_1.png", true);
        imageSetup(24, "/tilesets/starter_plains/walls/wall_inner_2.png", true);
        imageSetup(25, "/tilesets/starter_plains/walls/wall_inner_3.png", true);
        imageSetup(26, "/tilesets/starter_plains/walls/wall_inner_4.png", true);
        imageSetup(27, "/tilesets/starter_plains/walls/wall_side_1.png", true);
        imageSetup(28, "/tilesets/starter_plains/walls/wall_side_2.png", true);
        imageSetup(29, "/tilesets/starter_plains/walls/wall_side_3.png", true);
        imageSetup(30, "/tilesets/starter_plains/walls/wall_side_4.png", true);
        imageSetup(31, "/tilesets/starter_plains/walls/water.png", true);
        imageSetup(32, "/tilesets/starter_plains/walls/water_corner_1.png", true);
        imageSetup(33, "/tilesets/starter_plains/walls/water_corner_2.png", true);
        imageSetup(34, "/tilesets/starter_plains/walls/water_corner_3.png", true);
        imageSetup(35, "/tilesets/starter_plains/walls/water_corner_4.png", true);
        imageSetup(36, "/tilesets/starter_plains/walls/water_inner_1.png", true);
        imageSetup(37, "/tilesets/starter_plains/walls/water_inner_2.png", true);
        imageSetup(38, "/tilesets/starter_plains/walls/water_inner_3.png", true);
        imageSetup(39, "/tilesets/starter_plains/walls/water_inner_4.png", true);
        imageSetup(40, "/tilesets/starter_plains/walls/water_side_1.png", true);
        imageSetup(41, "/tilesets/starter_plains/walls/water_side_2.png", true);
        imageSetup(42, "/tilesets/starter_plains/walls/water_side_3.png", true);
        imageSetup(43, "/tilesets/starter_plains/walls/water_side_4.png", true);
        imageSetup(44, "/tilesets/starter_plains/tiles/flower_1.png", false);
        imageSetup(45, "/tilesets/starter_plains/tiles/flower_2.png", false);
        imageSetup(46, "/tilesets/starter_plains/tiles/flower_3.png", false);
        imageSetup(47, "/tilesets/starter_plains/tiles/grass_6.png", false);
        imageSetup(48, "/tilesets/starter_plains/tiles/grass_7.png", false);
        imageSetup(49, "/tilesets/starter_plains/tiles/grass_8.png", false);
        imageSetup(50, "/tilesets/starter_plains/tiles/grass_9.png", false);
        imageSetup(51, "/tilesets/starter_plains/tiles/mushroom_1.png", false);
        imageSetup(52, "/tilesets/starter_plains/tiles/mushroom_2.png", false);
        imageSetup(53, "/tilesets/starter_plains/tiles/mushroom_3.png", false);
        imageSetup(54, "/tilesets/starter_plains/tiles/mushroom_4.png", false);
        imageSetup(55, "/tilesets/starter_plains/walls/water_lily_1.png", true);
        imageSetup(56, "/tilesets/starter_plains/walls/water_lily_2.png", true);
        imageSetup(57, "/tilesets/starter_plains/walls/water_lily_3.png", true);
        imageSetup(58, "/tilesets/starter_plains/walls/water_lily_4.png", true);
        imageSetup(59, "/tilesets/starter_plains/walls/water_lily_5.png", true);
        imageSetup(60, "/tilesets/starter_plains/walls/fence.png", true);
        imageSetup(61, "/tilesets/starter_plains/walls/fence_double_1.png", true);
        imageSetup(62, "/tilesets/starter_plains/walls/fence_double_2.png", true);
        imageSetup(63, "/tilesets/starter_plains/walls/fence_double_3.png", true);
        imageSetup(64, "/tilesets/starter_plains/walls/fence_double_4.png", true);
        imageSetup(65, "/tilesets/starter_plains/walls/fence_horizontal.png", true);
        imageSetup(66, "/tilesets/starter_plains/walls/fence_single_1.png", true);
        imageSetup(67, "/tilesets/starter_plains/walls/fence_single_2.png", true);
        imageSetup(68, "/tilesets/starter_plains/walls/fence_single_3.png", true);
        imageSetup(69, "/tilesets/starter_plains/walls/fence_single_4.png", true);
        imageSetup(70, "/tilesets/starter_plains/walls/fence_vertical.png", true);
        imageSetup(71, "/tilesets/starter_plains/tiles/path_diagonal_1.png", false);
        imageSetup(72, "/tilesets/starter_plains/tiles/path_diagonal_2.png", false);
    }

    // Sets up the images for the tiles by scaling them to the correct size
    private void imageSetup(int index, String imagePath, boolean collision) 
    {
        Utility utility = new Utility();

        try {
            tileImages[index] = new Tile(ImageIO.read(getClass().getResourceAsStream(imagePath)), collision);
            tileImages[index].setImage(utility.scaleImage(tileImages[index].getImage(), Simulator.TILE_SIZE, Simulator.TILE_SIZE));
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
