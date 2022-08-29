package geometry;
//ID:316081975

/**
 * one dimensional point.
 *
 * @author Daniel Tal
 * @version 1.1
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x is the x coordinate of the point
     * @param y is the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between two points.
     *
     * @param other is a point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * checks if a-b is closer to 0 than epsilon.
     *
     * @param a is a number
     * @param b is a number
     * @return true if a-b is closer to 0 than epsilon and false otherwise
     */
    public static boolean closeEnough(Double a, Double b) {
        final int base = 10;
        final int exponent = -1;
        if (a == null || b == null) {
            return false;
        }
        // setting epsilon as a very small number
        double epsilon = Math.pow(base, exponent);
        return (Math.abs(a - b) < epsilon);
    }

    /**
     * checks if a is bigger than b or very close to him.
     * @param a is a number.
     * @param b is a number.
     * @return true if a is bigger than b or very close to him and false otherwise.
     */
    public static boolean biggerOrCloseEnough(Double a, Double b) {
        if (closeEnough(a, b)) {
            return true;
        }
        return a > b;
    }

    /**
     * checks if two points are equal.
     *
     * @param other is a point
     * @return true if the points are equal and false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (closeEnough(this.x, other.x) && closeEnough(this.y, other.y));
    }

    /**
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * checks which of the two points "appears first" on a graph.
     * a point appears first if it's x value is smaller.
     * if the two points have the same x value, then the point with the smaller y value "appears first".
     *
     * @param other is a point.
     * @return true if this point appears before other point and false otherwise.
     */
    public boolean firstInGraph(Point other) {
        if (other == null) {
            return false;
        }
        if (this.x > other.x) {
            return false;
        }
        return (!(this.x == other.x && this.y > other.y));
    }
}