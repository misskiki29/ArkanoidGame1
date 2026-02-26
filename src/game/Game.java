package game;

import Geometry_shapes.Point;
import Geometry_shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import listeners.BallRemover;
import listeners.BlockRemover;

import java.awt.Color;

/**
 * The game.Game class manages sprites, collidables, and the animation loop.
 * It builds the game level, initializes all objects, and runs the game.
 */
public class Game {
    /**
     * All drawable and updatable game objects.
     */
    private SpriteCollection sprites;
    /**
     * All collidable objects used for collision detection.
     */
    private GameEnvironment environment;
    /**
     * The GUI window in which the game is displayed.
     */
    private GUI gui;
    private Counter remainingBlocks = new Counter();
    private Counter remainingBalls = new Counter();
    private Counter score = new Counter();

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * Creates an empty game with sprite and environment containers.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }
    /**
     * Returns the current game score.
     *
     * @return the current score counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Returns this environment.
     *
     * @return the environment of this game
     */

    public GameEnvironment getEnvironment() {
        return this.environment;
    }
    /**
     * Returns the counter of remaining blocks.
     *
     * @return the remaining blocks counter
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
    /**
     * Returns the counter of remaining balls.
     *
     * @return the remaining balls counter
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Adds a collidable object to the game.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game:
     * creates the GUI, borders, paddle, blocks, and balls,
     * adds them to the sprite and environment lists,
     * and sets starting positions and colors.
     */
    public void initialize() {
        this.gui = new GUI("game.Game", WIDTH, HEIGHT);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        indicator.addToGame(this);
        //Creating side borders
        int borderThickness = 10;
        Block topBorder = new Block(
                new Rectangle(new Point(0, 0), WIDTH, borderThickness),
                Color.GRAY);
        Block leftBorder = new Block(
                new Rectangle(new Point(0, borderThickness),
                        borderThickness, HEIGHT - borderThickness),
                Color.GRAY);
        Block rightBorder = new Block(
                new Rectangle(new Point(WIDTH - borderThickness, borderThickness),
                        borderThickness, HEIGHT - borderThickness),
                Color.GRAY);
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT - borderThickness + 5),
                WIDTH, borderThickness),
                Color.ORANGE);

        topBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        //Creating paddle
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(
                new Rectangle(new Point(350, 560), 150, 20),
                Color.ORANGE,
                keyboard,
                7,
                Game.WIDTH
        );
        paddle.addToGame(this);
        //creating blocks and adding them to collidable
        int blockWidth = 50;
        int blockHeight = 20;
        int rows = 6;
        int cols = 12;
        int startX = 100;
        int startY = 100;
        int startXRight = WIDTH - borderThickness - cols * blockWidth;
        Color[] rowColors = {
                Color.GRAY,
                Color.RED,
                Color.YELLOW,
                Color.BLUE,
                Color.PINK,
                Color.GREEN
        };
        for (int row = 0; row < rows; row++) {
            int colsInRow = cols - row;
            int rowStartX = WIDTH - borderThickness - colsInRow * blockWidth;
            for (int col = 0; col < colsInRow; col++) {
                int x = rowStartX + col * blockWidth;
                int y = startY + row * blockHeight;
                Block b = new Block(
                        new Rectangle(new Point(x, y), blockWidth, blockHeight),
                        rowColors[row]
                );
                b.addHitListener(blockRemover);
                b.addHitListener(scoreListener);
                b.addToGame(this);
                this.remainingBlocks.increase(1);
            }
        }
        //Adding ball and connecting it with game environment
        Ball ball1 = new Ball(new Point(400, 500), 7, Color.RED);
        ball1.setVelocity(3, -4);
        ball1.addToGame(this);
        this.remainingBalls.increase(1);
        Ball ball2 = new Ball(new Point(300, 450), 7, Color.WHITE);
        ball2.setVelocity(-3, -5);
        ball2.addToGame(this);
        this.remainingBalls.increase(1);
        Ball ball3 = new Ball(new Point(100, 450), 7, Color.PINK);
        ball3.setVelocity(-3, -5);
        ball3.addToGame(this);
        this.remainingBalls.increase(1);
        //Dispalying score

    }

    /**
     * Runs the game loop:
     * draws all sprites, updates them,
     * and maintains a fixed frame rate of 60 FPS.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getValue() > 0
                && this.remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
                DrawSurface ds = gui.getDrawSurface();
                this.sprites.drawAllOn(ds);
                gui.show(ds);
            }
            if (this.remainingBlocks.getValue() == 0) {
                System.out.println("You Win!\nYour score is: " + this.score.getValue());
            }
            if (this.remainingBalls.getValue() == 0) {
                System.out.println("game.Game Over.\nYour score is: " + this.score.getValue());
            }
        }
        gui.close();
    }

    void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
