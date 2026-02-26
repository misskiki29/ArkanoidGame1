package Geometry_shapes;

import game.DoublesCompare;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a line segment in two-dimensional space, defined by its
 * start and end points.
 *
 * <p>
 * This class provides methods for geometric operations such as
 * calculating length, midpoint, slope, intersection points,
 * and checking whether two lines intersect.
 *
 * <p>
 * Floating-point comparisons are handled using a threshold (epsilon)
 * to account for rounding errors.
 *
 * @author Daria Lozinskaya
 * @since 2025-11-04
 */


public class Line {
    private Point start;
    private Point end;
    static final double THRESHOLD = 0.000000001;

    /**
     * Constructs a new {@code Geometry_shapes.Line} object with the start point and end point.
     *
     * @param start Starting point of the object.
     * @param end   TEnding point of the object.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a new {@code Geometry_shapes.Line} object with the four coordinates.
     *
     * @param x1 X-coordinate of the starting point of the object.
     * @param y1 Y-coordinate of the starting point of the object.
     * @param x2 X-coordinate of the ending point of the object.
     * @param y2 Y-coordinate of the ending point of the object.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns {@code true} if two double values are approximately equal,
     * within a defined {@link #THRESHOLD}.
     *
     * @param a the first value to compare.
     * @param b the second value to compare.
     * @return {@code true} if the values are nearly equal; {@code false} otherwise.
     */

    public static boolean almostEqual(double a, double b) {
        return Math.abs(a - b) <= THRESHOLD;
    }

    /**
     * Checks whether a value lies between two other values, inclusive,
     * allowing for a small rounding threshold.
     *
     * @param a   one endpoint of the range.
     * @param val the value to test.
     * @param c   the other endpoint of the range.
     * @return {@code true} if {@code val} is between {@code a} and {@code c}.
     */

    private static boolean between(double a, double val, double c) {
        return (val - a) * (val - c) <= THRESHOLD;
    }

    /**
     * Calculates the length of this line segment.
     *
     * @return the Euclidean distance between the start and end points.
     */
    public double length() {
        double distance = ((start.getX() - end.getX()) * (start.getX() - end.getX()))
                + ((start.getY() - end.getY()) * (start.getY() - end.getY()));
        return Math.sqrt(distance);
    }

    /**
     * Returns the midpoint of this line.
     *
     * @return a new {@code Geometry_shapes.Point} representing the midpoint of the line.
     */
    public Point middle() {

        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Returns a copy of the starting point of this line.
     *
     * @return the start point.
     */
    public Point start() {

        return new Point(start.getX(), start.getY());
    }

    /**
     * Returns a copy of the ending point of this line.
     *
     * @return the end point.
     */
    public Point end() {

        return new Point(end.getX(), end.getY());
    }

    /**
     * Determines if this line is vertical (its x-coordinates are equal).
     *
     * @return {@code true} if the line is vertical; {@code false} otherwise.
     */
    public boolean isVertical() {

        return Math.abs(start.getX() - end.getX()) < THRESHOLD;
    }

    /**
     * Calculates the slope of this line.
     *
     * @return the slope value; returns -1 for vertical lines.
     */
    public double getSlope() {
        if (!isVertical()) {
            double slope = (double) (start.getY() - end.getY()) / (start.getX() - end.getX());
            return slope;
        }
        return -1;
    }

    /**
     * Returns the y-intercept of this line (b in y = mx + b).
     *
     * @return the y-intercept.
     */
    public double getYIntersect() {
        return start.getY() - getSlope() * start.getX();
    }


    /**
     * Checks if a given point lies on the infinite line extending through
     * this segment.
     *
     * @param p the point to check.
     * @return {@code true} if the point lies on the infinite line.
     */
    private boolean pointOnInfiniteLine(Point p) {
        if (!isVertical()) {
            //y=m*x+b
            return Math.abs(p.getY() - (getSlope() * p.getX() + getYIntersect())) <= THRESHOLD;
        } else {
            //if line is vertical
            return Math.abs(p.getX() - start.getX()) <= THRESHOLD;
        }
    }

    /**
     * Checks if a given point lies on this finite line segment.
     *
     * @param p the point to check.
     * @return {@code true} if the point lies on the segment.
     */

    private boolean pointOnSegment(Point p) {
        //if it lies on infinite line?
        if (!pointOnInfiniteLine(p)) {
            return false;
        }
        //and if it is inside the bound
        return between(start.getX(), p.getX(), end.getX())
                && between(start.getY(), p.getY(), end.getY());
    }

    /**
     * Computes the intersection point between this line and another line.
     * <ul>
     * <li>Returns {@code null} if the lines are parallel or overlapping.</li>
     * <li>Returns {@code null} if the intersection lies outside either segment.</li>
     * </ul>
     *
     * @param other another {@code Geometry_shapes.Line} to test against.
     * @return the intersection {@code Geometry_shapes.Point}, or {@code null} if none exists.
     */
    public Point intersectionWith(Line other) {
        double xIntersection, yIntersection;

        boolean v1 = this.isVertical();
        boolean v2 = other.isVertical();

        // If both lines are non-vertical
        if (!v1 && !v2) {
            // find x-intersect
            double m1 = this.getSlope();
            double m2 = other.getSlope();
            double b1 = this.getYIntersect();
            double b2 = other.getYIntersect();

            //if parallel
            if (almostEqual(m1, m2)) {
                //if same line ->overlapping segments ->infinite intersections
                if (almostEqual(b1, b2)) {
                    return null;
                }
                return null;
            }

            xIntersection = (b2 - b1) / (m1 - m2);
            yIntersection = m1 * xIntersection + b1;
            Point p = new Point(xIntersection, yIntersection);
            return (this.pointOnSegment(p) && other.pointOnSegment(p)) ? p : null;
        }

        // If this line is vertical, other non-vertical
        if (v1 && !v2) {
            double x0 = this.start.getX();
            double m2 = other.getSlope();
            double b2 = other.getYIntersect();
            xIntersection = x0;
            yIntersection = m2 * xIntersection + b2;
            Point p = new Point(xIntersection, yIntersection);
            return (this.pointOnSegment(p) && other.pointOnSegment(p)) ? p : null;

        }
        // If this non-vertical, other vertical
        if (!v1 && v2) {
            double x0 = other.start.getX();
            double m1 = this.getSlope();
            double b1 = this.getYIntersect();
            xIntersection = x0;
            yIntersection = m1 * xIntersection + b1;
            Point p = new Point(xIntersection, yIntersection);
            return (this.pointOnSegment(p) && other.pointOnSegment(p)) ? p : null;
        }
        //both vertical
        //same x -> overlap->return null
        if (v1 && v2) {
            if (almostEqual(this.start.getY(), other.start.getX())) {
                return null; //infinite intersections
            }
            return null; //parallel vertical -> no intersection
        }
        return null;
    }


    /**
     * Returns {@code true} if this line and another line intersect.
     *
     * @param other another {@code Geometry_shapes.Line} object.
     * @return {@code true} if the two segments intersect; {@code false} otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point p = this.intersectionWith(other);
        return p != null;
    }

    /**
     * Returns {@code true} if this line intersects both given lines.
     *
     * @param other1 the first line.
     * @param other2 the second line.
     * @return {@code true} if this line intersects both lines; {@code false} otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (isIntersecting(other1) && isIntersecting(other2)) {
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if this line, {@code other1}, and {@code other2}
     * all intersect each other (i.e., form a triangle).
     *
     * @param other1 the first line.
     * @param other2 the second line.
     * @return {@code true} if all three lines intersect pairwise.
     */

    public boolean isThreeIntersecting(Line other1, Line other2) {
        if (this.isIntersecting(other1, other2) && (other1.isIntersecting(this, other2))) {
            return true;
        }
        return false;
    }


    /**
     * Checks if this line and another line are equal.
     * Two lines are considered equal if they have the same two endpoints,
     * regardless of order.
     *
     * @param other another {@code Geometry_shapes.Line} object.
     * @return {@code true} if both lines are equal; {@code false} otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    private List<Point> findIntersectionPoints(Rectangle rect) {
        List<Point> intersections = new ArrayList<>();
        Line[] recLines = new Line[4];
        recLines[0] = new Line(rect.getUpperLeft(),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()));
        recLines[1] = new Line(rect.getUpperLeft(),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()));
        recLines[2] = new Line(rect.getUpperLeft(),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()));
        recLines[3] = new Line(rect.getUpperLeft(),
                new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()));
        for (Line other : recLines) {
            Point p = this.intersectionWith(other);
            if (p != null) {
                intersections.add(p);
            }
        }
        return intersections;
    }

    /**
     * Returns the closest intersection point between this line and the given
     * rectangle. If there are no intersection points, returns {@code null}.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point to the start of the line,
     *         or {@code null} if no intersection exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        double min = this.start.distance(intersections.get(0));
        int index = 0;
        for (int i = 1; i < intersections.size(); i++) {
            Point p = intersections.get(i);
            if (DoublesCompare.less(start.distance(p), min)) {
                min = this.start.distance(p);
                index = i;
            }
        }
        return intersections.get(index);
    }

}
