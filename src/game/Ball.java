package game;

import Geometry_shapes.Line;
import Geometry_shapes.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The {@code game.Ball} class represents a ball with a center point, radius,
 * color and velocity, that can be drawn on a {@link DrawSurface} and moved
 * step by step inside different movement "frames".
 *
 * <p>
 *
 * <p>
 * A ball can be configured to:
 * <ul>
 *     <li>Move and bounce inside the full window frame.</li>
 *     <li>Move and bounce only inside the grey rectangle
 *         (from (50,50) to (500,500)), and also bounce off the overlapping
 *         area with the yellow rectangle.</li>
 *     <li>Move and bounce only outside both rectangles (grey and yellow).</li>
 * </ul>
 */
public class Ball implements Sprite {
    /**
     * The center of the ball.
     */
    private Point center;
    /**
     * The radius of the ball.
     */
    private int r;
    private Color color;
    /**
     * The color of the ball.
     */
    private Velocity velocity;
    /** The current velocity of the ball. */
    private GameEnvironment gameEnvironment;
    /**
     * The full window width.
     */
    static final double WIDTH = 800;
    /**
     * The full window height.
     */
    static final double HEIGHT = 600;

    /**
     * Constructs a new ball with the given center, radius and color.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        this.setGameEnvironment(g.getEnvironment());
    }

    /**
     * Draws the ball on the given draw surface.
     *
     * @param surface the surface on which the ball should be drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * Removes this ball from the given game.
     *
     * @param game the game from which this ball is removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Sets the game environment in which this ball moves.
     *
     * @param gameEnvironment the environment containing all collidable objects.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Returns the x-coordinate of the center of the ball as an integer.
     *
     * @return the x-coordinate of the ball's center.
     */
    // accessors
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y-coordinate of the center of the ball as an integer.
     *
     * @return the y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the radius (size) of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Sets the color of this object.
     *
     * @param color the new color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the ball's velocity to the given velocity object. The velocity
     * is copied so that future changes to the given {@link Velocity}
     * instance do not affect the ball.
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the ball's velocity using the given dx and dy components.
     *
     * @param dx the horizontal component of the velocity.
     * @param dy the vertical component of the velocity.
     */

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns a copy of the ball's current velocity.
     *
     * @return the current velocity of the ball.
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * Moves the ball one step inside the full window frame (0,0)-(WIDTH,HEIGHT),
     * bouncing it off the window borders when the ball reaches them.
     *
     * @param nextX the predicted next x-coordinate of the center.
     * @param nextY the predicted next y-coordinate of the center.
     */
    private void moveInFullFrame(double nextX, double nextY) {
        double x = nextX;
        double y = nextY;

        if (DoublesCompare.lessOrEquals(x - this.r, 0) || DoublesCompare.greaterOrEquals(x + this.r, WIDTH)) {
            this.velocity = new Velocity(-velocity.getDx(), velocity.getDy());
            x = this.center.getX() + this.velocity.getDx();
        }
        if (DoublesCompare.lessOrEquals(y - this.r, 0) || DoublesCompare.greaterOrEquals(y + this.r, HEIGHT)) {
            this.velocity = new Velocity(velocity.getDx(), -velocity.getDy());
            y = this.center.getY() + this.velocity.getDy();
        }
        this.center = new Point(x, y);
    }
    /**
     * Moves the ball one step according to its velocity.
     * If a collision is detected, the ball is positioned
     * slightly before the collision point and its velocity
     * is updated accordingly.
     */
    public void moveOneStep() {
        Point nextCenter = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);

        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        //If there are no collisions on this trajectory
        // then move the ball to the end of trajectory
        if (info == null) {
            this.center = nextCenter;
            return;
        }
        //If there is a collision on trajectory, there is a hit
        Point collisionPoint = info.collisionPoint();
        Collidable obj = info.collisionObject();

        //we will use epsilon to move the ball to the hit point
        //but slightly before, epsilon is this "slightly"
        double epsilon = 1.0;
        double newX = this.center.getX();
        double newY = this.center.getY();

        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();

        if (dx > 0) {
            newX = collisionPoint.getX() - epsilon;
        } else if (dx < 0) {
            newX = collisionPoint.getX() + epsilon;
        } else {
            newX = collisionPoint.getX();
        }

        if (dy > 0) {
            newY = collisionPoint.getY() - epsilon;
        } else if (dy < 0) {
            newY = collisionPoint.getY() + epsilon;
        } else {
            newY = collisionPoint.getY();
        }
        this.center = new Point(newX, newY);
        this.velocity = obj.hit(this, collisionPoint, this.velocity);
    }
}

