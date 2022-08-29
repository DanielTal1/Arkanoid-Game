package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class HighScoresScreen extends KeyPressStoppableAnimation {
    private HighScore highScore;

    public HighScoresScreen(KeyboardSensor k, HighScore  highScore) {
        super(k, k.SPACE_KEY);
        this.highScore = highScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(34, 22, 85));
        d.fillRectangle(0,0,800,600);
        for(int i=2; i<60 ; i++){
            d.setColor(MenuAnimation.getRandomColor());
            if(i*10>600){
                break;
            }
            d.fillCircle(10, i*10,5);
            d.fillCircle(790,i*10,5);
        }
        d.fillCircle(10,10,5);
        for(int i=0; i<78 ; i++){
            d.setColor(MenuAnimation.getRandomColor());
            d.fillCircle(i*10+20, 590,5);
            d.fillCircle(i*10+20, 10,5);

        }
        d.setColor(Color.red);
        d.drawText(165, (d.getHeight() / 2)-100, "HighScore : "+this.highScore.findHighscore(), 70);
        d.setColor(Color.WHITE);
        d.drawText(165, 3 * d.getHeight() / 4-50, "press space to return to the menu",32);

        super.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return super.shouldStop();
    }
}
