package game;

import Geometry_shapes.Point;

/**
 * Holds information about a collision:
 * the collision point and the collidable object involved.
 */
public class CollisionInfo {
    //the point at which the collision occurs.
    private Point collisionPoint;

    // the collidable object involved in the collision.
    private Collidable collisionObject;
    /**
     * Constructs a game.CollisionInfo object with the given point and collidable.
     *
     * @param collisionPoint  the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
