package Geometry_shapes;

/**
 * Represents a single point in two-dimensional Cartesian space.
 *
 * <p>
 * This class stores the x and y coordinates of a point and provides
 * methods for basic geometric operations such as measuring distance
 * between two points and checking equality with tolerance.
 * </p>
 *
 * <p>Floating-point comparisons are handled using a small {@link #THRESHOLD}
 * value to mitigate rounding errors.</p>
 *
 * @author Daria
 * @since 2025-11-04
 */
public class Point {
    private double x;
    private double y;
    /**
     * Threshold for floating-point comparisons.
     */
    static final double THRESHOLD = 0.000000001;

    /**
     * Constructs a new {@code Geometry_shapes.Point} with the specified coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other {@code Geometry_shapes.Point} to which the distance is measured.
     * @return the distance between this point and {@code other}.
     */
    public double distance(Point other) {
        double distance = ((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY()));
        return Math.sqrt(distance);
    }

    /**
     * Checks whether this point is approximately equal to another point,
     * within the {@link #THRESHOLD} margin of error.
     *
     * @param other the other {@code Geometry_shapes.Point} to compare with.
     * @return {@code true} if both coordinates are approximately equal;
     * {@code false} otherwise.
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.getX() - other.getX()) < THRESHOLD)
                && (Math.abs(this.getY() - other.getY()) < THRESHOLD)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y value.
     */

    public double getY() {
        return this.y;
    }
    /**
     * Prints this point in the format (x, y).
     */
    public void print() {
        System.out.println("(" + this.getX() + ", " + this.getY() + ")");
    }
}


