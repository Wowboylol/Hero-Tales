/*  
 *  MapLoader.java
 *  
 *  Description: Used by all map strategies to load map from file.
 *               Loads data and returns data in a 2D array of ints.
 *               2D array represents the tile index in the respective strategy.
 *
*/

package graphics.maps;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class MapLoader 
{
    public int[][] loadMap(String mapPath, int mapWidth, int mapHeight)
    {
        int mapTileGrid[][] = new int[mapWidth][mapHeight];

        try {
            InputStream mapFile = getClass().getResourceAsStream(mapPath);
            BufferedReader mapDataReader = new BufferedReader(new InputStreamReader(mapFile));
            int columnNumber = 0;
            int rowNumber = 0;

            while(columnNumber < mapWidth && rowNumber < mapHeight)
            {
                String line = mapDataReader.readLine();

                while(columnNumber < mapWidth)
                {
                    String tileData[] = line.split(" ");
                    int tileIndex = Integer.parseInt(tileData[columnNumber]);

                    mapTileGrid[columnNumber][rowNumber] = tileIndex;
                    columnNumber++;
                }
                if(columnNumber == mapWidth)
                {
                    columnNumber = 0;
                    rowNumber++;
                }
            }
            mapDataReader.close();
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return mapTileGrid;
    }
}
