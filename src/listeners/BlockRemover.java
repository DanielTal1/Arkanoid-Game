package listeners;
import gameinfo.Counter;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;

//ID:316081975
/**
 * BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor the creates a new BlockRemover object from game and counter.
     *
     * @param gameLevel is the game.
     * @param removedBlocks is the counter that indicates how many block remain in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * removes the blocks that are hit from the game.
     *
     * @param beingHit is the block that been hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}