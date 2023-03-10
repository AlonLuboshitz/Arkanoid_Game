package interfaces;

import gameplayables.Ball;
import gameplayables.Block;
////Id :312115090 alon luboshitz.

/**
 * this is an interface for listenres.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the object being hit.
     * @param hitter is the gameplayables hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
