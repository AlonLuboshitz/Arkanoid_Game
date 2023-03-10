package gameplayables;

import biuoop.DrawSurface;
import gamesettings.CollisionInfo;
import gamesettings.GameLevel;
import gamesettings.GameEnvironment;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
// 312115090 Alon luboshitz

/**
 * this is a ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity ballvelocity;
    private Line ballTrajectory;
    private GameEnvironment gameEnvironment;
    private int boundX;
    private int boundY;
    private int boundBoard;

    /**
     * constructor one.
     *
     * @param center for starting point of the ball.
     * @param r      for radius.
     * @param color  for color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.ballvelocity = new Velocity(0, 0);
        Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX(),
                this.ballvelocity.applyToPoint(this.center).getY());
        this.ballTrajectory = new Line(this.center, endPoint);
    }

    /**
     * constructor two.
     *
     * @param x     x coords of strat point of ball.
     * @param y     y coords of start point of ball.
     * @param r     radius.
     * @param color color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.ballvelocity = new Velocity(0, 0);
        Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX(),
                this.ballvelocity.applyToPoint(this.center).getY());
        this.ballTrajectory = new Line(this.center, endPoint);
    }
    //Getters.

    /**
     * getter.
     *
     * @return x.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getter.
     *
     * @return y.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getter.
     *
     * @return radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getter.
     *
     * @return color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * getter.
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.ballvelocity;
    }

    /**
     * getter for ball trajectory.
     *
     * @return this trajectory.
     */
    public Line getBallTrajectory() {
        return ballTrajectory;
    }

    /**
     * getter for this game environment.
     *
     * @return this game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }
//Setters.

    /**
     * setter for gameEnvironment.
     *
     * @param gameEnvironment is the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * setter for ball trajectory.
     */
    public void setBallTrajectory() {
        double y = Math.abs(Math.sin(this.getVelocity().getAngle())) * this.radius;
        double x = Math.abs(Math.cos(this.getVelocity().getAngle())) * this.radius;
        if (this.getVelocity().getdx() < 0) {
            if (this.getVelocity().getdy() < 0) {
                Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX() - x,
                        this.ballvelocity.applyToPoint(this.center).getY() - y);
                this.ballTrajectory = new Line(this.center, endPoint);
            } else {
                Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX() - x,
                        this.ballvelocity.applyToPoint(this.center).getY() + y);
                this.ballTrajectory = new Line(this.center, endPoint);
            }

        } else if (this.getVelocity().getdy() < 0) {
            Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX() + x,
                    this.ballvelocity.applyToPoint(this.center).getY() - y);
            this.ballTrajectory = new Line(this.center, endPoint);

        } else {
            Point endPoint = new Point(this.ballvelocity.applyToPoint(this.center).getX() + x,
                    this.ballvelocity.applyToPoint(this.center).getY() + y);
            this.ballTrajectory = new Line(this.center, endPoint);
        }
    }

    /**
     * setter.
     *
     * @param v puts v in velocity.
     */
    public void setVelocity(Velocity v) {
        this.ballvelocity = v;
        setBallTrajectory();
    }

    /**
     * setter.
     *
     * @param dx for dx of velocity.
     * @param dy for dy of velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.ballvelocity = new Velocity(dx, dy);
        setBallTrajectory();
    }

    /**
     * set bound of ball.
     *
     * @param x for x axis.
     * @param y for y axis.
     * @param b for extreme point of board\rectanlge.
     */
    public void setbounds(int x, int y, int b) {
        this.boundX = x;
        this.boundY = y;
        this.boundBoard = b;
    }

    /**
     * sets the center.
     *
     * @param x x coord.
     * @param y y coord.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * function take the ball and move it till the end of the board x wise.
     *
     * @param collisionInfo point ball going to be theortically (to far).
     */
    public void moveTillendX(CollisionInfo collisionInfo) {
        Point oldCenter = new Point(this.center.getX() - this.getVelocity().getdx(),
                this.center.getY() - this.getVelocity().getdy());
        // distances from collision point till center of ball.
        double yGap = (Math.abs(Math.sin(this.getVelocity().getAngle())) * this.radius) + 1;
        double xGap = (Math.abs(Math.cos(this.getVelocity().getAngle())) * this.radius) + 1;
        //collision x and y coords.
        double xofCollision = collisionInfo.collisionPoint().getX();
        double yofCollision = collisionInfo.collisionPoint().getY();
        //collision object limits make sure we're not inside the object.
        double rightXlimitofObject = collisionInfo.collisionObject().getCollisionRectangle().getUpperRight().getX();
        double leftXlimitofObject = collisionInfo.collisionObject().getCollisionRectangle().getUpperleft().getX();
        double upperYlimitofObject = collisionInfo.collisionObject().getCollisionRectangle().getUpperleft().getY();
        double lowerYlimitofObject = collisionInfo.collisionObject().getCollisionRectangle().getLowerRight().getY();
        //if ball is getting inside an object we take him out by lifiting it up from the object.
        // if dy velocity is 0 ball moves horizental for some reason sin(angle) != 0.
        // need to zero the gap so ball will keep its horizental movement.
        if (this.getVelocity().getdy() == 0 || this.getVelocity().getdy() == -0) {
            yGap = 0;
        }
        if (xofCollision > this.center.getX()) {
            if (this.center.getX() > leftXlimitofObject) { // ball might be inside the object check y wise.
                if (this.center.getY() > upperYlimitofObject && this.center.getY() < lowerYlimitofObject) {
                    //ball is inside object moving right tight it out moving up.
                    yGap = this.radius;
                    this.center = new Point(xofCollision - xGap, upperYlimitofObject - yGap);
                } // else ball isnt inside object.
            } else if (yofCollision < this.center.getY()) {
                this.center = new Point(xofCollision - xGap, yofCollision + yGap);
            } else {
                this.center = new Point(xofCollision - xGap, yofCollision - yGap);
            }
//else xofCollision < ball center x
        } else if (this.center.getX() < rightXlimitofObject) {
            if (this.center.getY() > upperYlimitofObject && this.center.getY() < lowerYlimitofObject) {
                //ball is inside object moving left check where it came from.
                yGap = this.radius;
                this.center = new Point(xofCollision + xGap, upperYlimitofObject - yGap);
            }
        } else if (yofCollision < this.center.getY()) {
            this.center = new Point(xofCollision + xGap, yofCollision + yGap);
        } else {
            this.center = new Point(xofCollision + xGap, yofCollision - yGap);
        }
    }

    /**
     * this functions moves the ball through a step.
     */
    public void moveOneStep() {
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(this.ballTrajectory);
        if (collisionInfo == null) {
            //no colllosion point move the ball with this trajectory.
            this.center = this.getVelocity().applyToPoint(this.center);
            setBallTrajectory();
            //there is a collosion get it move the ball next to it and use hit.
            // if ball hits horizental line need to calcuate y gap, else x gap.
        } else {
            Velocity temp = collisionInfo.collisionObject().hit(this,
                    collisionInfo.collisionPoint(), this.getVelocity());
            moveTillendX(collisionInfo);
            this.ballvelocity = temp;
            setBallTrajectory();
        }
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface is the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * functions adds ball to game through sprite collection.
     *
     * @param game is the game.
     */
    public void addToGame(GameLevel game) {
        this.setGameEnvironment(game.getEnvironment());
        game.addSprite(this);
    }

    /**
     * functinos deletes a ball from the game.
     * @param game is the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
    /**
     * this function moves the ball.
     */
    public void timePassed() {
        moveOneStep();
            }
}