package gameplayables;
// 312115090 Alon luboshitz

import shapes.Point;

/**
 * this is class of veloctiy. it holds angle, dx, and dy movements.
 */
public class Velocity {
    private double dx;
    private double dy;
    private double angle;
    // constructors
    /**
     * constructor one.
     * @param dx velocity in x axis.
     * @param dy velocity in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.angle = Math.toRadians(Math.tan(this.dy / this.dx));
    }
    /**
     * defualt constructor which uses the first one inside aswell.
     * taking angle and speed and math them into dx and dy vectors.
     * @param angle angle of velocity.
     * @param speed speed of velocity.
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(90 - angle);
        double dx = speed * Math.cos(radians);
        double dy = speed  * (-1) * Math.sin(radians); // -1 is up.
        return new Velocity(dx, dy);
        }
    /**
     * getter for angle.
     * @return angle.
     */
    public double getAngle() {
        return angle;
    }
    /**
      * getter.
      * @return dx.
      */
   public double getdx() {
       return this.dx;
   }
   /**
    * getter.
    * @return dy.
    */
   public double getdy() {
       return this.dy;
   }

    /**
     * setter for dy.
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;

    }
    /**
     * setter for dx.
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
           }

    /**
     * setter for angle.
     * @param angle is the angle.
     */
    public void setAngle(double angle) {
        this.angle = Math.toRadians(angle);

    }

    /**
    *  Take a point with position (x,y) and return a new point.
    * with position (x+dx, y+dy).
    * @param p point.
    * @return point alterd.
    */

   public Point applyToPoint(Point p) {
       Point newpoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
       return newpoint;
   }
}