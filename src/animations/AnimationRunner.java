package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

//ID:316081975
/**
 * AnimationRunner takes an Animation object and runs it.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor that creates an AnimationRunner object from GUI.
     *
     * @param gui is the GUI.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new biuoop.Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * runs the animation.
     *
     * @param animation is the Animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}