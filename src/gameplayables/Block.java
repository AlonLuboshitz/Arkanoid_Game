package gameplayables;

import biuoop.DrawSurface;
import gamesettings.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
// 312115090 Alon luboshitz

/**
 * this is the block class which implements the colliadble and sprite interfaces.
 * its a rectangel which can be hitted by other objects and can be drawen to the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private Rectangle rectangle;
    private java.awt.Color color;

    /**
     * constructor, takes the values and create a rectangle.
     *
     * @param upperLeft upper left point.
     * @param width     width.
     * @param hieght    as hieght.
     * @param color     color.
     */
    public Block(Point upperLeft, int width, int hieght, Color color) {
        rectangle = new Rectangle(upperLeft, width, hieght);
        this.color = color;
    }

    /**
     * get the shape of the block.
     *
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * set a new rectanlge for this block.
     *
     * @param rectangle is the new rectangle.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * moves the rectangle to new upperleft point. (for paddle).
     *
     * @param newupperLeft is the new upper left point.
     */
    public void moveRectangle(Point newupperLeft) {
        this.rectangle = new Rectangle(newupperLeft, rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * notifies the object its beening hit by another object.
     * checks on which line\ edges of the block the hit has been made and changes the objects.
     * velocity accordingly.
     * @param hitter is the ball that hits.
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity of the object.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //hits one of the edges of the rectangle\block need to switch dy and dx.
        if (collisionPoint.equals(rectangle.getUpperleft()) || collisionPoint.equals(rectangle.getUpperRight())
                || collisionPoint.equals(rectangle.getLowerLeft())
                || collisionPoint.equals(rectangle.getLowerRight())) {
            currentVelocity.setDx(currentVelocity.getdx() * (-1));
            currentVelocity.setDy(currentVelocity.getdy() * (-1));
            } else if (collisionPoint.ispointonLine(this.rectangle.recasLines()[0])
                || collisionPoint.ispointonLine(this.rectangle.recasLines()[2])) {
            // lines 0 and 2 represents horizental lines of rectangle.
            currentVelocity.setDy(currentVelocity.getdy() * (-1));
        } else {
            // if not on lines 0 and 2 then must be on 1 or 3 -> change dx.
            currentVelocity.setDx(currentVelocity.getdx() * (-1));
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * this function draws the block to the drawsurface.
     *
     * @param d is the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.rectangle.getUpperleft().getX(), (int) this.rectangle.getUpperleft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        if (!this.color.equals(Color.GRAY)) { //if its white = death region.
            d.setColor(Color.BLACK);
        }
        d.drawRectangle((int) this.rectangle.getUpperleft().getX(), (int) this.rectangle.getUpperleft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * function is adding block to game sprite collection and colliadble collection.
     *
     * @param game is the game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }
    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * notifies the listeneres hit has been made.
     * @param hitter is the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * removes this block from the game sprites and colliadbles.
     * @param game is the game.
     */
    public void removeFromGame(GameLevel game) {

        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * this function notifies the block of time passed and acts accordinly.
     */
    public void timePassed() {
        //do something.
    }
}
