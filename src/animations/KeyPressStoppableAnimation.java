package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

//ID:316081975
/**
 * KeyPressStoppableAnimation is a decorator class
 * that wrap an existing animation and add a "waiting-for-key" behavior to it.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public class  KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor that creates an new KeyPressStoppableAnimation object.
     *
     * @param sensor is the KeyboardSensor.
     * @param key is a String key that the animation waits for.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        if(this.stop){
            this.stop = false;
            return true;
        }
        return false;
    }

}