package gamelevels;


import gameplayables.Block;
import interfaces.LevelInformation;
import shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
// id 312115090 Alon Luboshitz.

/**
 * this is an abstract superclass for each level.
 * it has sereval memebers that each level needs, its gonna be setted by.
 * a constructor in each level.
 */
public abstract class BasicLevel implements LevelInformation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HIEGHT = 600;
    private static final int X_LIM = 20;
    private static final int Y_LIM = 20;
    private String levelname;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int ballAngel;
    private int numberOfBlocksToRemove;
    private Color backGroundcolor;

    /**
     * constructor.
     *
     * @param levelname              level name.
     * @param numberOfBalls          num of balls.
     * @param paddleSpeed            paddle speed.
     * @param paddleWidth
     * @param ballAngel
     * @param numberOfBlocksToRemove
     * @param backGroundcolor
     */
    public BasicLevel(String levelname, int numberOfBalls, int paddleSpeed, int paddleWidth,
                       int ballAngel, int numberOfBlocksToRemove, Color backGroundcolor) {
        this.levelname = levelname;
        this.numberOfBalls = numberOfBalls;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.ballAngel = ballAngel;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
        this.backGroundcolor = backGroundcolor;
    }

    @Override
    public String levelName() {
        return levelname;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * getter for ball speed.
     * @return ball speed.
     */

    /**
     * getter for ball angel.
     * @return ball angel.
     */
    public int getBallAngel() {
        return ballAngel;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    /**
     * getter for screen hieght.
     * @return screen hieght.
     */
    public int getScreenHieght() {
        return SCREEN_HIEGHT;
    }

    /**
     * getterfor screen widht.
     * @return width.
     */
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * getter for xlim.
     * @return xlim.
     */
    public int getxLim() {
        return X_LIM;
    }

    /**
     * getter for ylim.
     * @return ylim.
     */
    public int getyLim() {
        return Y_LIM;
    }

    /**
     * getter for color.
     * @return background color.
     */
    public Color getBackGroundcolor() {
        return backGroundcolor;
    }

    /**
     * this function is to create blocks in a row.
     *
     * @param numberOfBlocks numofblocks.
     * @param blockWidth     width of each one.
     * @param blockHieght    hiehgt of each one.
     * @param xpointofBlock  starting x point for the first block.
     * @param ypointofBlock  starting y point for the first block.
     * @param color          color of the block.
     * @return list of blocks.
     */
    protected ArrayList<Block> generateRowBlocks(int numberOfBlocks, int blockWidth,
                                                 int blockHieght, int xpointofBlock,
                                                 int ypointofBlock, Color color) {
        if (blockWidth < 0) {
            blockWidth = blockWidth * (-1);
        }
        if (blockHieght < 0) {
            blockHieght = blockHieght * (-1);
        }
        if (blockHieght == 0 || blockWidth == 0) {
            throw new RuntimeException("trying to create blocks with no dementions");
        }
        if (xpointofBlock < 0) {
            xpointofBlock = xpointofBlock * (-1);
        }
        if (ypointofBlock < 0) {
            ypointofBlock = ypointofBlock * (-1);
        }
        if (xpointofBlock < X_LIM || xpointofBlock > SCREEN_WIDTH - X_LIM
                || ypointofBlock > SCREEN_HIEGHT - Y_LIM || ypointofBlock < (2 * Y_LIM)) {
            throw new RuntimeException("trying to create blocks out of screen");
        }
        ArrayList<Block> l = new ArrayList<>();
        if ((blockWidth * numberOfBlocks) + (xpointofBlock + this.X_LIM) > SCREEN_WIDTH) {
            throw new RuntimeException("row blocks are to big for screen limits");
        }
        for (int i = 0; i < numberOfBlocks; i++) {
            Point p = new Point(xpointofBlock + (i * blockWidth), ypointofBlock);
            Block h = new Block(p, blockWidth, blockHieght, color);
            l.add(h);
        }
        return l;
    }

    protected Block createBlock(int blockWidth, int blockHieght, int xpointofBlock,
                                int ypointofBlock, Color color) {
        if (blockWidth < 0) {
            blockWidth = blockWidth * (-1);
        }
        if (blockHieght < 0) {
            blockHieght = blockHieght * (-1);
        }
        if (blockHieght == 0 || blockWidth == 0) {
            throw new RuntimeException("trying to create blocks with no dementions");
        }
        if (xpointofBlock < 0) {
            xpointofBlock = xpointofBlock * (-1);
        }
        if (ypointofBlock < 0) {
            ypointofBlock = ypointofBlock * (-1);
        }
        if (xpointofBlock < X_LIM || xpointofBlock > SCREEN_WIDTH - X_LIM
                || ypointofBlock > SCREEN_HIEGHT - Y_LIM || ypointofBlock < (2 * Y_LIM)) {
            throw new RuntimeException("trying to create blocks out of screen");
        }
        Point p = new Point(xpointofBlock, ypointofBlock);
        Block h = new Block(p, blockWidth, blockHieght, color);
        return h;

    }
}
