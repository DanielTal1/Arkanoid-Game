package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import java.awt.Color;


//ID:316081975
/**
 * LineDraw is the a Sprite of a Line that can be drawn on the screen.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class LineDraw implements Sprite {
    private Line line;
    private Color color;

    /**
     * Constructor that creates a new LineDraw.
     *
     * @param line is the line.
     * @param color is the color of the line.
     */
    public LineDraw(Line line, Color color) {
        this.line = line;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) line.start().getX(), (int) line.start().getY(), (int) line.end().getX(),
                (int) line.end().getY());
    }
    @Override
    public void timePassed() {
        //do nothing
    }
}
