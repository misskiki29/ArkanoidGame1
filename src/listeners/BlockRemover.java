package listeners;

import game.Ball;
import game.Block;
import game.Game;
import game.Counter;

/**
 * A HitListener that removes blocks from the game
 * and updates the remaining blocks counter.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * Constructs a BlockRemover.
     *
     * @param game the game from which blocks are removed
     * @param remainingBlocks a counter that tracks the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
