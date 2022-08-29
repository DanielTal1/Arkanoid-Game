package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collections.GameEnvironment;
import collections.SpriteCollection;
import gameinfo.Counter;
import gameinfo.PaddleBorders;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import levels.LevelInformation;
import sprites.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import java.awt.Color;
import java.util.ArrayList;

//ID:316081975
/**
 * GameLevel holds the logic behind the levels.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter score;
    private AnimationRunner animationRunner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;
    private Counter numberOfBalls;
    private Counter numberOfBlocks;
    private Counter lives;
    private Paddle paddle;

    /**
     * Constructor that creates a new GameLevel.
     *
     * @param levelInfo is the level information.
     * @param ar is the AnimationRunner.
     * @param ks is the KeyboardSensor.
     * @param score is a Counter that keeps the score.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor ks,
                     Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInfo;
        this.score = score;
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.numberOfBalls = new Counter(this.levelInfo.numberOfBalls());
        this.numberOfBlocks = new Counter(this.levelInfo.blocks().size());
        this.running = true;
        this.lives = lives;
        this.paddle = null;
    }

    /**
     * addCollidable adds a new Collidable to the environment.
     *
     * @param c is the added Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addCollidable adds a new Sprite to the environment.
     *
     * @param s is the added Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game: create the blocks,balls and paddle and adds them to the game.
     */
    public void initialize() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.levelInfo.levelName(), this.lives);
        BlockRemover removeBlocks = new BlockRemover(this, this.numberOfBlocks);
        BallRemover removeBalls = new BallRemover(this, this.numberOfBalls,this.lives);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        //adding the sprites of the background
        for (Sprite backgroundSprite : this.levelInfo.fullBackground()) {
            sprites.addSprite(backgroundSprite);
        }
        //initializing the borders
        Block rightBorder = new Block(new Rectangle(new Point(770, 50), 30, 570), Color.GRAY);
        Block leftBorder = new Block(new Rectangle(new Point(0, 50), 30, 570), Color.GRAY);
        Block deathBlock = new Block(new Rectangle(new Point(0, 620), 800, 0), Color.GRAY);
        Block topBorder = new Block(new Rectangle(new Point(0, 20), 800, 30), Color.GRAY);
        PaddleBorders leftRightBorders = new PaddleBorders(leftBorder.getCollisionRectangle().upRightPoint().getX(),
                rightBorder.getCollisionRectangle().getUpperLeft().getX());
        //setting the paddle
        Paddle paddle = new Paddle(this.keyboardSensor, new Rectangle(this.levelInfo.paddleStart(),
                this.levelInfo.paddleWidth(), 15), Color.ORANGE, leftRightBorders,
                this.levelInfo.paddleSpeed());
        //adding the borders to the game.
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        topBorder.addToGame(this);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(removeBalls);
        for (Block block : this.levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(removeBlocks);
            block.addHitListener(scoreTracker);
        }
        //adding the paddle and balls to the game
        paddle.addToGame(this);
        this.paddle = paddle;
        scoreIndicator.addToGame(this);
    }

    /**
     * removes the Collidable object from the game.
     *
     * @param c is the removed Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes the Sprite object from the game.
     *
     * @param s is the removed Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //drawing the background and the sprites;
        this.sprites.drawAllOn(d);
        //notify that time passed in order for the sprites objects to move
        this.sprites.notifyAllTimePassed();
        if (this.levelInfo.blocks().size() - this.numberOfBlocks.getValue()
                >= this.levelInfo.numberOfBlocksToRemove()) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.numberOfBalls.getValue() == 0 && this.lives.getValue()>0) {
            this.oneBallOnPaddle();
        }
        else if(this.numberOfBalls.getValue()==0){
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.animationRunner.run(new PauseScreen(this.keyboardSensor));
        }
    }

    /**
     * creates the balls, runs the CountdownAnimation and calling animationRunner to run the level.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.animationRunner.run(new CountdownAnimation(2, 3, this.sprites));
        this.animationRunner.run(this);
    }

    /**
     * creates the balls on top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        ArrayList<Velocity> velocityArray = checksVelocityList(
                (ArrayList<Velocity>) this.levelInfo.initialBallVelocities());
        for (int i = 1; i <= velocityArray.size(); i++) {
            Ball ball = new Ball(this.levelInfo.paddleStart().getX()
                    + (float) this.levelInfo.paddleWidth() / 2,
                    this.levelInfo.paddleStart().getY() - 10,
                    6, Color.white);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(velocityArray.get(i - 1));
            ball.addToGame(this);
        }
    }

    public void oneBallOnPaddle() {
        Ball ball = new Ball(new Point(this.paddle.getLocation(),500),6, Color.white);
        this.numberOfBalls.increase(1);
        ball.setGameEnvironment(this.environment);
        ball.setVelocity(new Velocity(1,5));
        ball.addToGame(this);
    }

    /**
     * checks if the number of velocities in the list of velocities equals the number of balls.
     * if it isn't the function changes the list of velocities accordingly.
     *
     * @param velocitiesToCheck is the list of velocities.
     * @return the new velocity List.
     */
    public ArrayList<Velocity> checksVelocityList(ArrayList<Velocity> velocitiesToCheck) {
        if (velocitiesToCheck.size() == this.numberOfBalls.getValue()) {
            return velocitiesToCheck;
        } else if (velocitiesToCheck.size() > this.numberOfBalls.getValue()) {
            return new ArrayList<>(velocitiesToCheck.subList(0, this.numberOfBalls.getValue()));
        } else {
            int difference = this.numberOfBalls.getValue() - velocitiesToCheck.size();
            for (int i = 0; i < difference; i++) {
                velocitiesToCheck.add(new Velocity(i + 2,  -i - 2));
            }
            return velocitiesToCheck;
        }
    }

    /**
     * @return Counter that holds the numberOfBalls in the level.
     */
    public Counter getNumberOfBalls() {
        return numberOfBalls;
    }
}

