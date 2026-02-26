package game;

/**
 * The {@code game.DoublesCompare} class provides static helper methods for comparing
 * floating-point values (doubles) using a numerical threshold instead of direct
 * comparison.
 * This class defines a consistent comparison policy based on a fixed
 * {@link #THRESHOLD}, allowing the program to handle geometric calculations,
 * boundary checks, and equality tests robustly.
 *
 * <p>
 *
 * <p>
 * All comparison methods incorporate the threshold to determine whether
 * two doubles are effectively equal or which value should be considered
 * greater or smaller.
 */
public class DoublesCompare {
    /**
     * The maximal allowed difference between two doubles for them to be
     * considered equal.
     */
    public static final double THRESHOLD = 0.000000001;

    /**
     * Returns {@code true} if the difference between {@code a} and {@code b}
     * is smaller than {@link #THRESHOLD}. This method should be used instead
     * of {@code a == b}.
     *
     * @param a the first double to compare.
     * @param b the second double to compare.
     * @return {@code true} if {@code |a - b| < THRESHOLD}; {@code false} otherwise.
     */
    public static boolean equals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Returns {@code true} if {@code a} is strictly smaller than {@code b},
     * taking the threshold into account.
     *
     * <p>
     *
     * <p>
     * That is, {@code a < b - THRESHOLD}.
     *
     * @param a the first double.
     * @param b the second double.
     * @return {@code true} if {@code a} is reliably smaller than {@code b};
     * {@code false} otherwise.
     */
    public static boolean less(double a, double b) {
        return a < b - THRESHOLD;
    }

    /**
     * Returns {@code true} if {@code a} is strictly greater than {@code b},
     * considering the threshold.
     *
     * <p>
     *
     * <p>
     * That is, {@code a > b + THRESHOLD}.
     *
     * @param a the first double.
     * @param b the second double.
     * @return {@code true} if {@code a} is reliably greater than {@code b};
     * {@code false} otherwise.
     */
    public static boolean greater(double a, double b) {
        return a > b + THRESHOLD;
    }

    /**
     * Returns {@code true} if {@code a} is less than or equal to {@code b},
     * within the threshold tolerance.
     *
     * <p>
     *
     * <p>
     * This method treats values that differ by less than {@link #THRESHOLD}
     * as equal.
     *
     * @param a the first double.
     * @param b the second double.
     * @return {@code true} if {@code a <= b} within threshold tolerance;
     * {@code false} otherwise.
     */

    public static boolean lessOrEquals(double a, double b) {
        return a < b + THRESHOLD;
    }

    /**
     * Returns {@code true} if {@code a} is greater than or equal to {@code b},
     * within the threshold tolerance.
     * This method treats values that differ by less than {@link #THRESHOLD}
     * as equal.
     *
     * @param a the first double.
     * @param b the second double.
     * @return {@code true} if {@code a >= b} within threshold tolerance;
     * {@code false} otherwise.
     */
    public static boolean greaterOrEquals(double a, double b) {
        return a > b - THRESHOLD;
    }
}

