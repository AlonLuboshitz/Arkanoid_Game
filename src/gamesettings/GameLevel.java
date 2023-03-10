package gamesettings;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameplayables.Ball;
import gameplayables.Block;
import gameplayables.Paddle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// 312115090 Alon luboshitz

/**
 * this is the game level class.
 * its resopnsible for creating gui, blocks at the edges, blocks in the middle, balls.
 * its holds an spritecollection and the game envirmonet and it reasponsible to add the object to them.
 */
public class GameLevel implements Animation {
    private Boolean running = true;
    private AnimationRunner runner;
    private int widthofScreen;
    private int hieghtofScreen;
    private int frameLimx; //blocks at edges widgh. max half of width.
    private int frameLimy; //blocks at edges hieght. max half of hieght
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreListener;
    private LevelInformation leveldecorator;

    /**
     * constructor for game level.
     *
     * @param ar             animation runnner to run the game level.
     * @param keyboard       keyboard.
     * @param score          score beeing sent to the game level by ref from game flow.
     * @param leveldecorator decorator for the level.
     */
    public GameLevel(AnimationRunner ar, KeyboardSensor keyboard, Counter score,
                     LevelInformation leveldecorator) {
        if (ar != null) {
            this.runner = ar;
        }
        if (keyboard != null) {
            this.keyboard = keyboard;
        }
        if (score != null) {
            this.score = score;
        }
        if (leveldecorator != null) {
            this.leveldecorator = leveldecorator;
        }
        this.widthofScreen = this.leveldecorator.getScreenWidth();
        this.hieghtofScreen = this.leveldecorator.getScreenHieght();
        this.frameLimx = this.leveldecorator.getxLim();
        this.frameLimy = this.leveldecorator.getyLim();
    }
    //Getters

    /**
     * getter for blockcounter.
     *
     * @return block counter.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }
    /**
     * getter for the lvevl name.
     * @return level name.
     */
    public String levelName() {
        return this.leveldecorator.levelName();
    }
    /**
     * getter for ball counter.
     *
     * @return ball counter.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * getter for width of gui.
     *
     * @return width of gui.
     */
    public int getWidthofScreen() {
        return widthofScreen;
    }

    /**
     * getter for yframe.
     *
     * @return yframe.
     */
    public int getFrameLimy() {
        return frameLimy;
    }


    /**
     * getter for game enviromnet.
     *
     * @return game enviroment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }


    /**
     * getter for sprites.
     *
     * @return sprites collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }
    //Setters

    /**
     * set new game enviroment.
     *
     * @param environment the new one.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * sets new spritescollection.
     *
     * @param sprites the new collection.
     */
    public void setSpritesCollection(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    /**
     * adds colliadble object to the game gameenviromnet.
     *
     * @param c is the colliadble.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.getEnvironment().addCollidable(c);
        }
    }

    /**
     * adds sprite the game sprite collection.
     *
     * @param s is the sprite.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        }
    }

    /**
     * deletes colliadble c from the gameenviroment list.
     *
     * @param c is the colliabdle nedd to be deleted.
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.environment.removeColliadle(c);
        } else {
            throw new RuntimeException("trying to delete null colliadble.");
        }
    }

    /**
     * deletes sprite s from sprite collection.
     *
     * @param s is the sprite.
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            this.sprites.removeSprite(s);
        } else {
            throw new RuntimeException("trying to delete null sprite");
        }
    }

    /**
     * functions generate frame of the game - blocks in the edges.
     * death region is the buttom edge of the screen.
     * creating the paddle adopting to the buttom edge.
     *
     * @return lists of rectangles.
     */
    public List<Block> generateFrame() {
        this.ballRemover = new BallRemover(this, ballCounter);
        ArrayList<Block> l = new ArrayList<>();
        java.awt.Color color = Color.GRAY;
        int frameLimy = this.leveldecorator.getyLim();
        int widthofScreen = this.leveldecorator.getScreenWidth();
        int hieghtofScreen = this.leveldecorator.getScreenHieght();
        int frameLimx = this.leveldecorator.getxLim();

        Block upEdge = new Block(new Point(0, frameLimy), widthofScreen, frameLimy, color);
        Block rightEdge = new Block(new Point(widthofScreen - frameLimx, 2 * frameLimy), frameLimx,
                hieghtofScreen - frameLimy, color);
        Block leftEdge = new Block(new Point(0, frameLimy * 2), frameLimx, hieghtofScreen - frameLimy, color);
        int tempYframe = frameLimy / 2;
        Block deathRegion = new Block(new Point(frameLimx, hieghtofScreen),
                widthofScreen - (2 * frameLimx), tempYframe, this.leveldecorator.getBackGroundcolor());
        deathRegion.addHitListener(ballRemover);
        l.add(upEdge);
        l.add(rightEdge);
        l.add(leftEdge);
        l.add(deathRegion);
        return l;
    }

    /**
     * this function create blocks in the middle of the board, making sure their in the limit.
     * adding listeners - printlistener and blockremover to each midblock.
     * its uses another function.
     * @param numofRows     rows of blocks wanted to be created.
     * @param hieghtofBlock hieght of each block.
     */
//    public void createMidBlocks(int numofRows, int hieghtofBlock) {
//        Random rand = new Random();
//        this.scoreListener = new ScoreTrackingListener(score);
//        this.blockRemover = new BlockRemover(this, blockCounter);
//        int i = 1;
//        if ((numofRows * hieghtofBlock) + (3 * frameLimy) > hieghtofScreen) {
//            throw new RuntimeException("Trying to create to many rows mid blocks exceeding y limits of gui");
//        }
//        for (int j = numofRows; j >= 1; j--) {
//            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
//            ArrayList<Block> l = generateMidBlocks(j, hieghtofBlock + (i * frameLimy) + frameLimy, color);
//            for (Block b : l) {
//                b.addToGame(this);
//                b.addHitListener(blockRemover);
//                b.addHitListener(scoreListener);
//                this.blockCounter.increase(1);
//            }
//            i++;
//        }
//    }

    /**
     * this function generate blocks with hieght width and color.
     *
     * @param numberOfBlocks is number of blocks wanted to be generated.
     * @param hieght         is the hieght.
     * @param color          color.
     * @return lists of the blocks.
     */
//    public ArrayList<Block> generateMidBlocks(int numberOfBlocks, double hieght, java.awt.Color color) {
//        ArrayList<Block> l = new ArrayList<>();
//        int widthofBlocks = frameLimx * 4;
//        if ((widthofBlocks * numberOfBlocks) + (2 * frameLimx) > widthofScreen) {
//            throw new RuntimeException("mid blocks are to big for screen limits");
//        }
//        for (int i = 0; i < numberOfBlocks; i++) {
//            Point p = new Point((widthofScreen - frameLimx) - ((i + 1) * widthofBlocks), hieght);
//            Block h = new Block(p, widthofBlocks, 20, color);
//            l.add(h);
//        }
//        return l;
//    }

    /**
     * this function generate balls making sure their in the limit of the gui and there are no blocks interupting them.
     *
     * @param numofBalls is the number of balls.
     */
    public void generateBalls(int numofBalls) {
        Random rand = new Random();
        for (int i = 0; i < numofBalls; i++) {
            int x = this.widthofScreen / 2;
            int y = hieghtofScreen - (2 * frameLimy + 10);
            int r = 8;
            x = varifyXofball(x); //varify x with gui limits then make sure no blocks are there y wise.
            y = varifyYofball(x, y, r);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            Ball ball = new Ball((double) x, (double) y, r, color);
            ball.setVelocity(this.leveldecorator.initialBallVelocities().get(i));
            ball.addToGame(this);
            ballCounter.increase(1);
        }

    }

    /**
     * make sure balls will be fast enough.
     *
     * @param speed is the speed.
     * @return new speed.
     */
    public int varifySpeed(int speed) {
        if (speed < 5) {
            speed = 5;
        }
        return speed;
    }

    /**
     * varify x is not outside of frames.
     *
     * @param x random x.
     * @return varified x.
     */
    public int varifyXofball(int x) {
        if (x < frameLimx) {
            x = x + frameLimx;
        }
        return x;
    }

    /**
     * varifies ball is not inside blocks.
     *
     * @param x      is varifeid x.
     * @param y      is random y.
     * @param radius ball radius.
     * @return new point for ball if is inside of blocks.
     */
    public int varifyYofball(int x, int y, int radius) {
        if (y < frameLimy) {
            y = y + frameLimy;
        }
        for (Collidable collidable : this.getEnvironment().getCollidableList()) {
            double xleftLim = collidable.getCollisionRectangle().getUpperleft().getX();
            double xrightLim = collidable.getCollisionRectangle().getUpperRight().getX();
            if (x >= xleftLim && x <= xrightLim) {
                //x is inside block
                double yUpperLim = collidable.getCollisionRectangle().getUpperleft().getY();
                double yLowerLim = collidable.getCollisionRectangle().getLowerLeft().getY();
                //taking in account first row of blocks is longer and up the y axis there are fewer blocks.
                if (y >= yUpperLim && y <= yLowerLim) {
                    y = y + frameLimy + radius + 1;
                }
            }

        }
        return y;
    }

    /**
     * function to create the paddle.
     * creates it using the level decorator information.
     * @return paddle.
     */
    public Paddle createPaddle() {
        int x = (this.widthofScreen / 2) - (this.leveldecorator.paddleWidth() / 2);
        Point startpoint = new Point(x, hieghtofScreen - frameLimy);
        Paddle paddle = new Paddle(startpoint, this.leveldecorator.paddleWidth(), frameLimx, frameLimy,
                this.keyboard, widthofScreen, this.leveldecorator.paddleSpeed());
        return paddle;
    }

    /**
     * this function add blocks the game level calling the fram function aswell.
     */
    public void addBlocks() {
        this.scoreListener = new ScoreTrackingListener(score);
        this.blockRemover = new BlockRemover(this, blockCounter);
        for (Block b : this.generateFrame()) {
            b.addToGame(this);
        }
        for (Block b : this.leveldecorator.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            this.blockCounter.increase(1);
        }
    }

    /**
     * this functino initialize the game.
     * its creating the game environment, the spritecollection
     * the frame blocks, the paddle, and the balls accurding to the levelinformation.
     * in order to make sure things are in the right place.
     */
    public void initialize() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        SpriteCollection spriteCollection = new SpriteCollection();
        setSpritesCollection(spriteCollection);
        setEnvironment(gameEnvironment);
        this.leveldecorator.getBackground().addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, this);
        scoreIndicator.addToGame(this);
        this.addBlocks();
        Paddle p = createPaddle();
        p.addToGame(this);
        generateBalls(this.leveldecorator.numberOfBalls());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        } else if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,  this.keyboard.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * this function run the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }
}

/**
 * this function calls initizilaed function and run.
 *
 * @param args has no meaning atm.
 */

