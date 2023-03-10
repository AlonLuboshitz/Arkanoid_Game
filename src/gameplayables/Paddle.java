package gameplayables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesettings.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
// 312115090 Alon luboshitz

/**
 * this is a paddle class implenetes the sprite and colliadble interfaces.
 * the paddle is the player keyboard interface which he uses to move around the game board and hit balls.
 */


public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Point startPoint;
    private Block block;
    private java.awt.Color color;
    private int xframe, yframe;
    private int rightEdge;
    private int speed;
    private int paddlewidth;
//Constructors.

    /**
     * Defualt constructor.
     */
    public Paddle() {
        throw new RuntimeException("cannot construct paddle without data");
    }

    /**
     * constructor for paddle.
     *
     * @param startPoint     start point.
     * @param paddlewidth    width of paddle.
     * @param frameLimx      lim x of frame.
     * @param frameLimy      y lim.
     * @param keyboardSensor the keybaord.
     * @param rightEdge      right edge of screen.
     * @param speed          speed of paddle.
     */
    public Paddle(Point startPoint, int paddlewidth, int frameLimx, int frameLimy,
                  KeyboardSensor keyboardSensor, int rightEdge, int speed) {
        if (speed < 0) {
            this.speed = speed * (-1);
        } else {
            this.speed = speed;
        }
        if (paddlewidth < 0) {
            this.paddlewidth = 100;
        } else if (paddlewidth > rightEdge - (2 * frameLimx)) {
            throw new RuntimeException("paddle is to big for the screen");
        } else {
            this.paddlewidth = paddlewidth;
        }
        if (xframe < 0) {
            this.xframe = 20;
        } else {
            xframe = frameLimx;
        }
        if (yframe < 0) {
            this.yframe = 20;
        } else {
            yframe = frameLimy;
        }
        if (startPoint.getX() < 0) {
            throw new RuntimeException("X coords is negative");
        }
        if (startPoint.getY() < 0) {
            throw new RuntimeException("Y coords is negative");
        }
        if (rightEdge < startPoint.getX()) {
            throw new RuntimeException("start point is out of screen");
        } else if (rightEdge < 0) {
            throw new RuntimeException("negative rightedge is non defiend.");
        } else if (rightEdge - this.paddlewidth > startPoint.getX()) {
            this.rightEdge = rightEdge;
        } else {
            //X coord of paddle is to close to the right edge
            startPoint.setX((double) (rightEdge - frameLimx - paddlewidth));
            this.rightEdge = rightEdge;
        }
        this.color = Color.RED;
        block = new Block(startPoint, this.paddlewidth, frameLimy, this.color);
        if (keyboardSensor != null) {
            this.keyboard = keyboardSensor;
        }
    }
//Getters.

    /**
     * paddle as a block.
     *
     * @return block.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * getter for keyboard.
     *
     * @return keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }
//Setters.

    /**
     * set color.
     *
     * @param color is the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * set new block for paddle.
     *
     * @param block is the block.
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * this function uses the keyboard arrows pressed by the player and moves the paddle to left.
     * the movement is 5 steps x wise.
     */
    public void moveLeft() {
        //make sure paddle isnt going off limits to the left.
        Point paddlePoint = (block.getCollisionRectangle().getUpperleft()); //acuatal upperleft point of paddle
        if (paddlePoint.getX() - speed >= xframe) {
            //enough space to move, move to the left.
            Point movedPoint = new Point(paddlePoint.getX() - speed, paddlePoint.getY());
            block.moveRectangle(movedPoint);
            //else not enough space steps, move the gap.
        } else {
            double distance = Math.abs(paddlePoint.getX() - xframe);
            Point movedPoint = new Point(paddlePoint.getX() - distance, paddlePoint.getY());
            block.moveRectangle(movedPoint);
        }
    }

    /**
     * this function uses the keyboard arrows pressed by the player and moves the paddle to the right.
     * the movement is 5 steps x wise.
     */
    public void moveRight() {
        //make sure paddle isnt going off limits to the right
        Point paddlePoint = (block.getCollisionRectangle().getUpperRight()); //accutual upperright point of paddle
        double rightEdge1 = this.rightEdge - xframe;

        if (paddlePoint.getX() + speed <= rightEdge1) {
            //enough room to move
            Point movedPoint = new Point((paddlePoint.getX() + speed) - paddlewidth, paddlePoint.getY());
            block.moveRectangle(movedPoint);
        } else { //move the gap.
            double distance = Math.abs(rightEdge1 - paddlePoint.getX());
            Point movedPoint = new Point((paddlePoint.getX() + distance) - paddlewidth, paddlePoint.getY());
            block.moveRectangle(movedPoint);
        }
    }

    /**
     * this function checks where the (ball in this case) hits the paddle.
     * diving the paddle into 5 areas.
     *
     * @param collosionpoint is the collision point.
     * @return the zone of the hit.
     */
    public int zoneOfhit(Point collosionpoint) {
        int addOn = (int) (block.getCollisionRectangle().getWidth()) / 5;
        //divides the paddle to 5 parts - taking widgth divides to 5.
        double collosionpointX = collosionpoint.getX(); // x of collosion.
        double upperLeftX = block.getCollisionRectangle().getUpperleft().getX(); //x of paddle from left.
        if ((collosionpointX > upperLeftX) && (collosionpointX < upperLeftX + addOn)) {
            return 1;
        } else if ((collosionpointX >= (upperLeftX + addOn)) && (collosionpointX < (upperLeftX + (2 * addOn)))) {
            return 2;
        } else if ((collosionpointX >= (upperLeftX + (2 * addOn))) && (collosionpointX <= (upperLeftX + (3 * addOn)))) {
            return 3;
        } else if ((collosionpointX > (upperLeftX + (3 * addOn))) && (collosionpointX <= (upperLeftX + (4 * addOn)))) {
            return 4;
        } else if ((collosionpointX > (upperLeftX + (4 * addOn))) && (collosionpointX < (upperLeftX + (5 * addOn)))) {
            return 5;
        } else {
            return 6;
        }
    }

    // interfaces.Sprite interface implemenets:

    /**
     * timepassed uses the movement functions.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * using paddle as a block and draws it to the screen.
     *
     * @param d is the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
    }

    // interfaces.Collidable interfaces implements:

    /**
     * gets the rectangle shape of the paddle.
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return block.getCollisionRectangle();
    }

    /**
     * hit function, uses the zone function to determine which zone is beeing hit and change the velocity.
     * of the ball regarding the zone. zone 3 (middle) and the sides acts normally like a block.
     *
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity of the ball.
     * @param hitter          is the ball.
     * @return new velocity after being hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int n = zoneOfhit(collisionPoint);
        //creating speed from ball trajectory using 0,0 point and velocity values.
        Point zero = new Point();
        Point end = new Point(currentVelocity.getdx(), currentVelocity.getdy());
        double speed = zero.distance(end);
        switch (n) {
            default:
                return block.hit(hitter, collisionPoint, currentVelocity);
            case 1:
                Velocity v = Velocity.fromAngleAndSpeed(-60, speed);
                return v;
            case 2:
                Velocity y = Velocity.fromAngleAndSpeed(-30, speed);
                return y;

            case 3:
                return block.hit(hitter, collisionPoint, currentVelocity);
            case 4:
                Velocity g = Velocity.fromAngleAndSpeed(30, speed);
                return g;

            case 5:
                Velocity h = Velocity.fromAngleAndSpeed(60, speed);
                return h;

        }

    }

    /**
     * add this paddle to the game class.
     *
     * @param g is the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
