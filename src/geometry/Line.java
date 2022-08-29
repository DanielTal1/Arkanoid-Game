package geometry;
//ID:316081975
import java.util.List;
import java.util.Objects;

/**
 * straight line.
 *
 * @author Daniel Tal
 * @version 1.1
 */
public class Line {
    private Point start;
    private Point end;
    private Double slope;
    private Double yAxis;
    private Point startInGraph;
    private Point endInGraph;

    /**
     * Constructor that creates a line based on two points.
     *
     * @param start is first point
     * @param end is the second point
     */
    public Line(Point start, Point end) {
        // setting the start and end points of the line based on which comes first on a graph.
        if (start.firstInGraph(end)) {
            this.startInGraph = start;
            this.endInGraph = end;
        } else {
            this.startInGraph = end;
            this.endInGraph = start;
        }
        this.start = start;
        this.end = end;
        this.slope = slope(this.startInGraph, this.endInGraph);
        this.yAxis = yAxisIntercept(this.startInGraph, this.slope);

    }

    /**
     * Constructor that creates a line based on 4 double values.
     *
     * @param x1 is the x coordinate of the first point
     * @param y1 is the y coordinate of the first point
     * @param x2 is the x coordinate of the second point
     * @param y2 is the y coordinate of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point first = new Point(x1, y1);
        Point second = new Point(x2, y2);
        // setting the start and end points of the line based on which comes first on a graph.
        if (first.firstInGraph(second)) {
            this.startInGraph = first;
            this.endInGraph = second;
        } else {
            this.startInGraph = second;
            this.endInGraph = first;
        }
        this.start = first;
        this.end = second;
        this.slope = slope(this.startInGraph, this.endInGraph);
        this.yAxis = yAxisIntercept(this.startInGraph, this.slope);
    }

    /**
     * calculating the slope of the line created by the two points.
     *
     * @param startP is the first point
     * @param endP is the second point
     * @return the double value of the slope of the line, null is returned if the line has no slope.
     */
    private Double slope(Point startP, Point endP) {
        double deltaY = endP.getY() - startP.getY();
        double deltaX = endP.getX() - startP.getX();
        if (Point.closeEnough(deltaX, (double) 0)) {
            return null;
        }
        return deltaY / deltaX;
    }

    /**
     * calculating the y-intercept of the line.
     *
     * @param startP is the first point.
     * @param slopeOfLine is slope of the line.
     * @return the y value of the point where the line meets (or should meet) the y-axis,
     * null is returned if the line has no slope.
     */
    private Double yAxisIntercept(Point startP, Double slopeOfLine) {

        /*
        if the line has no slope then it's equation is based on the x coordinate,
        therefore there is no need in y-intercept.
         */
        if (slopeOfLine == null) {
            return null;
        }
        //if the slope is zero then the y value of all the points on the line are equal
        if (Point.closeEnough(slopeOfLine, (double) 0)) {
            return startP.getY();
        }
        //calculating the y-intercept using slope-intercept form of line equation
        return slopeOfLine * (-startP.getX()) + startP.getY();
    }

    /**
     * @return the length of the line based on the distance between the start and end points.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);

    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return the slope of the line
     */
    public Double getSlope() {
        return this.slope;
    }

    /**
     * @return the y-intercept of the line.
     */
    public Double getYAxis() {
        return this.yAxis;
    }

    /**
     * checks if the two lines intersect.
     * coincident lines count as the lines intersect.
     *
     * @param other is a point
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        // if intersectionWith finds one point of intersection then the lines intersect
        if (this.intersectionWith(other) != null) {
            return true;
        }

        /*
         if the lines don't intersect in a single point
         they can coincide or they don't intersect at all.
         if the lines has different equations then they don't intersect
         */
        if (!Objects.equals(this.slope, other.slope) || !Objects.equals(this.yAxis, other.yAxis)) {
            return false;
        }
        // checking if the lines coincide between the start and end points.
        if (this.slope == null) {
            if (this.startInGraph.getX() == other.startInGraph.getX()) {
                return ((this.startInGraph.getY() >= other.startInGraph.getY()
                        && this.startInGraph.getY() <= other.endInGraph.getY())
                        || other.startInGraph.getY() >= this.startInGraph.getY()
                        && other.startInGraph.getY() <= this.endInGraph.getY());
            }
            return false;
        }
        return ((this.startInGraph.getX() >= other.startInGraph.getX()
                && this.startInGraph.getX() <= other.endInGraph.getX())
                || other.startInGraph.getX() >= this.startInGraph.getX()
                && other.startInGraph.getX() <= this.endInGraph.getX());
    }


    /**
     * finds the intersection point between two lines.
     *
     * @param other is a point
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //checking if each line is made of a single point
        if (this.startInGraph.equals(this.end) && other.startInGraph.equals(other.endInGraph)) {
            if (this.startInGraph.equals(other.startInGraph)) {
                return this.startInGraph;
            }
            return null;
        //checking if this line is made of a single point
        } else if (this.startInGraph.equals(this.endInGraph)) {
            return other.pointInLine(this.startInGraph);
        //checking if other line is made of a single point
        } else if (other.startInGraph.equals(other.end)) {
            return this.pointInLine(other.startInGraph);
        }
        double xOfIntersection;
        double yOfIntersection;
        //checking if both lines has no slope and therefore their line equations are of the type x=number
        if (this.slope == null && other.slope == null) {
            //checking if the one line is the continuation of another
            if (this.startInGraph.equals(other.endInGraph)) {
                xOfIntersection = this.startInGraph.getX();
                yOfIntersection = this.startInGraph.getY();
            } else if (this.endInGraph.equals(other.startInGraph)) {
                xOfIntersection = this.endInGraph.getX();
                yOfIntersection = this.endInGraph.getY();
            } else {
                return null;
            }
        //checking if this line has no slope and therefore his line equations is of the type x=number
        } else if (this.slope == null) {
            xOfIntersection = this.startInGraph.getX();
            yOfIntersection = xOfIntersection * other.slope + other.yAxis;
        //checking if other line has no slope and therefore his line equations is of the type x=number
        } else if (other.slope == null) {
            xOfIntersection = other.start.getX();
            yOfIntersection = xOfIntersection * this.slope + this.yAxis;

        /*
        there's two ordinary lines with a line equation of the type y=mx+b.
        m is the slope of the line, y is the y-intercept.
         */
        } else {
            double deltaSlope = this.slope - other.slope;
            double deltaYAxis = other.yAxis - this.yAxis;

            /*
            checking if both deltaSlope and deltaYAxis are 0 (or smaller than epsilon)
            and therefore the lines has the same equation.
            in this case the lines are located on the same infinite line
             */
            if (Point.closeEnough(deltaSlope, (double) 0) && Point.closeEnough(deltaYAxis, (double) 0)) {
                //checking if the one line is the continuation of another
                if (this.startInGraph.equals(other.endInGraph)) {
                    xOfIntersection = this.startInGraph.getX();
                    yOfIntersection = xOfIntersection * this.slope + this.yAxis;
                    return new Point(xOfIntersection, yOfIntersection);
                }
                if (this.endInGraph.equals(other.startInGraph)) {
                    xOfIntersection = other.startInGraph.getX();
                    yOfIntersection = xOfIntersection * this.slope + this.yAxis;
                    return new Point(xOfIntersection, yOfIntersection);
                } else {
                    return null;
                }
            }
            //if deltaSlope is zero but deltaYAxis isn't then the lines are Parallel
            if (Point.closeEnough(deltaSlope, (double) 0)) {
                return null;
            }

            /*
            if both deltaSlope and deltaYAxis are not zero,
            then the lines or their imaginary continuation must intercept.
            if the equations of this line: y=mx+b, other line: y=nx+c
            then in order to find the xOfIntersection,the two equations needs to be equal:mx+b=nx+c.
            therefore (m-n)x=c-b and x=(c-b)/(m-n) which is deltaYAxis/deltaSlope.
            in order to find yOfIntersection we need to substitute xOfIntersection in one of the equations.
             */
            xOfIntersection = deltaYAxis / deltaSlope;
            yOfIntersection = this.slope * xOfIntersection + this.yAxis;
        }
        final int base = 10;
        final int exponent = -1;
        double epsilon = Math.pow(base, exponent);

        /*
        checking if the xOfIntersection and yOfIntersection are between
        the start and end points of each line.
        if the point is not in the range of one of the lines then there is no point of intersection.
         */
        if (xOfIntersection > this.endInGraph.getX() + epsilon || xOfIntersection + epsilon < this.startInGraph.getX()
                || xOfIntersection > other.endInGraph.getX() + epsilon
                || xOfIntersection + epsilon < other.startInGraph.getX()
                || yOfIntersection - epsilon > Math.max(this.startInGraph.getY(), this.endInGraph.getY())
                || yOfIntersection + epsilon < Math.min(this.startInGraph.getY(), this.endInGraph.getY())
                || yOfIntersection - epsilon > Math.max(other.startInGraph.getY(), other.endInGraph.getY())
                || yOfIntersection + epsilon < Math.min(other.startInGraph.getY(), other.endInGraph.getY())) {
            return null;
        }
        return new Point(xOfIntersection, yOfIntersection);
    }


    /**
     * checks if two lines are equal.
     *
     * @param other is a line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.slope == null && other.slope == null) {
            return (this.startInGraph.equals(other.startInGraph) && this.endInGraph.equals(other.endInGraph));
        }
        return (this.startInGraph.equals(other.startInGraph) && this.endInGraph.equals(other.endInGraph)
                && Point.closeEnough(this.slope, other.slope)
                && Point.closeEnough(this.yAxis, other.yAxis));

    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the start of the line.

    /**
     * finds the closest intersection point to the start of the line.
     * @param rect is the rectangle
     * @return the closest intersection point, null is returned if the line does not intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        Double minDistance = null;
        //saving the intersection Points in a list.
        List<Point> pointsList = rect.intersectionPoints(this);
        //if the list is empty then there is no intersection Points
        if (pointsList.isEmpty()) {
            return null;
        }
        //going through the intersection Points and finding the closest.
        for (Point intersectPoint : pointsList) {
            if (minDistance == null) {
                minDistance = intersectPoint.distance(this.start);
                closestPoint = intersectPoint;
            } else {
                if (intersectPoint.distance(this.start) < minDistance) {
                    minDistance = intersectPoint.distance(this.start);
                    closestPoint = intersectPoint;
                }
            }
        }
        return closestPoint;
    }

    /**
     * checks if a point is part of a line.
     * @param point is the point.
     * @return the point if it's part of the line, null is returned otherwise.
     */
    public Point pointInLine(Point point) {
        if (this.slope == null) {
            //checking if the point is part of the line(which has no slope)
            if (Point.closeEnough(this.startInGraph.getX(), point.getX())
                    && Point.biggerOrCloseEnough(point.getY(), this.startInGraph.getY())
                    && Point.biggerOrCloseEnough(this.endInGraph.getY(), point.getY())) {
                return point;
            }
            return null;
        }
        //checking if the point is part of the line.
        if (Point.closeEnough(this.slope * point.getX() + this.getYAxis(),
                point.getY())
                && Point.biggerOrCloseEnough(point.getX(), this.startInGraph.getX())
                && Point.biggerOrCloseEnough(this.endInGraph.getX(), point.getX())) {
            return point;
        }
        return null;
    }
}