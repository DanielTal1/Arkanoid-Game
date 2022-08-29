package animations;
import biuoop.DrawSurface;

//ID:316081975
/**
 * Animation is an object that controls the animation running on the screen.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface Animation {

    /**
     * controls the actions of the animation in each frame.
     *
     * @param d is the DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * indicates if the animation should stop running.
     *
     * @return true if the animation should stop or false if the animation should continue running.
     */
    boolean shouldStop();
}