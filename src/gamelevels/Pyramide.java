package gamelevels;
import backgrounds.Colorbackground;
import gameplayables.Block;
import gameplayables.Velocity;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// id 312115090 Alon luboshitz.

/**
 * this class is pyramide look a like level.
 */
public class Pyramide extends BasicLevel {
    private int blockWidth = 40;

    /**
     * constructor.
     */
    public Pyramide() {
        super("Pyramide", 4, 5, 80, -60, 100, Color.PINK);
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        int numberOfBalls = this.numberOfBalls();
        int ballAngel = this.getBallAngel();
        int ballspeed = 6;
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 1; i <= numberOfBalls; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ballAngel + (30 * (i)), ballspeed));
        }
        if (numberOfBalls != velocityList.size()) {
            throw new RuntimeException("list size and numberofballs is difrrenet");
        } else {
            return velocityList;
        }
    }
@Override
    public List<Block> blocks() {
        int x = this.getxLim();
        int y = this.getScreenHieght() / 2;
        int divider = (this.getScreenWidth() - (2 * this.getxLim())) / this.blockWidth;
        List<Block> blockList = new ArrayList<>();
        Random rand = new Random();
        int j = 0;
        for (; divider > 0; divider = divider - 2) {
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            blockList.addAll(this.generateRowBlocks(divider, this.blockWidth, 20, x + 2 * (x * (j)),
                    y - (this.getyLim() * j), color));
            j++;
        }
        if (blockList.size() != this.numberOfBlocksToRemove()) {
            throw new RuntimeException("blocks number isnt accual to counted number.");
        } else {
            return blockList;
        }
    }

    @Override
    public Sprite getBackground() {
        return new Colorbackground(Color.PINK);
    }
}
