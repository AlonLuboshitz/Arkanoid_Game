package interfaces;

import gameplayables.Block;
import gameplayables.Velocity;

import java.awt.Color;
import java.util.List;
//id 312115090 Alon luboshitz

/**
 * this is an interface to include the levelinformation for each level.
 */
public interface LevelInformation {
    /**
     * returns the color of the background.
     *
     * @return color.
     */
    Color getBackGroundcolor();

    /**
     * returns the number of balls in the level.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * returns a list of velocities of balls in each lvl.
     *
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle speed.
     *
     * @return speed.
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     *
     * @return width.
     */
    int paddleWidth();

    /**
     * returns the level name.
     *
     * @return string with level name.
     */
    String levelName();

    /**
     * returns a sprite with background for this level.
     *
     * @return sprite.
     */
    Sprite getBackground();

    /**
     * returns the blocks that made this level.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * returns the number of blocks need to be removed from each level.
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * returns the screenhieght of the level.
     *
     * @return screen hieght.
     */
    int getScreenHieght();

    /**
     * returns the screen width of the level.
     *
     * @return screen width.
     */
    int getScreenWidth();

    /**
     * returns the limit of x from the start of the screen. end is width of screen - lim.
     *
     * @return xLim.
     */
    int getxLim();

    /**
     * returns the limit of y from the start of the screen.
     *
     * @return yLim.
     */
    int getyLim();
}
