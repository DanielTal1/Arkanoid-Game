package sprites;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import gameinfo.CollisionInfo;
import animations.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import java.util.Random;
import java.awt.Color;

//ID:316081975
/**
 * ball which is a moving circle.
 *
 * @author Daniel Tal
 * @version 1.2
 */

public class Ball implements Sprite {
    private static final int DEFAULT_SPEED = 5;
    private int radius;
    private java.awt.Color color;
    private Point center;
    private Velocity speed;
    private GameEnvironment environment;

    /**
     * Constructor that creates a ball based on it's center point, radius and color.
     *
     * @param center is the center point of the ball.
     * @param r      is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.center = center;
        //default speed
        this.speed = new Velocity(DEFAULT_SPEED, DEFAULT_SPEED);
    }

    /**
     * Constructor that creates a ball based on it's center x coordinate,center y coordinate, radius and color.
     *
     * @param centerX is the center x coordinate.
     * @param centerY is the center y coordinate.
     * @param r       is the radius of the ball.
     * @param color   is the color of the ball.
     */
    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this.center = new Point(centerX, centerY);
        this.radius = r;
        this.color = color;
        //default speed
        this.speed = new Velocity(DEFAULT_SPEED,  DEFAULT_SPEED);
    }

    /**
     * @return the int value of ball's center x coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the int value of ball's center y coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface is the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());

    }

    /**
     * sets the velocity of the ball to the given Velocity.
     *
     * @param v is the required Velocity.
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * sets the velocity of the ball to the velocity that created by dx and dy.
     *
     * @param dx is the change in position on the x axes.
     * @param dy is the change in position on the y axes.
     */
    public void setVelocity(double dx, double dy) {
        this.speed = new Velocity(dx, dy);
    }

    /**
     * @return the Velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.speed;
    }


    /**
     * moves the ball according to it's speed and the objects surrounding it.
     */
    public void moveOneStep() {
        final double radiusExtension = 1.3;
        Velocity newVelocity;

        /*
        calculating the end point of the ball starting from the ball center and ending
        at the perimeter of the ball after it moved one step.
        the ending point is the extension of the line between the old center and new center.
        that way the radius is taken into account.
         */
        Point expectedEndPoint = new Point(this.center.getX()
                + Math.abs(Math.sin(this.speed.getAngleInRadians())) * Math.signum(this.speed.getDx())
                * (this.speed.getSpeedUnit() + this.radius * radiusExtension),
                this.center.getY() + Math.signum(this.speed.getDy())
                        * Math.abs(Math.cos(this.speed.getAngleInRadians()))
                        * (this.speed.getSpeedUnit() + this.radius * radiusExtension));
        Line trajectory = new Line(this.center, expectedEndPoint);
        //finding the closest collision object and collision point
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        // if info is null then there are no objects close to the ball and it can move as usual
        if (info == null) {
            this.center = getVelocity().applyToPoint(this.center);
        } else {

            /*
            checking if the ball is trapped inside the collision object.
            the only case for it to happen is when the paddle moves to the location the ball was.
            in that case the ball is moved away from the paddle.
             */
            if (info.collisionObject().getCollisionRectangle().pointInRectangle(this.center)) {
                this.center = new Point(this.center.getX() + this.speed.getDx(),
                        info.collisionObject().getCollisionRectangle().downLeftPoint().getY() + 1);
                newVelocity = new Velocity(this.speed.getDx(), Math.abs(this.speed.getDy()));
                setVelocity(newVelocity);
                return;
            }
            //finding the new velocity after the ball hits the object
             newVelocity = (info.collisionObject().hit(this, info.collisionPoint(), this.speed));

            /*
            null is returned only when the ball hits the paddle right after the ball hits
            the floor. in that case the ball hits the left or right sides of paddle.
             */
            if (newVelocity == null) {
                this.center = new Point(info.collisionPoint().getX()
                        - Math.signum(info.collisionPoint().getX() - this.center.getX())
                        * this.radius, info.collisionPoint().getY());
                newVelocity = new Velocity(-this.speed.getDx(), this.speed.getDy());
            } else {
                //changing the center of the ball so it will be close to the collision point.
                this.center = new Point(info.collisionPoint().getX()
                        - Math.abs(Math.sin(this.speed.getAngleInRadians())) * Math.signum(this.speed.getDx())
                        * this.radius * radiusExtension, info.collisionPoint().getY()
                        - Math.abs(Math.cos(this.speed.getAngleInRadians()))
                        * Math.signum(this.speed.getDy()) * this.radius * radiusExtension);
            }
            //changing the velocity
            setVelocity(newVelocity);
        }
    }

    /**
     * randomly generating a color.
     *
     * @return the new created color
     */
    public static Color randomColor() {
        Random rand = new Random();
        //generating 3 random numbers between 0 and 1
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        //colors are made with 3 numbers
        return new Color(red, green, blue);
    }

    /**
     * adds the ball to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * removes the ball from the game.
     *
     * @param g is the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * sets the GameEnvironment for the ball.
     * @param environmentOfGame is the GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environmentOfGame) {
        this.environment = environmentOfGame;
    }
}
