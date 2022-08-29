package interfaces;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import sprites.Ball;

//ID:316081975
/**
 * The Collidable interface will be used by things that can be collided with.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter is the ball that collides with the object.
     * @param collisionPoint is the point where the object collided with.
     * @param currentVelocity is the velocity of the object that collided with.
     * @return the new velocity of the object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}