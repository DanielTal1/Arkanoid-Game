package sprites;
//ID:316081975
import biuoop.DrawSurface;
import animations.GameLevel;
import gameinfo.PaddleBorders;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;

import java.awt.Color;

/**
 * Paddle is a rectangle that is controlled by the arrow keys.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Paddle implements Sprite, Collidable {
    private static final int DEFAULT_SPEED = 4;
    private biuoop.KeyboardSensor keyboard;
    private int paddleSpeed;
    private Rectangle paddleShape;
    private java.awt.Color color;
    private PaddleBorders borders;


    /**
     * constructor that creates a new paddle with KeyboardSensor,speed,shape,color and left and right borders.
     *
     * @param keyboard is the KeyboardSensor that helps the user to control the paddle.
     * @param rectangle is the paddleShape.
     * @param color is the color of the paddle
     * @param borders is the right and left borders of movement.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, Color color, PaddleBorders borders) {
        this.keyboard = keyboard;
        this.paddleShape = rectangle;
        this.color = color;
        this.borders = borders;
        this.paddleSpeed = DEFAULT_SPEED;
    }

    /**
     * constructor that creates a new paddle with KeyboardSensor,speed,shape,color and left and right borders
     * and speed.
     *
     * @param keyboard is the KeyboardSensor that helps the user to control the paddle.
     * @param rectangle is the paddleShape.
     * @param color is the color of the paddle
     * @param borders is the right and left borders of movement.
     * @param paddleSpeed is the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, Color color, PaddleBorders borders,
                  int paddleSpeed) {
        this.keyboard = keyboard;
        this.paddleShape = rectangle;
        this.color = color;
        this.borders = borders;
        this.paddleSpeed = paddleSpeed;
    }


    /**
     * moves the paddle to the left if it's between the borders.
     */
    public void moveLeft() {
        //checks if the paddle can be moved to this direction
        if (paddleShape.getUpperLeft().getX() - this.paddleSpeed  < this.borders.getLeftBorder()) {
            return;
        }
        //changing the upper left point of the paddle.
        this.paddleShape.setUpperLeftPoint(new Point(paddleShape.getUpperLeft().getX()
                - this.paddleSpeed, paddleShape.getUpperLeft().getY()));
    }

    /**
     * moves the paddle to the right if it's between the borders.
     */
    public void moveRight() {
        //checks if the paddle can be moved to this direction
        if (paddleShape.upRightPoint().getX() + this.paddleSpeed > this.borders.getRightBorder()) {
            return;
        }
        //changing the upper left point of the paddle.
        this.paddleShape.setUpperLeftPoint(new Point(paddleShape.getUpperLeft().getX()
                + this.paddleSpeed, paddleShape.getUpperLeft().getY()));
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            this.paddleSpeed = DEFAULT_SPEED;
            moveLeft();
        } else if (this.keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY)) {
            this.paddleSpeed = DEFAULT_SPEED;
            moveRight();
        } else {
            this.paddleSpeed = 0;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        //first drawing the frame in black
        d.setColor(Color.BLACK);
        d.drawRectangle((int) Math.round(this.paddleShape.getUpperLeft().getX()),
                (int) Math.round(this.paddleShape.getUpperLeft().getY()),
                (int) Math.round(this.paddleShape.getWidth()), (int) Math.round(this.paddleShape.getHeight()));
        d.setColor(this.color);
        //filling the frame with colour.
        d.fillRectangle((int) Math.round(this.paddleShape.getUpperLeft().getX()),
                (int) Math.round(this.paddleShape.getUpperLeft().getY()),
                (int) Math.round(this.paddleShape.getWidth()), (int) Math.round(this.paddleShape.getHeight()));
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        final int startingAngle = 300;
        final int angleChange = 30;

        /*
        if the dy velocity of the ball is negative and it hits the paddle then it just hit the floor,
        null is returned to make sure that the ball won't get trapped in the paddle
         */
        if (currentVelocity.getDy() < 0) {
            return null;
        }
        //checking if the ball hits the paddle in the sides.
        if ((this.paddleShape.getLeftBorder().pointInLine(collisionPoint) != null)
                || (this.paddleShape.getRightBorder().pointInLine(collisionPoint) != null)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        double partOfPaddle = collisionPoint.getX() - this.paddleShape.getUpperLeft().getX();
        //splitting the paddle to 5 equal parts.
        for (int i = 1; i <= 5; i++) {
            if (partOfPaddle < i * this.paddleShape.getWidth() / 5) {
                if (i == 3) {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
                //returning new velocity according to the part of the paddle which the ball hit
                return (Velocity.fromAngleAndSpeed(startingAngle + angleChange * (i - 1),
                        currentVelocity.getSpeedUnit()));
            }
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public int getLocation(){
        return (int)(this.paddleShape.getUpperLeft().getX()+this.paddleShape.getWidth()/2);
    }
}