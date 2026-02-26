package game;

/**
 * A counter that keeps track of an integer value.
 */
public class Counter {
    private int counter;
    /**
     * Constructs a counter with an initial value of zero.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * Increases the counter by the given number.
     *
     * @param number the amount to add to the counter
     */
    public void increase(int number) {
        this.counter = counter + number;
    }
    /**
     * Decreases the counter by the given number.
     *
     * @param number the amount to subtract from the counter
     */
    public void decrease(int number) {
        this.counter = counter - number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current counter value
     */
    public int getValue() {
        return this.counter;
    }
}
