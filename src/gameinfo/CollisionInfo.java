package gameinfo;
import geometry.Point;
import interfaces.Collidable;

//ID:316081975
/**
 * CollisionInfo holds the the information about the collision.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor that creates CollisionInfo using collisionPoint and collisionObject.
     * @param collisionPoint is the point of collision.
     * @param collisionObject is the object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collisionObject involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
