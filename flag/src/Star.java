/*
 * AP Computer Science Flag Project
 * Code written by Rafi Long
 * See code for documentation
 */

import java.awt.*;

/**
 * Describes a star of particular size and location in relation to the height of the flag
 * Draws the star
 */
public class Star {
    /** The distance the center of the star is on the X axis in relation to the total size of the flag */
    private double ratioX = 0;
    /** The distance the center of the star is on the Y axis in relation to the total size of the flag */
    private double ratioY = 0;
    /** The diameter of the star in relation to the total size of the flag */
    private double ratioD = 0;

    /** The color of the rectangle */
    public Color color;

    /**
     * Basic rectangle constructor
     * @param ratioX see variable documentation
     * @param ratioY see variable documentation
     * @param ratioD see variable documentation
     */
    public Star(double ratioX, double ratioY, double ratioD, Color color) {
        this.ratioX = ratioX;
        this.ratioY = ratioY;
        this.ratioD = ratioD;
        this.color = color;
    }

    /**
     * Prints the star using fillPolygon
     * Inner radius proportion found by John B. Hall at:
     *   http://johnbhall.com/2012/03/normal-illustrator-star-american-flag/
     * @param g graphics
     */
    public void drawStar(Graphics g, Color color) {
        g.setColor(color);

        // The x points and the y points
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        for (int triangle = 0; triangle < 5; triangle++) {
            // A holder for the points returned by findPointOnStar
            double[] points;

            // Finds a point on the inner circle of the star, and adds to the list of points
            points = findPointOnStar((234 + triangle * 72) % 360, ratioD * .382);
            xPoints[triangle * 2] = (int) points[0];
            yPoints[triangle * 2] = (int) points[1];

            // Finds the corresponding point on the outer circle of the star, and adds to the list of points
            points = findPointOnStar((270 + triangle * 72) % 360, ratioD);
            xPoints[1 + triangle * 2] = (int) points[0];
            yPoints[1 + triangle * 2] = (int) points[1];
        }

        g.fillPolygon(xPoints, yPoints, 10);
    }

    /**
     * Finds the point on a circle with the degree and diameter
     * @param degree the degree the point is above the x axis
     * @param ratioDiameter the diameter of the circle the point can be found on
     * @return the x and the
     */
    public double[] findPointOnStar(double degree, double ratioDiameter) {
        // Finds the x and y point as if the center of the star is origin
        double xPoint = Math.cos(Math.toRadians(degree)) * ratioDiameter / 2;
        double yPoint = Math.sin(Math.toRadians(degree)) * ratioDiameter / 2;

        // Adds offsets (ratioX and Display.centerXOffset) so origin is the edge of the screen, and multiplies
        //  proportions by the flag height to convert to pixels
        double[] xyPoint = {(xPoint + ratioX) * Display.flagHeight + Display.centerXOffset,
                (yPoint + ratioY) * Display.flagHeight + Display.centerYOffset};

        // Returns the fixed x and y points
        return xyPoint;
    }
}
