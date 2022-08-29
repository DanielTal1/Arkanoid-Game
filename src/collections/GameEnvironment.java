package collections;
import gameinfo.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import java.util.LinkedList;
import java.util.List;

//ID:316081975
/**
 * CollisionInfo holds the the information about the collision.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class GameEnvironment {
    //LinkedList to save all the Collidable objects
    private LinkedList<Collidable> collidableList;

    /**
     * constructor that creates a new GameEnvironment.
     */
    public GameEnvironment() {
        collidableList = new LinkedList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c is the collidable added
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * removes the collidable from the linked list.
     *
     * @param c is the removed collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * finds the closest collision of an object according to the object trajectory.
     *
     * @param trajectory is the path in which the object moves. starting from line.start() to line.end().
     * @return the information about the closest collision that is going to occur,
     * null is returned if the object will not collide with with any of the collidables.
     * in this collection
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Double minDistance = null;
        Point p;
        Point intersectionPoint = null;
        List<Collidable> collidableListCopy = new LinkedList<>(this.collidableList);
        //for loop that go through all the Collidables
        for (Collidable c: collidableListCopy) {
            //checking the closest intersection from start of the trajectory of the object and Collidables.
            p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                // saving the first Collidable found and changing it until the closest is found.
                if (closestCollidable == null || trajectory.start().distance(p) < minDistance) {
                    closestCollidable = c;
                    minDistance = trajectory.start().distance(p);
                    intersectionPoint = p;
                }
            }
        }
        //if there is no collision null is returned.
        if (minDistance == null) {
            return null;
        }
        //return the information about the collision
        return new CollisionInfo(intersectionPoint, closestCollidable);
    }
}
