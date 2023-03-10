package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
//id 312115090 alon luboshitz

/**
 * this is a decorator class, it wraps animation using a key press to do something.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed = true;
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop;

    /**
     * constructor.
     *
     * @param sensor    is the keyboard.
     * @param key       the key being pressed.
     * @param animation the animation it wraps.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if ((!this.isAlreadyPressed) && (keyboardSensor.isPressed(key))) {
            this.stop = true;
        }
        if (keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}
