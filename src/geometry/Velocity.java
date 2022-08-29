package geometry;
//ID:316081975

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Daniel Tal
 * @version 1.1
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor that creates velocity based on changes in position on the x and the y axes.
     *
     * @param dx is the change in position on the x axes.
     * @param dy is the change in position on the x axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the change in position on the x axes.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the change in position on the y axes.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * gets a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p is the point in the old position
     * @return the point in the new position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * gets angle and speed unit and translates it to change in position on the `x` and the `y` axes.
     * @param angle is the angle of the speed.
     * @param speed is the speed unit.
     * @return the velocity created from the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //sin and cos functions gets values in radians
        double inRadians = Math.toRadians(angle);

        /*
        by definition of sin/cos function:
        sin(angle in radians)= speed/dx
        cos(angle in radians)= speed/dy
         */
        double dx = Math.sin(inRadians) * speed;
        // zero angle is up
        double dy = -Math.cos(inRadians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * finds the speedUnit from the dx and dy speed.
     * @return the speedUnit
     */
    public double getSpeedUnit() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow((this.dy), 2));
    }

    /**
     * finds the angle of the speed from the dx speed and speedUnit.
     * @return the angle of the speed.
     */
    public double getAngleInRadians() {
        return Math.asin(this.getDx() / this.getSpeedUnit());
    }
}