package listeners;
//ID:316081975

import gameinfo.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * Updates the value of the game score.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor that creates a new ScoreTrackingListener from score counter.
     *
     * @param scoreCounter is the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}