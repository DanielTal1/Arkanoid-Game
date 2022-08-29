package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuAnimation<T> implements Menu<T>{
    private ArrayList<Triple<T>> selectionOptions;
    private KeyboardSensor keyboard;
    private Boolean stop;
    private List<Color> ColorArray;

    public MenuAnimation(KeyboardSensor keyboard){
        this.selectionOptions = new ArrayList<>();
        this.keyboard = keyboard;
        this.stop = false;
        this.ColorArray= getRandomColorsArray();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(34, 22, 85));
        d.fillRectangle(0,0,800,600);
        for(int i=2; i<60 ; i++){
            d.setColor(this.ColorArray.get(i));
            if(i*10>600){
                break;
            }
            d.fillCircle(10, i*10,5);
            d.fillCircle(790,i*10,5);
        }
        d.fillCircle(10,10,5);
        for(int i=0; i<78 ; i++){
            d.setColor(this.ColorArray.get(i));
            d.fillCircle(i*10+20, 590,5);
            d.fillCircle(i*10+20, 10,5);

        }
        d.setColor(new Color(241, 78, 72));
        d.drawText(191, 120, "ARKANOID", 80);
        d.drawText(40, 3 * d.getHeight() / 4+100, "made by Daniel Tal", 20);
        d.setColor(Color.WHITE);
        d.drawText(180, (d.getHeight() / 2) - 100, "Press 's' to start a new game.", 32);
        d.drawText(165, (d.getHeight() / 2) + 15, "Press 'h' to see the highest score", 32);
        d.drawText(265, 3 * d.getHeight() / 4-20, "Press 'q' to quit.", 32);
        for (Triple<T> option : this.selectionOptions) {
            String currentKey = option.getKey();
            if (this.keyboard.isPressed(currentKey)) {
                this.stop = true;
            }
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selectionOptions.add(new Triple(key,message,returnVal));
    }

    @Override
    public T getStatus() {
        for(Triple<T> option:this.selectionOptions) {
            String currentKey= option.getKey();
            if (this.keyboard.isPressed(currentKey)){
                this.stop = false;
                return option.getReturnVal();
            }
        }
        return null;
    }

    public static Color getRandomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r,g,b);
    }
    public static List<Color> getRandomColorsArray(){
        List<Color> colorArray = new ArrayList<>();
        for(int i=0; i< 500; i++){
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colorArray.add(new Color(r,g,b));
        }
        return colorArray;
    }
}

