package geometry;
//ID:316081975
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle is a Rectangle aligned with the axes.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Rectangle {
    private Point upperLeftPoint;
    private double width;
    private double height;

    /**
     * constructor that creates a new rectangle with location and width/height.
     * @param upperLeft is the upperLeft point of the rectangle according to the animation.
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * finds the intersection points of a line and rectangle.
     * @param line is the line
     * @return a (possibly empty) List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<>();
        //checks the intersection of the line and each of the rectangle's sides.
        Point upBorderIntersect = line.intersectionWith(this.getTopBorder());
        //adds the intersection point if it's not null.
        pointsList = addNonNullPoints(upBorderIntersect, pointsList);
        Point downBorderIntersect = line.intersectionWith(this.getBottomBorder());
        pointsList = addNonNullPoints(downBorderIntersect, pointsList);
        Point leftBorderIntersect = line.intersectionWith(this.getLeftBorder());
        pointsList = addNonNullPoints(leftBorderIntersect, pointsList);
        Point rightBorderIntersect = line.intersectionWith(this.getRightBorder());
        pointsList = addNonNullPoints(rightBorderIntersect, pointsList);
        return pointsList;
    }

    /**
     * adds the intersection point if it's not null.
     * @param intersect is the intersection point.
     * @param pointsList is the list of points.
     * @return the updated list of intersection points.
     */
    private static java.util.List<Point> addNonNullPoints(Point intersect, List<Point> pointsList) {
        if (intersect == null) {
            return pointsList;
        }
        pointsList.add(intersect);
        return pointsList;
    }

    /**
     * @return the up right Point of the rectangle.
     */
    public Point upRightPoint() {
        return new Point(this.upperLeftPoint.getX() + width, this.upperLeftPoint.getY());
    }

    /**
     * @return the down left of the rectangle.
     */
    public Point downLeftPoint() {
        return new Point(this.upperLeftPoint.getX(), this.upperLeftPoint.getY() + height);
    }

    /**
     * @return the down right Point of the rectangle.
     */
    public Point downRightPoint() {
        return new Point(this.upRightPoint().getX(), this.downLeftPoint().getY());
    }

    /**
     * @return the top border of the rectangle.
     */
    public Line getTopBorder() {
        return new Line(this.upperLeftPoint, this.upRightPoint());
    }

    /**
     * @return the bottom border of the rectangle.
     */
    public Line getBottomBorder() {
        return new Line(this.downLeftPoint(), this.downRightPoint());
    }

    /**
     * @return the right border of the rectangle.
     */
    public Line getRightBorder() {
        return new Line(this.upRightPoint(), this.downRightPoint());
    }

    /**
     * @return the left border of the rectangle.
     */
    public Line getLeftBorder() {
        return new Line(this.upperLeftPoint, this.downLeftPoint());
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * changes the upper left point of the rectangle and therefore moving the whole rectangle.
     * @param newUpperLeft is the new upper left point of the rectangle.
     */
    public void setUpperLeftPoint(Point newUpperLeft) {
        this.upperLeftPoint = newUpperLeft;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }

    /**
     * checks if a point is in the rectangle.
     * @param point is the point
     * @return true if the is in the rectangle and false otherwise.
     */
    public Boolean pointInRectangle(Point point) {
        return (point.getX() >= this.upperLeftPoint.getX() && point.getX() <= this.upRightPoint().getX()
                && point.getY() >= this.upperLeftPoint.getY() && point.getY() <= this.downLeftPoint().getY());
    }
}