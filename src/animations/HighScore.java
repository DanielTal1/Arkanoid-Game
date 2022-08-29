package animations;
import java.io.*;
import java.io.File;


public class HighScore {
    private int highScore;

    public HighScore() {
        this.highScore = findHighscore();
    }

    public Integer getHighScore (){
        return this.highScore;
    }

    public int findHighscore() {
        File scoreFile = new File("highScore.txt");
        BufferedReader reader = null;
        try {
            if (scoreFile.exists()) {
                reader = new BufferedReader(new FileReader(scoreFile));
                String line = reader.readLine();
                //The highest score so far is: XXX
                if (line != null && line.length() > 3) {
                    try {
                        return Integer.parseInt(line.substring(line.length() - 3));
                    } catch (Exception notInt) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                scoreFile.createNewFile();
                return 0;
            }
        } catch (Exception e) {
            return 0;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int updateHighScore(int newScore) {
        if (newScore > this.highScore) {
            this.highScore = newScore;
        } else {
            return this.highScore;
        }
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("highScore.txt")));
            writer.print("The highest score so far is: " + this.highScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return this.highScore;
    }
}

