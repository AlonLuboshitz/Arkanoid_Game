package interfaces;

import gameplayables.Ball;
import gameplayables.Velocity;
import shapes.Point;
import shapes.Rectangle;
// 312115090 Alon luboshitz

/**
 * this is the interface colliadble.
 */
public interface Collidable {
    /**
     * get collision rec.
     * @return rec.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit the object.
     * @param collisionPoint is the collision point
     * @param currentVelocity is the velocity.
     * @param hitter is the ball that hits.
     * @return velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    }
