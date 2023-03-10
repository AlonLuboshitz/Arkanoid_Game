package gamesettings;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
////Id :312115090 alon luboshitz.

/**
 * this class is a score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int xSpot, ySpot;
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param score initial score.
     * @param game  is the game.
     */
    public ScoreIndicator(Counter score, GameLevel game) {
        if (score != null) {
            this.score = score;
        }
        if (game != null) {
            this.gameLevel = game;
        }
        this.xSpot = (game.getWidthofScreen() / 4); //xSpot a little to the left middle of drawsurface.
        this.ySpot = game.getFrameLimy(); // y spot a lil above the frame.
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(xSpot - 10, ySpot, "Lives: " + gameLevel.getBallCounter().getValue(), 16);
        d.drawText(2 * xSpot - 10, ySpot, "Score: " + score.getValue(), 16);
        d.drawText(3 * xSpot, ySpot, gameLevel.levelName(), 16);

    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
