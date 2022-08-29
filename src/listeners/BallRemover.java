package listeners;
import gameinfo.Counter;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;

//ID:316081975
/**
 * HitListener that is in charge of removing balls.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    private Counter lives;

    /**
     * Constructor that creates a new BallRemover object from game and counter.
     *  @param gameLevel is the game that is running.
     * @param remainingBalls is the counter that indicates how many balls remaining.
     * @param lives
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls, Counter lives) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
        this.lives= lives;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
        if(this.remainingBalls.getValue()==0 && this.lives.getValue()>0){
            this.lives.decrease(1);
        }
    }
}
