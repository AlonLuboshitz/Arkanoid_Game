package gamelevels;

import backgrounds.WiseEasyBackground;
import gameplayables.Block;
import gameplayables.Velocity;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
//id 312115090 Alon luboshitz

/**
 * this is wide easy class.
 */
public class WideEasy extends BasicLevel {
    /**
     * constructor sending to basic level with parameters.
     */
    public WideEasy() {
        super("Wide Easy", 2, 5, 400, 0,
                10, Color.WHITE);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int numberOfBalls = this.numberOfBalls();
        int ballAngel = this.getBallAngel();
        int ballspeed = 5;
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ballAngel + (30 * i), ballspeed));
        }
        if (numberOfBalls != velocityList.size()) {
            throw new RuntimeException("list size and numberofballs is difrrenet");
        } else {
            return velocityList;
        }
    }

    @Override
    public Sprite getBackground() {
        return new WiseEasyBackground(this.blocks(),
                this.getScreenWidth() - (2 * this.getxLim()),
                (2 * this.getyLim()));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        //filling entire row knwoing screen is 800-2xlim = 760.
        //devides by 10 nicely.
        int i = 10;
        int xspotJumps = (getScreenWidth() - 2 * getxLim()) / i;
        int yspot = getScreenHieght() / 2 - 100;
        Color color;
        for (int j = 1; j <= i; j++) {
            if (j <= 2) {
                color = Color.RED;
            } else if (j <= 4) {
                color = Color.orange;
            } else if (j <= 6) {
                color = Color.PINK;
            } else if (j <= 8) {
                color = Color.green;
            } else {
                color = Color.blue;
            }
            blockList.add(this.createBlock(xspotJumps, 40,
                    ((j - 1) * xspotJumps) + this.getxLim(), yspot, color));
        }
        if (blockList.size() != this.numberOfBlocksToRemove()) {
            throw new RuntimeException("blocks number isnt accual to counted number.");
        } else {
            return blockList;
        }
    }
}
