package game;

import Geometry_shapes.Line;
import Geometry_shapes.Point;
import Geometry_shapes.Rectangle;

import java.util.ArrayList;

/**
 * A game.GameEnvironment holds all the collidable objects in the game.
 * It is used by a ball to check for upcoming collisions.
 */

public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * Creates a new, empty game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable to add.
     */

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Removes the given collidable object from the environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object is moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, returns null.
     * Otherwise, returns the information about the closest collision
     * that is going to occur.
     *
     * @param trajectory the line representing the object's movement.
     * @return a game.CollisionInfo for the closest collision, or null if none.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestP = null;
        Collidable closestColl = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();

            Point p = trajectory.closestIntersectionToStartOfLine(rect);

            if (p != null) {
                double distance = trajectory.start().distance(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestP = p;
                    closestColl = c;
                }
            }
        }
        if (closestP == null) {
            return null;
        }
        return new CollisionInfo(closestP, closestColl);
    }
}
