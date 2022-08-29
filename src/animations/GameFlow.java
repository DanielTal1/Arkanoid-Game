package animations;
import biuoop.KeyboardSensor;
import gameinfo.Counter;
import levels.LevelInformation;
import java.util.List;

//ID:316081975
/**
 * GameFlow creates the different levels, and moves from one level to the next.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class GameFlow implements Task<Void>{
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private List<LevelInformation> levels;
    private Counter lives;

    /**
     * Constructor that creates a new GameFlow object.
     *
     * @param ar is the AnimationRunner.
     * @param ks is the KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks,List<LevelInformation> levels) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.levels = levels;
        this.lives = new Counter(3);
    }

    /**
     * runs the levels one after another.
     *
     */
    public void runLevels() {
        HighScore highScore = new HighScore();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score, this.lives);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if(this.lives.getValue() == 0){
                highScore.updateHighScore(this.score.getValue());
                gameOver();
                return;
            }
        }
        highScore.updateHighScore(this.score.getValue());
        youWin();
    }

    /**
     * gameOver runs the GameOver Screen.
     */
    public void gameOver() {
        GameOverScreen gameOverScreen = new GameOverScreen(this.keyboardSensor, this.score);
        HighScoresScreen highScoresScreen = new HighScoresScreen(this.keyboardSensor, new HighScore());
        this.animationRunner.run(gameOverScreen);
        this.score = new Counter(0);
        this.animationRunner.run(highScoresScreen);
    }
    /**
     * youWin runs the YouWin Screen.
     */
    public void youWin() {
        YouWinScreen winScreen = new YouWinScreen(this.keyboardSensor, this.score);
        this.animationRunner.run(winScreen);
        HighScoresScreen highScoresScreen = new HighScoresScreen(this.keyboardSensor, new HighScore());
        this.score = new Counter(0);
        this.animationRunner.run(highScoresScreen);
    }

    @Override
    public Void run() {
        this.runLevels();
        return null;
    }
}

