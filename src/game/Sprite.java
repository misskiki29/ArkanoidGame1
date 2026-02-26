package game;

import biuoop.DrawSurface;

/**
 * A game.Sprite is an object that can be drawn on the screen and updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given surface.
     *
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that time has passed.
     * Used to update the sprite's state.
     */
    void timePassed();
    /**
     * Adds the sprite to the given game.
     *
     * @param g the game to add this sprite to
     */
    void addToGame(Game g);
}
