package animations;

import biuoop.DrawSurface;
import gamesettings.Counter;
import interfaces.Animation;
//Id 312115090 Alon luboshitz.

/**
 * this is an animation for win situation. printing "you win" and the score.
 */
public class YouWin implements Animation {
    private Counter score;

    /**
     * constrcutor.
     *
     * @param score score to show.
     */
    public YouWin(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your Score is: " + score.getValue(), 32);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}

