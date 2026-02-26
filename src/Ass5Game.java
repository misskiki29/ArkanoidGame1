//ID 230360703
// Date 2.01.2026
import game.Game;

/**
 * Entry point for Assignment 3.
 * Creates a game, initializes it, and starts the animation loop.
 */
public class Ass5Game {
    /**
     * Launches the game.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
