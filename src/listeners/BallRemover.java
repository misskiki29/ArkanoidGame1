package listeners;

import game.Ball;
import game.Block;
import game.Game;
import game.Counter;

/**
 * A HitListener that removes balls from the game
 * and updates the remaining balls counter.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * Constructs a BallRemover.
     *
     * @param game the game from which balls are removed
     * @param remainingBalls a counter that tracks the number of remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
