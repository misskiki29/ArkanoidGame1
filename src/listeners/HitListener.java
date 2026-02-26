package listeners;

import game.Ball;
import game.Block;

/**
 * A listener interface for objects that want to be notified
 * when a hit event occurs.
 */
public interface HitListener {
    /**
     * Called whenever the given block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that performed the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
    }
