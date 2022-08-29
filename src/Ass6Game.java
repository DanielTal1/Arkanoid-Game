import animations.*;
import biuoop.GUI;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game activate the game to start.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class Ass6Game {

    /**
     * main method that calls the methods that initialize and run the game.
     *
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation<>(keyboard);
        HighScore highScore = new HighScore();
        HighScoresScreen highScoresAnimation = new HighScoresScreen(keyboard, highScore);
        menu.addSelection("h", "High score", new ShowTask(animationRunner, highScoresAnimation));
        List<LevelInformation> levels = getLevelList(getNumbersList(args));
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard, levels);
        menu.addSelection("s", "start game", gameFlow);
        menu.addSelection("q", "quit", new ExitGame());
        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            if(task!= null){
                task.run();
            }
        }
    }

    /**
     * creates a list with all the levels according to the list of numbers.
     *
     * @param levelsNumbers is the list of numbers.
     * @return list with the levelInformation for each level.
     */
    private static List<LevelInformation> getLevelList(ArrayList<Integer> levelsNumbers) {
        List<LevelInformation> levels = new ArrayList<>();
        if (levelsNumbers.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        } else {
            for (Integer levelNumber : levelsNumbers) {
                if (levelNumber == 1) {
                    levels.add(new Level1());
                } else if (levelNumber == 2) {
                    levels.add(new Level2());
                } else if (levelNumber == 3) {
                    levels.add(new Level3());
                } else if (levelNumber == 4) {
                    levels.add(new Level4());
                }
            }
        }
        return levels;
    }

    /**
     * checks if a string is a number.
     *
     * @param s is a String.
     * @return true if the String is a number, false otherwise.
     */
    private static Boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * ignoring the arguments which aren't numbers.
     *
     * @param arguments are the command line arguments.
     * @return a list with all the arguments which are numbers.
     */
    private static ArrayList<Integer> getNumbersList(String[] arguments) {
        ArrayList<Integer> numbersList = new ArrayList<>();
        for (String level : arguments) {
            if (isNumber(level)) {
                int levelNumber = Integer.parseInt(level);
                if (levelNumber >= 1 && levelNumber <= 4) {
                    numbersList.add(levelNumber);
                }
            }
        }
        return numbersList;
    }


}
