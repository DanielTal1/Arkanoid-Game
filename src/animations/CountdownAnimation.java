package animations;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import collections.SpriteCollection;

import java.awt.*;

//ID:316081975
/**
 * Shows countdown before each level starts.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean running;
    private int currentCount;

    /**
     * Constructor that creates a new CountdownAnimation object.
     *
     * @param numOfSeconds is the number of seconds that CountdownAnimation will be displayed.
     * @param countFrom is the start of the count.
     * @param gameScreen is the gameScreen on which the CountdownAnimation is displayed.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new biuoop.Sleeper();
        this.running = true;
        this.currentCount = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        double millisecondsPerFrame =  (this.numOfSeconds / this.countFrom) * 1000;
        long startTime = System.currentTimeMillis();
        //drawing the background and the sprites
        this.gameScreen.drawAllOn(d);
        if (this.currentCount == 0) {
            d.drawText(370, d.getHeight() / 2 + 50, "GO", 55);
        } else if (this.currentCount > 0) {
            d.drawText(375, d.getHeight() / 2 + 50, String.valueOf(this.currentCount), 55);
        }
        d.drawText(265,d.getHeight() -200,"Press 'p' to pause during game",20);
        if (this.currentCount != this.countFrom) {
            long usedTime = System.currentTimeMillis() - startTime;
            //if theres time to sleep then sleeper is used
            long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.currentCount == -1) {
            this.running = false;
        }
        this.currentCount--;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}