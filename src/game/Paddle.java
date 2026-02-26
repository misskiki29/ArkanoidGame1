package game;

import Geometry_shapes.Point;
import Geometry_shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * A paddle controlled by the player. It moves left and right,
 * can be drawn on the screen, and acts as a collidable object.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int speed;
    private int screenWidth;
    /**
     * Creates a new paddle.
     *
     * @param rect the paddle's rectangle
     * @param color the paddle's color
     * @param keyboard the keyboard sensor for movement
     * @param speed the movement speed per frame
     * @param screenWidth the width of the game screen (for wrap-around)
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard,
                  int speed, int screenWidth) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        this.screenWidth = screenWidth;
    }

    /**
     * Moves the paddle left, wrapping around if it exits the screen.
     */
    public void moveLeft() {
        double newX = this.rect.getUpperLeft().getX() - speed;
        if (newX + rect.getWidth() < 0) {
            newX = screenWidth;
        }
        this.rect = new Rectangle(
                new Point(newX, rect.getUpperLeft().getY()),
                rect.getWidth(),
                rect.getHeight()
        );

    }
    /**
     * Moves the paddle right, wrapping around if it exits the screen.
     */
    public void moveRight() {
        double newX = this.rect.getUpperLeft().getX() + speed;
        if (newX > screenWidth) {
            newX = -rect.getWidth();
        }
        this.rect = new Rectangle(
                new Point(newX, rect.getUpperLeft().getY()),
                rect.getWidth(),
                rect.getHeight()
        );
    }
    /**
     * Moves the paddle according to current keyboard input.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given surface.
     *
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int w = (int) rect.getWidth();
        int h = (int) rect.getHeight();
        d.setColor(color);
        d.fillRectangle(x, y, w, h);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, w, h);

    }

    /**
     * Returns the paddle's collision rectangle.
     *
     * @return the rectangle representing the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;


    }
    /**
     * Computes the paddle's response to a collision.
     * The top surface is divided into five regions that determine
     * the outgoing velocity angle.
     *
     * @param collisionPoint the point of impact
     * @param currentVelocity the incoming velocity
     * @param hitter the project that hits
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double leftX = rect.getUpperLeft().getX();
        double width = rect.getWidth();
        double regionWidth = width / 5.0;
        double hitPos = x - leftX;

        // clamp hitPos inside [0, width)
        if (hitPos < 0) {
            hitPos = 0;
        }
        if (hitPos >= width) {
            hitPos = width - 0.0001;
        }

        int region = (int) (hitPos / regionWidth);
        double speed = currentVelocity.getSpeed();

        Velocity newVel;
        switch (region) {
            case 0:
                newVel = Velocity.fromAngleAndSpeed(300, speed);
                break;
            case 1:
                newVel = Velocity.fromAngleAndSpeed(330, speed);
                break;
            case 2:
                newVel = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
                break;
            case 3:
                newVel = Velocity.fromAngleAndSpeed(30, speed);
                break;
            case 4:
                newVel = Velocity.fromAngleAndSpeed(60, speed);
                break;
            default:
                newVel = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
                break;
        }
        if (newVel.getDy() >= 0) {
            newVel = new Velocity(newVel.getDx(), -Math.abs(newVel.getDy()));
        }

        return newVel;
        }
    /**
     * Adds the paddle to the given game as both sprite and collidable.
     *
     * @param g the game to add the paddle to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
