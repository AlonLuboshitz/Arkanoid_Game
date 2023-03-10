package gamelevels;

import backgrounds.EyeoftheTigerbackground;
import gameplayables.Block;
import gameplayables.Velocity;
import interfaces.Sprite;
import shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
// id 312115090 Alon Luboshitz.

/**
 * this is an eyeofthetiger background.
 */

public class EyeoftheTiger extends BasicLevel {
    private EyeoftheTigerbackground tigerbackground;
    private final int xstartleftEye = 70;
    private final int xstartrightEye = 440;
    private final int yofEye = 200;
    private final int blockwidth = 50;
    private final int blockhieght = 20;

    /**
     * constructor sends to super with parameters.
     */
    public EyeoftheTiger() {
        super("EyeoftheTiger", 2, 8, 160,
                30, 26, Color.WHITE);
        this.tigerbackground = new EyeoftheTigerbackground(this.getxLim(), this.getScreenWidth() - this.getxLim(),
                this.getyLim() * 2, this.getScreenHieght());
        this.seteyeinBackground();
    }

    @Override
    /**
     * creating 2 different velocities oppisite to each other.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        int numberOfBalls = this.numberOfBalls();
        int ballAngel = this.getBallAngel();
        int ballspeed = 7;
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(ballAngel + 2 * (ballAngel * (-i)), ballspeed));
        }
        if (numberOfBalls != velocityList.size()) {
            throw new RuntimeException("list size and numberofballs is difrrenet");
        } else {
            return velocityList;
        }
    }

    @Override
    public Sprite getBackground() {
        return this.tigerbackground;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();

        blockList.addAll(createAnEye(this.xstartleftEye, this.yofEye));
        blockList.addAll(createAnEye(this.xstartrightEye, this.yofEye));
        return blockList;
    }

    /**
     * function sets the point of the eye in the background.
     */
    private void seteyeinBackground() {
        Point leftEyepoint = new Point((double) (xstartleftEye + (blockwidth * 2)),
                (double) (yofEye + (blockhieght * 2)));
        Point rightEyepoint = new Point((double) (xstartrightEye + (blockwidth * 2)),
                (double) (yofEye + (blockhieght * 2)));
        this.tigerbackground.setLefteye(leftEyepoint);
        this.tigerbackground.setRighteye(rightEyepoint);
        this.tigerbackground.setEyeradius(blockhieght);
    }

    /**
     * this functions create and eye from 12 blocks wrapping a circle.
     *
     * @param xstart xstart of upper row.
     * @param ystart ystart of upper row.
     * @return List of this Blocks.
     */
    private List<Block> createAnEye(int xstart, int ystart) {
        Color blockColor = Color.PINK;
        List<Block> blockList = new ArrayList<>();
        //creating upper row
        blockList.addAll(this.generateRowBlocks(4, blockwidth, blockhieght,
                xstart, ystart, blockColor));
        //creating lower row (bet ween upper and lower there are two blocks)
        blockList.addAll(this.generateRowBlocks(4, blockwidth, blockhieght,
                xstart, (ystart + (3 * blockhieght)), blockColor));
        //creating two mid blocks in bet ween the rows left and right side
        for (int i = 1; i <= 2; i++) {
            blockList.add(this.createBlock(blockwidth, blockhieght, xstart,
                    ystart + (i * blockhieght), blockColor));
            blockList.add(this.createBlock(blockwidth, blockhieght, xstart + (3 * blockwidth),
                    ystart + (i * blockhieght), blockColor));
        }
        return blockList;
    }
}
