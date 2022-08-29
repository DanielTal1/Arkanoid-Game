package sprites;
import biuoop.DrawSurface;
import animations.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import listeners.HitListener;
import interfaces.HitNotifier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//ID:316081975
/**
 * Block is a obstacle that appears on the screen.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockShape;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor that creates a block.
     *
     * @param rectangle is the Block shape
     * @param color is the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.blockShape = rectangle;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * @return the rectangle that the block is made of.
     */
    public Rectangle getCollisionRectangle() {
        return this.blockShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        if (this.blockShape.getBottomBorder().pointInLine(collisionPoint) != null
                || this.blockShape.getTopBorder().pointInLine(collisionPoint) != null) {
            newDy = -newDy;
        }
        if (this.blockShape.getLeftBorder().pointInLine(collisionPoint) != null
                || this.blockShape.getRightBorder().pointInLine(collisionPoint) != null) {
            newDx = -newDx;
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) (this.blockShape.getUpperLeft().getX()),
                (int) (this.blockShape.getUpperLeft().getY()),
                (int) (this.blockShape.getWidth()), (int) (this.blockShape.getHeight()));
        surface.setColor(Color.black);
        surface.drawRectangle((int) (this.blockShape.getUpperLeft().getX()),
                (int) (this.blockShape.getUpperLeft().getY()),
                (int) (this.blockShape.getWidth()),
                (int) (this.blockShape.getHeight()));
    }

    @Override
    public void timePassed() {
        //do nothing
    }

    /**
     * adds the block to the game.
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @return the color of the block.
     */
    public Color color() {
        return this.color;
    }

    /**
     * notifies all of the registered HitListener objects whenever a hit() occurs.
     *
     * @param hitter is the ball that hits the block.
     */
    public void notifyHit(Ball hitter) {
        // Makes a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * removes the block from the game.
     *
     * @param gameLevel is the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
