package gameinfo;
//ID:316081975

/**
 * Simple class for counting things.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Counter {
    private int count;

    /**
     * creates a new counter from a number.
     *
     * @param number is the number.
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * add number to current count.
     *
     * @param number is the added number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number is the subtracted number.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * @return the value of current count
     */
    public int getValue() {
        return this.count;
    }
}