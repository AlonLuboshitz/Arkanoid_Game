package gamesettings;

import gameplayables.Ball;
import gameplayables.Block;
import interfaces.HitListener;
////Id :312115090 alon luboshitz.

/**
 * this class is a score updater.
 * it lisetens to block and use the counter by refrences to update.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        if (scoreCounter != null) {
            this.currentScore = scoreCounter;
        }
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }

}
