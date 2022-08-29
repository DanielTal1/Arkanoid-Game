package collections;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.util.LinkedList;
import java.util.List;

//ID:316081975
/**
 * SpriteCollection will hold a collection of sprites.
 *
 * @author Daniel Tal
 * @version 1.0
 */

public class SpriteCollection {
    //LinkedList to save all the Sprite objects
    private LinkedList<Sprite> spriteList;

    /**
     * constructor to create a new SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<>();
    }

    /**
     * adds a new sprite to the linked list.
     *
     * @param s is the new sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * removes the sprite from the linked list.
     *
     * @param s is the removed sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteListCopy = new LinkedList<>(this.spriteList);
        for (Sprite s : spriteListCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d is the DrawSurface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteListCopy = new LinkedList<>(this.spriteList);
        for (Sprite s : spriteListCopy) {
            s.drawOn(d);
        }
    }
}
