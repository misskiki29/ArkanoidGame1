package game;

import listeners.HitListener;
/**
 * A HitListener that tracks and updates the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a game.ScoreTrackingListener.
     *
     * @param scoreCounter the counter used to track the game score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
    }
