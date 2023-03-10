package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;
//Id 312115090 alon luboshitz.

/**
 * this is class that runs animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * defualt constructor.
     */
    public AnimationRunner() {
        this(new GUI("title", 800, 600), 60);
    }

    /**
     * constructor.
     * @param gui gui.
     * @param framesPerSecond frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        if (gui != null) {
            this.gui = gui;
        }
        if (framesPerSecond != 60) {
            this.framesPerSecond = 60;
        } else {
            this.framesPerSecond = framesPerSecond;
        }
    }

    /**
     * getter for gui.
     * @return gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * run function. takes animation and runs it.
     * @param animation animation being run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
