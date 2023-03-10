package gamelevels;

import backgrounds.DirectHitBackground;
import gameplayables.Block;
import gameplayables.Velocity;
import interfaces.Sprite;
import shapes.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
// id 312115090 Alon luboshitz

/**
 * this is a class for level one.
 * it has two balls, white background and same velocities for each ball.
 * speed of paddle is 10, and
 */
public class Directhit extends BasicLevel {
    /**
     * sends to super constructor with parameters of this level.
     */
    public Directhit() {
        super("Direct hit", 1, 10, 100,
                 -30, 1, Color.BLACK);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int numberOfBalls = this.numberOfBalls();
        int ballAngel = this.getBallAngel();
        int ballspeed = 5;
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ballAngel, ballspeed));
        }
        if (numberOfBalls != velocityList.size()) {
            throw new RuntimeException("list size and numberofballs is difrrenet");
        } else {
            return velocityList;
        }
    }

    @Override
    public Sprite getBackground() {
        Point blockpoint = new Point(this.getScreenWidth() / 2 - 25, this.getScreenHieght() / 2 - (this.getyLim() * 5));
        return new DirectHitBackground(Color.BLACK, blockpoint, 50, 50);
    }

    @Override
    public List<Block> blocks() {
        int x = this.getScreenWidth() / 2 - 25;
        int y = this.getScreenHieght() / 2 - (this.getyLim() * 5);
        List<Block> blockList = new ArrayList<>();
        blockList.add(this.createBlock(50, 50, x, y, Color.RED));
        if (blockList.size() != this.numberOfBlocksToRemove()) {
            throw new RuntimeException("blocks number isnt accual to counted number.");
        } else {
            return blockList;
        }
    }
}
