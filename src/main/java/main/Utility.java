/*  
 *  Utility.java
 *  
 *  Description: Contains helpful functions used game-wide.
 *
*/

package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import entities.Player;

public class Utility 
{
    // Resizes image to specified width and height
    public static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2d = scaledImage.createGraphics();
        graphics2d.drawImage(image, 0, 0, width, height, null);
        graphics2d.dispose();
        return scaledImage;
    }

    // Rotates image by angle in degrees
    public static BufferedImage rotateImage(BufferedImage sprite, int angle)
    {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(angle), w/2, h/2);
        AffineTransformOp op = new AffineTransformOp(rotate, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(sprite, null);
    }

    // Draw circle given graphics
    public static void drawCircle(Graphics2D graphics2D, int x, int y, int radius)
    {
        graphics2D.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    // Calculate angle between origin and point
    public static int calculateAngle(int originX, int originY, int pointX, int pointY)
    {
        int deltaX = pointX - originX;
        int deltaY = pointY - originY;
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        if(angle < 0) angle += 360;
        return (int)angle;
    }

    // Calculates the x position on screen given world coordinate x
    public static int getScreenX(Simulator simulator, int givenCoordinateX)
    {
        if(Player.PLAYER_SCREEN_X > simulator.getPlayerCoordinate().getX())
            return givenCoordinateX;

        if((Simulator.SCREEN_WIDTH - Player.PLAYER_SCREEN_X) > simulator.getMapWidth() - simulator.getPlayerCoordinate().getX())
            return Simulator.SCREEN_WIDTH - (simulator.getMapWidth() - givenCoordinateX);

        return givenCoordinateX - simulator.getPlayerCoordinate().getX() + Player.PLAYER_SCREEN_X;
    }

    // Calculates the y position on screen given world coordinate y
    public static int getScreenY(Simulator simulator, int givenCoordinateY)
    {
        if(Player.PLAYER_SCREEN_Y > simulator.getPlayerCoordinate().getY())
            return givenCoordinateY;

        if((Simulator.SCREEN_HEIGHT - Player.PLAYER_SCREEN_Y) > simulator.getMapHeight() - simulator.getPlayerCoordinate().getY())
            return Simulator.SCREEN_HEIGHT - (simulator.getMapHeight() - givenCoordinateY);

        return givenCoordinateY - simulator.getPlayerCoordinate().getY() + Player.PLAYER_SCREEN_Y;
    }
}
