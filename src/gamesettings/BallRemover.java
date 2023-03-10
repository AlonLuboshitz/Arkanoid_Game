package gamesettings;
////Id :312115090 alon luboshitz.
import gameplayables.Ball;
import gameplayables.Block;

import interfaces.HitListener;

/**
 * this class is to remove ball from the game.
 * its listens to block - death region, which being hit with it.
 * removes the balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remaingBalls;

    /**
     * constructor of ball remover.
     * @param game is the game.
     * @param remaingBalls number of remaingballs.
     */
    public BallRemover(GameLevel game, Counter remaingBalls) {
        if (game != null) {
            this.game = game;
        }
        if (remaingBalls != null) {
            this.remaingBalls = remaingBalls;
        }
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remaingBalls.decrease(1);
    }

    /**
     * set number of balls.
     * @param remaingBalls balls.
     */
    public void setRemaingBalls(Counter remaingBalls) {
        this.remaingBalls = remaingBalls;
    }
}
