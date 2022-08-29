package levels;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Circle;
import sprites.LineDraw;
import sprites.Sprite;
import sprites.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//ID:316081975
/**
 * Level1 is the information needed to create the first Level.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Level1 implements LevelInformation {

/**
     * Constructor that creates a new Level1.
     */
    public Level1() {
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity = new Velocity(0, 6);
        ArrayList<Velocity> velocityArray = new ArrayList<>();
        velocityArray.add(velocity);
        return velocityArray;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public Point paddleStart() {
        return new Point(345, 570);
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(30, 50), 740, 570),
                new Color(95, 154, 24));
    }
    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(400, 150), 30, 30), Color.RED);
        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public ArrayList<Sprite> fullBackground() {
        ArrayList<Sprite> backgroundSprites = new ArrayList<>();
        backgroundSprites.add(this.getBackground());
        backgroundSprites.add(new Circle(new Point(415, 165), 120, Color.BLACK, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(415, 165), 110, Color.WHITE, Color.BLACK));
        backgroundSprites.add(new Circle(new Point(415, 165), 100, Color.WHITE, Color.pink));
        backgroundSprites.add(new Circle(new Point(415, 165), 90, Color.BLACK, Color.ORANGE));
        backgroundSprites.add(new Circle(new Point(415, 165), 80, Color.BLACK,
                new Color(18, 139, 208)));
        backgroundSprites.add(new Circle(new Point(415, 165), 70, Color.BLACK,
                new Color(18, 139, 208)));
        backgroundSprites.add(new Circle(new Point(415, 165), 60, Color.BLACK, Color.RED));
        backgroundSprites.add(new Circle(new Point(415, 165), 50, Color.BLACK, Color.RED));
        backgroundSprites.add(new Circle(new Point(415, 165), 40, Color.BLACK, Color.yellow));
        backgroundSprites.add(new Circle(new Point(415, 165), 30, Color.BLACK, Color.yellow));
        backgroundSprites.add(new LineDraw(new Line(270, 165, 390, 165), Color.BLUE));
        backgroundSprites.add(new LineDraw(new Line(440, 165, 560, 165), Color.BLUE));
        backgroundSprites.add(new LineDraw(new Line(415, 50, 415, 140), Color.BLUE));
        backgroundSprites.add(new LineDraw(new Line(415, 190, 415, 310), Color.BLUE));
        return backgroundSprites;
    }
}
