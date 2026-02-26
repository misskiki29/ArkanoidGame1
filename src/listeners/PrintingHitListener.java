package listeners;

import game.Ball;
import game.Block;

/**
 * A HitListener that prints a message when a block is hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Called when a block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that performed the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A game.Block was hit.");
    }
}
