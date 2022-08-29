package listeners;
import sprites.Ball;
import sprites.Block;

//ID:316081975
/**
 * The HitListener interface indicates that objects that implement it get notifications
 * when HitNotifier objects are being it.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that been hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}