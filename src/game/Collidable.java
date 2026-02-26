package game;

import Geometry_shapes.Point;
import Geometry_shapes.Rectangle;

/**
 * Represents an object that can be collided with.
 * A collidable has a collision shape and defines how a hit changes velocity.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of the object.
     *
     * @return the collision shape.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the object that it was hit at the given point,
     * and returns the new velocity after the collision.
     *
     * @param collisionPoint  the point where the collision occurs.
     * @param currentVelocity the ball's velocity before the hit.
     * @param hitter
     * @return the updated velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

