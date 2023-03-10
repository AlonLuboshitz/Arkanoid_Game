package animations;

import biuoop.DrawSurface;
import gamesettings.Counter;
import interfaces.Animation;
// id 312115090 Alon luboshitz.

/**
 * game over class. it is animation.
 */
public class GameOver implements Animation {
    private Counter score;

    /**
     * constructor getting a score.
     *
     * @param score
     */
    public GameOver(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over! Your Score is: " + score.getValue(), 32);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
