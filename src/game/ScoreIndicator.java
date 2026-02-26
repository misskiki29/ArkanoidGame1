package game;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * A sprite that displays the current game score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructs a game.ScoreIndicator with the given score counter.
     *
     * @param score the counter representing the game score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(390, 25, "Score: " + score.getValue(), 16);
    }

    @Override
    public void timePassed() {
    }
}
