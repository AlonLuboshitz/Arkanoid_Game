package gamesettings;
// 312115090 Alon luboshitz

import interfaces.Collidable;
import shapes.Point;

/**
 * this is the collision info class holding the collision point and the collision object.
 */
public class CollisionInfo {
   private Point collosionPoint;
   private Collidable collidableObject;

    /**
     * constructor.
     * @param collosionPoint is the collision point.
     * @param collidableObject is the collision object.
     */

   public CollisionInfo(Point collosionPoint, Collidable collidableObject) {
       this.collosionPoint = collosionPoint;
       this.collidableObject = collidableObject;
   }

    /**
     * the point at which the collision occurs.
      * @return collision point.
     */
    public Point collisionPoint() {
        return this.collosionPoint;
    }

    /**
     *  the collidable object involved in the collision.
     * @return the object.
     */
    public Collidable collisionObject() {
       return this.collidableObject;
    }
}
