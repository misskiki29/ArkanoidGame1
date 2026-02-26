package game;
import Geometry_shapes.Point;

/**
 * The {@code game.Velocity} class represents the change in position of an object
 * along the x-axis and y-axis in a single time step.
 *
 * <p>
 *
 * <p>
 * A velocity is defined by its horizontal component {@code dx} and vertical
 * component {@code dy}. The class also stores the corresponding speed
 * (the magnitude of the velocity vector) and the movement angle in degrees.
 *
 * <p>
 *
 * <p>
 * A velocity can be applied to a {@link Point} using
 * {@link #applyToPoint(Point)}, producing a new point that reflects
 * the movement defined by this velocity. A velocity can also be created
 * from an angle and speed using
 * {@link #fromAngleAndSpeed(double, double)}.
 */

public class Velocity {
    /**
     * Horizontal change in position per step.
     */
    private double dx;
    /**
     * Vertical change in position per step.
     */
    private double dy;
    /**
     * The direction of the velocity in degrees.
     */
    private double angle;
    /**
     * The speed (magnitude) of the velocity vector.
     */
    private double speed;

    /**
     * Constructs a new {@code game.Velocity} with the given horizontal and vertical
     * movement components. The corresponding speed and angle are calculated
     * automatically.
     *
     * @param dx the horizontal change per step.
     * @param dy the vertical change per step.
     */

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        setSpeedAndAngle();
    }

    /**
     * Computes and updates the speed and angle based on the current {@code dx}
     * and {@code dy} values. The angle is stored in degrees and computed using
     * {@link Math#atan2(double, double)}.
     */
    private void setSpeedAndAngle() {
        this.speed = Math.sqrt(dx * dx + dy * dy);
        this.angle = Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * Creates a new {@code game.Velocity} object from the given angle and speed.
     * The angle is interpreted in degrees, with 0 degrees pointing to the
     * right (positive x direction), and positive angles rotating
     * counterclockwise.
     *
     * <p>
     *
     * <p>
     * The horizontal and vertical components {@code dx} and {@code dy} are
     * computed from the angle and speed using basic trigonometric relations.
     *
     * @param angle the direction of movement in degrees.
     * @param speed the magnitude of the velocity.
     * @return a new {@code game.Velocity} with components corresponding to the
     * given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle);
        double dx = speed * Math.cos(angleRad);
        double dy = speed * Math.sin(angleRad);
        return new Velocity(dx, dy);
    }
    /**
     * Returns the speed of this velocity.
     *
     * @return the {@code speed} value.
     */

    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the horizontal component of this velocity.
     *
     * @return the {@code dx} value.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the vertical component of this velocity.
     *
     * @return the {@code dy} value.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies this velocity to a given point.
     *
     * <p>
     *
     * <p>
     * The resulting point represents the new position after moving
     * by {@code dx} horizontally and {@code dy} vertically.
     *
     * @param p the point to which the velocity should be applied.
     * @return a new {@code Geometry_shapes.Point} representing the updated position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
