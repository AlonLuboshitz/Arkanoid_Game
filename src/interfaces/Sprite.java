package interfaces;

import biuoop.DrawSurface;
import gamesettings.GameLevel;
// 312115090 Alon luboshitz

/**
 * this is the sprite interface.
 */
public interface Sprite {
        /**
         *  draw the sprite to the screen.
         * @param d is the drawsurface.
         */
        void drawOn(DrawSurface d);

        /**
         * notify the sprite that time has passed.
         */
        void timePassed();

        /**
         * add the sprite to the game.
         * @param game is the game.
         */
        void addToGame(GameLevel game);

}
