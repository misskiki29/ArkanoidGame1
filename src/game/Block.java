package game;

import Geometry_shapes.Point;
import Geometry_shapes.Rectangle;
import biuoop.DrawSurface;
import listeners.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A rectangular block that can be drawn on the screen and collided with.
 * A block has a rectangle shape and a color, and implements both
 * {@link Collidable} and {@link Sprite}.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Creates a block with the given rectangle and color.
     *
     * @param rect  the block's collision rectangle.
     * @param color the block's fill color.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public void timePassed() {
        return;
    }
    /**
     * Returns the color of this object.
     *
     * @return the color of this object
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Draws the block on the given draw surface.
     *
     * @param d the surface on which the block should be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int w = (int) this.rect.getWidth();
        int h = (int) this.rect.getHeight();
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Checks whether the given ball has the same color as this object.
     *
     * @param ball the ball to compare with
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }
    /**
     * Removes this object from the given game.
     *
     * @param game the game from which this object is removed
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double leftX = rect.getUpperLeft().getX();
        double rightX = leftX + rect.getWidth();
        double topY = rect.getUpperLeft().getY();
        double bottomY = topY + rect.getHeight();
        if (DoublesCompare.equals(collisionPoint.getX(), leftX)
                || DoublesCompare.equals(collisionPoint.getX(), rightX)) {
            dx = -dx;
        }
        if (DoublesCompare.equals(collisionPoint.getY(), topY)
                || DoublesCompare.equals(collisionPoint.getY(), bottomY)) {
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }
}
