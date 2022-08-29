package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameinfo.Counter;

import java.awt.*;

//ID:316081975
/**
 * GameOverScreen is the screen shown when the player loses.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class GameOverScreen extends KeyPressStoppableAnimation {
    private Counter score;


    /**
     * Constructor that creates a new GameOverScreen.
     *
     * @param k is the KeyboardSensor.
     * @param score is a Counter that holds the score.
     */
    public GameOverScreen(KeyboardSensor k, Counter score) {
        super(k, k.SPACE_KEY);
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(34, 22, 85));
        d.fillRectangle(0,0,800,600);
        for(int i=2; i<60 ; i++){
            d.setColor(new Color(128, 193, 252));
            if(i*10>600){
                break;
            }
            d.fillCircle(10, i*10,5);
            d.fillCircle(790,i*10,5);
        }
        d.fillCircle(10,10,5);
        for(int i=0; i<78 ; i++){
            d.fillCircle(i*10+20, 590,5);
            d.fillCircle(i*10+20, 10,5);

        }
        d.drawText(180, (d.getHeight() / 2) - 100, "Game Over. Your score is "
                + this.score.getValue(), 32);
        d.drawText(210, 3 * d.getHeight() / 4, "press space to continue", 32);
        super.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return super.shouldStop();
    }
}

