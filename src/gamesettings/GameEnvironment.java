package gamesettings;

import interfaces.Collidable;
import shapes.Line;
import shapes.Point;

import java.util.ArrayList;
import java.util.List;
// 312115090 Alon luboshitz

/**
 * this is the gameenvironment class.
 * it holds a list of colliadble objects and functions regarding this list.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * constructor creating a list of colliadbles.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }
    //Getters

    /**
     * get the colliadble list.
     * @return the colliadble list.
     */
    public List<Collidable> getCollidableList() {
        return collidableList;
    }

    /**
     * sets a new colliadble list.
     * @param collidableList is the new list.
     */
    public void setCollidableList(List<Collidable> collidableList) {
        if (collidableList != null) {
            this.collidableList = collidableList;
        }
    }

    /**
     * add the given collidable to the environment.
     * @param c is the colliadble.
     */
     public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.add(c);
        }
    }

    /**
     * function to remove colliadble from the list.
     * @param c is colliadble.
     */
    public void removeColliadle(Collidable c) {
         if (this.collidableList.contains(c)) {
             this.collidableList.remove(c);
         }
    }

    /**
     * function returns the closest collision info of a given line with the colliables in the colliabldes list.
     * @param trajectory is the line intersecting with the colliadble.
     * @return the closest point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point start = trajectory.start(); //set start point.
        Point closest = null;
        Collidable closestCollidable = null;
        double distance = 50000; //big enough distance \ assuming bigger then gui limits.
    for (Collidable collidable : this.collidableList) {
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null) {
                if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()).distance(start)
                        < distance) {
                    closest = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                    distance = closest.distance(start);
                    closestCollidable = collidable;
                }
            }
        }
    if (closest == null) {
        return null;
    } else {
        CollisionInfo collisionInfo = new CollisionInfo(closest, closestCollidable);
        return collisionInfo;
    }
    }
}
