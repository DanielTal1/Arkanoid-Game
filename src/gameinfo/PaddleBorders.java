package gameinfo;
//ID:316081975

/**
 * the frame in which a ball must stay. contains 4 borders- one for each side.
 *
 * @author Daniel Tal
 * @version 1.1
 */
public class PaddleBorders {
    private double leftBorder;
    private double rightBorder;

    /**
     * constructor that creates a new paddle window based on leftBorder and rightBorder.
     * @param left is the leftBorder.
     * @param right is the rightBorder.
     */
    public PaddleBorders(double left, double right) {
        this.leftBorder = left;
        this.rightBorder = right;
    }

    /**
     * @return the left border of the paddle window.
     */
    public double getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * @return the start height of the window
     */
    public double getRightBorder() {
        return this.rightBorder;
    }
}
