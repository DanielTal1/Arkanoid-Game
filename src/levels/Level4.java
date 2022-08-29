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
public class Level4 implements LevelInformation {
    /**
     * Constructor that creates a new Level3.
     */
    public Level4() {
        //do nothing
    }
    @Override
    public int numberOfBalls() {
        return 4;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArray = new ArrayList<>();
        int[] numbersArray = {4, 3, 4, 4};
        for (int i = 0; i < 4; i++) {
            Velocity velocity = new Velocity(numbersArray[i], -numbersArray[numbersArray.length - i - 1]);
            velocityArray.add(velocity);
        }
        return velocityArray;
    }

    @Override
    public int paddleSpeed() {
        return 5;
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
        return "Stairway To Heaven";
    }


    @Override
    public Sprite getBackground() {
        new Color(92, 86, 86);
        return new Block(new Rectangle(new Point(30, 50), 740, 570),
                new Color(78, 111, 199));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        //adding the blocks
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Rectangle(new Point(30 + j * 74, 50 + (i + j) * 25),
                        74, 25), Color.PINK);
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
        backgroundSprites.add(new Circle(new Point(700, 80), 80, Color.WHITE,
                new Color(215, 229, 21)));
        backgroundSprites.add(new Circle(new Point(700, 80), 83,
                new Color(215, 229, 21)));
        backgroundSprites.add(new Circle(new Point(700, 80), 86,
                new Color(215, 229, 21)));
        backgroundSprites.add(new Circle(new Point(700, 80), 89,
                new Color(215, 229, 21)));
        Color[] colorArray = {new Color(141, 134, 134),
                new Color(137, 132, 132), new Color(104, 101, 101),
                new Color(132, 131, 131)};
        for (int i = 0; i < 6; i++) {
            backgroundSprites.add(new Circle(new Point(80 + i * 50, 80), 30,
                    Color.WHITE, colorArray[1]));
            backgroundSprites.add(new Circle(new Point(90 + i * 50, 70), 30,
                    Color.WHITE, colorArray[2]));
            backgroundSprites.add(new Circle(new Point(98 + i * 50, 95), 30,
                    Color.WHITE, colorArray[3]));
            backgroundSprites.add(new Circle(new Point(105 + i * 50, 105), 30,
                    Color.WHITE, colorArray[0]));
            backgroundSprites.add(new Circle(new Point(80 + i * 50, 115), 30,
                    Color.WHITE, colorArray[0]));
        }
        for (int i = 0; i < 3; i++) {
            backgroundSprites.add(new Circle(new Point(580 + i * 50, 380), 30,
                    Color.WHITE, colorArray[1]));
            backgroundSprites.add(new Circle(new Point(590 + i * 50, 370), 30,
                    Color.WHITE, colorArray[2]));
            backgroundSprites.add(new Circle(new Point(59 + i * 50, 395), 30,
                    Color.WHITE, colorArray[3]));
            backgroundSprites.add(new Circle(new Point(605 + i * 50, 405), 30,
                    Color.WHITE, colorArray[0]));
            backgroundSprites.add(new Circle(new Point(580 + i * 50, 415), 30,
                    Color.WHITE, colorArray[0]));
        }
        return backgroundSprites;
    }
}
