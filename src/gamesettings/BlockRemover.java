package gamesettings;
////Id :312115090 alon luboshitz.

import gameplayables.Ball;
import gameplayables.Block;

import interfaces.HitListener;

/**
 * this class removes blocks from the games.
 * it listens to block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * this is constrcutor.
     * @param game is the game.
     * @param removedBlocks number of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        if (game != null) {
            this.game = game;
        }
        if (removedBlocks != null) {
            this.remainingBlocks = removedBlocks;
        }
    }


    /**
     * this functions remove block from the game.
     * @param beingHit is the block being hit.
     * @param hitter is the ball that hits it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
