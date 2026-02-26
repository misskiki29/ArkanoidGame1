package game;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection that stores and manages multiple sprites.
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;

    /**
     * Creates an empty sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Removes the given sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notifies all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> snapshot = new ArrayList<>(this.sprites);
        for (Sprite s : snapshot) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites on the given surface.
     *
     * @param d the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
