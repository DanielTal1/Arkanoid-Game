package sprites;
import biuoop.DrawSurface;

//ID:316081975
/**
 * a Sprite is a game object that can be drawn to the screen.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d is the DrawSurface on which the sprite will be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
