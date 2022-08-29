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
 * Level3 is the information needed to create the third Level.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Level3 implements LevelInformation {

    /**
     * Constructor that creates a new Level3.
     */
    public Level3() {
        //do nothing
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArray = new ArrayList<>();
        int[] numbersArray = {5, -4, 3};
        for (int i = 0; i < 3; i++) {
            Velocity velocity = new Velocity(numbersArray[i], numbersArray[numbersArray.length - i - 1]);
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
        return "City Nights";
    }


    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(30, 50), 740, 570),
                new Color(0, 0, 0));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<>();
        //adding the blocks
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9 - i; j++) {
                Block block = new Block(new Rectangle(new Point(158 + (i + j) * 68, 130 + i * 25),
                        68, 25), new Color(198, 198, 23));
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
        backgroundSprites.add(new Block(new Rectangle(new Point(145, 360), 30, 40),
                Color.GRAY));
        backgroundSprites.add(new Block(new Rectangle(new Point(100, 400), 120, 200),
                Color.GRAY));
        backgroundSprites.add(new Block(new Rectangle(new Point(155, 190), 10, 170),
                Color.GRAY));
        backgroundSprites.add(new Circle(new Point(160, 177), 15, Color.BLACK,
                new Color(206, 68, 32)));
        backgroundSprites.add(new Circle(new Point(160, 177), 10, Color.BLACK,
                new Color(201, 40, 40)));
        backgroundSprites.add(new Circle(new Point(160, 177), 5, Color.BLACK, Color.WHITE));
        backgroundSprites.add(new Circle(new Point(80, 80), 80, Color.WHITE,
                new Color(163, 157, 157)));
        backgroundSprites.add(new Circle(new Point(100, 80), 10, new Color(163, 157, 157),
                new Color(184, 173, 173)));
        backgroundSprites.add(new Circle(new Point(70, 110), 5, new Color(163, 157, 157),
                new Color(184, 173, 173)));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                backgroundSprites.add(new Block(new Rectangle(new Point(110 + 20 * i, 410 + 35 * j),
                        15, 15),
                        new Color(198, 198, 23)));
            }
        }
        return backgroundSprites;
    }
}

