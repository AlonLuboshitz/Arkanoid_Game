package shapes;
// 312115090 Alon luboshitz
/**
 * this is a class of point reprsented in two coordinates.
 */
public class Point {
   private double x;
   private double y;
   /**
    * defult constructor.
    */
   public Point() {
      this(0, 0);
   }
   /**
    * this is a constructor of point getting two variables.
    * @param x x coordiante of point.
    * @param y y coordiante of point.
    */
   public Point(double x, double y) {
       this.x = x;
       this.y = y;
   }
    /**
    * this function returns the distance bitween two points.
    * @param other is the second point (object of a point).
    * @return distance.
    */
   public double distance(Point other) {
      double distance = 0;
      distance = Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
      return distance; }
   /**
    * equals -- return true is the points are equal, false otherwise.
    * @param other is the other point.
    * @return equals.
    */
   public boolean equals(Point other) {
       boolean equals = false;
       if ((other.getX() == this.x) && (other.getY() == this.y)) {
           equals = true;
          }
       return equals;
    }

    /**
     * checks if point is on a given line.
     * @param l is the line.
     * @return true or false.
     */
    public boolean ispointonLine(Line l) {
        if (l.getslope() == Double.POSITIVE_INFINITY) {
            if (this.x != l.start().getX()) {
                return false;
            } else if (l.isbitweenY(this.y)) {
                return true;
            }
        } else if (((l.getslope() * this.x) + l.getB()) == this.y) {
            return true;
        }
        return false;
    }
   /**
    * returns x value of point.
    * @return x.
    */
   public double getX() {
      return this.x;
   }
   /**
    * returns y value of point.
    * @return y.
    */
   public double getY() {
      return this.y;
   }

    /**
     * setter for x.
     * @param x is the x.
     */
    public void setX(double x) {
       if (x > 0) {
           this.x = x;
       } else {
           throw new RuntimeException("cannot set negative x point");
       }
    }
}