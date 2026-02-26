package Geometry_shapes;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The {@code Geometry_shapes.Rectangle} class represents an axis-aligned rectangle defined by
 * its upper-left point, width, height, and color.
 *
 * <p>
 *
 * <p>
 *
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a new {@code Geometry_shapes.Rectangle} with the specified upper-left point,
     * width, height, and color.
     *
     * <p>
     *
     * <p>
     * The {@code start} point is copied to avoid aliasing.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the x-coordinate of the upper-left corner of the rectangle.
     *
     * @return the x-coordinate of the rectangle's starting point.
     */
    public int getStartX() {
        return (int) upperLeft.getX();
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */

    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Returns the width of the rectangle as a double.
     *
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle as a double.
     *
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns all intersection points between this rectangle and the given line.
     * Duplicate points (e.g., corner hits) are removed.
     *
     * @param line the line to check
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new java.util.ArrayList<Point>();
        Line[] lines = new Line[4];
        Point ul = this.upperLeft;
        Point ur = new Point(ul.getX() + this.getWidth(), ul.getY());
        Point bl = new Point(ul.getX(), ul.getY() + this.getHeight());
        Point br = new Point(ul.getX() + this.getWidth(), ul.getY() + this.getHeight());
        lines[0] = new Line(ul, ur);
        lines[1] = new Line(ur, br);
        lines[2] = new Line(br, bl);
        lines[3] = new Line(bl, ul);
        //finding intersection points
        for (Line edge : lines) {
            Point p = edge.intersectionWith(line);
            if (p != null) {
                boolean exists = false;
                for (Point q : intersections) {
                    if (q.equals(p)) {
                        exists = true;
                    }
                    break;
                }
                if (!exists) {
                    intersections.add(p);
                }
            }
        }
        return intersections;
    }

    /**
     * Draws the rectangle (filled) on the given {@link DrawSurface}.
     *
     * <p>
     *
     * <p>
     * The rectangle is drawn from its upper-left corner using its width and
     * height.
     *
     * @param d the draw surface on which to draw the rectangle.
     */
    // draw the rectangle on the given DrawSurface
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) getWidth(), (int) getHeight());
    }
}
