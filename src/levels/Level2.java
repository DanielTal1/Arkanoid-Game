package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;
import sprites.Circle;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//ID:316081975
/**
 * Level2 is the information needed to create the second Level.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Level2 implements LevelInformation {

    /**
     * Constructor that creates a new Level2.
     */
    public Level2() {
        //do nothing
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArray = new ArrayList<>();
        int[] numbersArray = {4, 4};
        for (int i = 0; i < 2; i++) {
            Velocity velocity = new Velocity(Math.pow(-1,i)*numbersArray[i], Math.pow(-1,i)*numbersArray[numbersArray.length - i - 1]);
            velocityArray.add(velocity);
        }
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
        return "Sea World";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(30, 50), 740, 570),
                new Color(110, 151, 231));
    }
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        Color[] colourArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.CYAN, Color.PINK, Color.GREEN};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                Block block = new Block(new Rectangle(new Point(30 + j  * 74, 130 + i * 25),
                        74, 25), colourArray[i]);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public ArrayList<Sprite> fullBackground() {
        ArrayList<Sprite> backgroundSprites = new ArrayList<>();
        backgroundSprites.add(this.getBackground());
        backgroundSprites.add(new Circle(new Point(650, 450), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(680, 370), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(100, 450), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(170, 350), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(550, 100), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(200, 500), 20,
                Color.WHITE, new Color(248, 129, 1)));
        backgroundSprites.add(new Circle(new Point(218, 497), 3,
                Color.WHITE, Color.BLACK));
        backgroundSprites.add(new Circle(new Point(180, 495), 8,
                Color.WHITE, new Color(248, 129, 1)));
        backgroundSprites.add(new Circle(new Point(180, 508), 8,
                Color.WHITE, new Color(248, 129, 1)));

        backgroundSprites.add(new Circle(new Point(500, 430), 20, Color.WHITE, Color.GRAY));
        backgroundSprites.add(new Circle(new Point(482, 427), 3, Color.WHITE, Color.BLACK));
        backgroundSprites.add(new Circle(new Point(520, 425), 8, Color.WHITE, Color.GRAY));
        backgroundSprites.add(new Circle(new Point(520, 438), 8, Color.WHITE, Color.GRAY));
        backgroundSprites.add(new Circle(new Point(470, 415), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(230, 485), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(230, 485), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(230, 485), 5, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(30, 485), 10, Color.BLACK,
                new Color(1, 64, 19)));
        backgroundSprites.add(new Circle(new Point(30, 492), 10, Color.BLACK,
                new Color(1, 64, 19)));
        backgroundSprites.add(new Circle(new Point(770, 400), 10, Color.BLACK,
                new Color(1, 64, 19)));
        backgroundSprites.add(new Circle(new Point(770, 390), 10, Color.BLACK,
                new Color(1, 64, 19)));
        backgroundSprites.add(new Circle(new Point(770, 385), 10, Color.BLACK,
                new Color(1, 64, 19)));
        return backgroundSprites;
    }
}
