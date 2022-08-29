package sprites;
import biuoop.DrawSurface;
import gameinfo.Counter;
import animations.GameLevel;

import java.awt.Color;

//ID:316081975
/**
 * Displays the current game score.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class ScoreIndicator implements Sprite {
    private final String levelName;
    private Counter score;
    private Counter lives;

    /**
     * Constructor that creates a new ScoreIndicator from a score Counter.
     *
     * @param score is the score Counter.
     * @param levelName is the name of the level
     */
    public ScoreIndicator(Counter score, String levelName, Counter lives) {
        this.score = score;
        this.levelName = levelName;
        this.lives =  lives;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle(0, 0, 800, 20);
        surface.setColor(Color.BLACK);
        surface.drawText(350, 15, "Score: " + this.score.getValue(), 18);
        surface.drawText(500, 15, "Level Name: " + this.levelName, 18);
        surface.drawText(100,15,"lives: "+this.lives.getValue(),18);
    }

    /**
     * adds the sprite to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        //do nothing
    }
}
