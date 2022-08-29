package sprites;
import biuoop.DrawSurface;
import geometry.Point;
import java.awt.Color;

//ID:316081975
/**
 * Circle is the a Sprite of a Circle that can be drawn on the screen.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Circle implements Sprite {
    private int radius;
    private Point center;
    private Color colorBorder;
    private Color colorFill = null;

    /**
     * Constructor that creates a new Circle.
     *
     * @param center is the center.
     * @param radius is the radius.
     * @param colorBorder is the Color of the Borders.
     * @param colorFill is the Color inside the circle
     */
    public Circle(Point center, int radius, Color colorBorder, Color colorFill) {
        this.radius = radius;
        this.center = center;
        this.colorBorder = colorBorder;
        this.colorFill = colorFill;
    }

    /**
     * Constructor that creates a new Circle.
     *
     * @param center is the center.
     * @param radius is the radius.
     * @param colorBorder is the Color of the Borders.
     */
    public Circle(Point center, int radius, Color colorBorder) {
        this.radius = radius;
        this.center = center;
        this.colorBorder = colorBorder;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.colorFill != null) {
            d.setColor(this.colorFill);
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
        d.setColor(this.colorBorder);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        //do nothing;
    }
}
