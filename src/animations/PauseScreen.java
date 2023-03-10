package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
//Id 312115090 alon Luboshitz.

/**
 * this class is to pause the game.
 * it implenetes animation. and works while pressing p on keyboard.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
