package levels;
import geometry.Point;
import geometry.Velocity;
import sprites.Sprite;
import sprites.Block;
import java.util.ArrayList;
import java.util.List;

//ID:316081975
/**
 * LevelInformation is the information needed to create each level.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface LevelInformation {
    /**
     * @return the numberOfBalls.
     */
    int numberOfBalls();

    /**
     * @return list with the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level - without drawing on it.
     */
    Sprite getBackground();

    /**
     * @return list of the the blocks.
     * each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return the location of the paddle at the start of the game.
     */
    Point paddleStart();

    /**
     * @return list with all the sprites that will be shown in the background.
     */
    ArrayList<Sprite> fullBackground();
}