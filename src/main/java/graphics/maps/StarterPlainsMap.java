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
import graphics.Tile;
import main.Simulator;

public class StarterPlainsMap implements MapStrategy
{
    // Attributes
    public static final int MAP_TILES = 55;
    private Tile tiles[];

    // Default constructor
    public StarterPlainsMap()
    {
        this.tiles = new Tile[MAP_TILES];
        getTileImages();
    }

    // Build map
    public void buildMap(Graphics2D graphics2D)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < Simulator.MAX_SCREEN_COL && row < Simulator.MAX_SCREEN_ROW)
        {
            graphics2D.drawImage(tiles[0].getImage(), x, y, Simulator.TILE_SIZE, Simulator.TILE_SIZE, null);
            col++;
            x += Simulator.TILE_SIZE;

            if(col == Simulator.MAX_SCREEN_COL)
            {
                col = 0;
                x = 0;
                row++;
                y += Simulator.TILE_SIZE;
            }
        }
    }

    // Get tile images
    private void getTileImages()
    {
        try {
            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_1.png")));
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_2.png")));
            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_3.png")));
            tiles[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_4.png")));
            tiles[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_5.png")));
            tiles[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path.png")));
            tiles[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_1.png")));
            tiles[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_2.png")));
            tiles[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_3.png")));
            tiles[9] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_corner_4.png")));
            tiles[10] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_1.png")));
            tiles[11] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_2.png")));
            tiles[12] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_3.png")));
            tiles[13] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_inner_4.png")));
            tiles[14] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_1.png")));
            tiles[15] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_2.png")));
            tiles[16] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_3.png")));
            tiles[17] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/path_side_4.png")));
            tiles[18] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall.png")), true);
            tiles[19] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_1.png")), true);
            tiles[20] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_2.png")), true);
            tiles[21] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_3.png")), true);
            tiles[22] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_corner_4.png")), true);
            tiles[23] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_1.png")), true);
            tiles[24] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_2.png")), true);
            tiles[25] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_3.png")), true);
            tiles[26] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_inner_4.png")), true);
            tiles[27] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_1.png")), true);
            tiles[28] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_2.png")), true);
            tiles[29] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_3.png")), true);
            tiles[30] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/wall_side_4.png")), true);
            tiles[31] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water.png")), true);
            tiles[32] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_1.png")), true);
            tiles[33] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_2.png")), true);
            tiles[34] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_3.png")), true);
            tiles[35] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_corner_4.png")), true);
            tiles[36] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_1.png")), true);
            tiles[37] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_2.png")), true);
            tiles[38] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_3.png")), true);
            tiles[39] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_inner_4.png")), true);
            tiles[40] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_1.png")), true);
            tiles[41] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_2.png")), true);
            tiles[42] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_3.png")), true);
            tiles[43] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/walls/water_side_4.png")), true);
            tiles[44] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_1.png")));
            tiles[45] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_2.png")));
            tiles[46] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/flower_3.png")));
            tiles[47] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_6.png")));
            tiles[48] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_7.png")));
            tiles[49] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_8.png")));
            tiles[50] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/grass_9.png")));
            tiles[51] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_1.png")));
            tiles[52] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_2.png")));
            tiles[53] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_3.png")));
            tiles[54] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tilesets/starter_plains/tiles/mushroom_4.png")));
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
