package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesettings.SpriteCollection;
import interfaces.Animation;
//Id 312115090 Alon luboshitz.

/**
 * this is a countdown class, defualt from 3 to 1. unless we send it difrrenet number.
 */
public class CountdownAnimation implements Animation {
    private boolean running;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private long holdTime;

    /**
     * defualt constructor.
     */
    public CountdownAnimation() {
        this(2, 3, null);
    }

    /**
     * constructor.
     * @param numOfSeconds number of seconds to hold the all count down.
     * @param countFrom count from.
     * @param gameScreen the game screen show the count on.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sleeper = new Sleeper();
        this.running = true;
        if (numOfSeconds <= 0) {
            this.numOfSeconds = 2;
        } else {
            this.numOfSeconds = numOfSeconds;
        }
        if (countFrom <= 0) {
            this.countFrom = 3;
        } else {
            this.countFrom = countFrom;
        }
        if (gameScreen.equals(null)) {
            throw new RuntimeException("no sprites to show");
        } else {
            this.gameScreen = gameScreen;
        }
        this.holdTime = (long) ((this.numOfSeconds / (double) this.countFrom) * 1000);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int x = d.getWidth() / 2; //x place for countdown
        int y = d.getHeight() / 2; //y place for countdown;
        String count = String.valueOf(this.countFrom);
        this.gameScreen.drawAllOn(d);
        d.drawText(x, y, count, 32);
        sleeper.sleepFor(holdTime);
        if (this.countFrom == 0) {

            this.running = false;
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
