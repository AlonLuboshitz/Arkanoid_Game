package shapes;
// 312115090 Alon luboshitz
/**
 * this class is for line introduced with two points.
 * slope and b is for intersection with y axis.
 */
public class Line {
   private Point start = new Point();
   private Point end = new Point();
   private double b;
   private double slope;
   /**
    * constructor given two points.
    * @param start fisrt point.
    * @param end second point.
    */
   public Line(Point start, Point end) {
       this.start = start;
       this.end = end;
       this.slope = slope();
       this.b = setB();
    }
    /**
     * constructor given four coordinates.
     * building from each two a point.
     * @param x1 x coord 1.
     * @param y1 y coord 1.
     * @param x2 x coord 2.
     * @param y2 y coord 2.
     */
   public Line(double x1, double y1, double x2, double y2) {
       this.start = new Point(x1, y1);
       this.end = new Point(x2, y2);
       this.slope = slope();
       this.b = setB();
    }
    /**
     * this function is for getting slope of the line.
     * @return the slope.
     */
    public double slope() {
        double y = this.start.getY() - this.end.getY();
        double x = this.start.getX() - this.end.getX();
        if (this.start.getX() == this.end.getX()) {
            // same x means x=a, point.
             return Double.POSITIVE_INFINITY;
        } else {
             return y / x;
            }
    }
    /**
     * function for intersection with y axis.
     * @return B for y axis.
     */
    public double setB() {
        //b is cutting with y axis
        double b;
        b = this.start.getY() - (this.slope * this.start.getX());
        return b;
    }
    /**
     * function for minX point bitween two line points.
     * @return minX point.
     */
   public Point minX() {
       if (this.start.getX() < this.end.getX()) {
           return this.start;
       } else {
           return this.end;
       }
   }
   /**
    * function for maxX point bitween two line points.
    * @return maxX point.
    */
   public Point maxX() {
        if (this.start.getX() > this.end.getX()) {
        return this.start;
    } else {
        return this.end;
    }
}
/**
 * function for minY point bitween two line points.
 * @return minY.
 */
public Point minY() {
    if (this.start.getY() < this.end.getY()) {
        return this.start;
    } else {
        return this.end;
    }
}
/**
 * function for maxY point bitween two line points.
 * @return maxY point.
 */
public Point maxY() {
     if (this.start.getY() > this.end.getY()) {
     return this.start;
 } else {
     return this.end;
 }
}
/**
 * returns length of the line.
 * @return length.
 */
public double length() {
       double length = start.distance(end);
       return length;
    }
/**
 * return middle point of a line.
 * @return middle point.
 */
   public Point middle() {
       double xMiddle, yMiddle;
       xMiddle = (start.getX() + end.getX()) / 2;
       yMiddle = (start.getY() + end.getY()) / 2;
       Point middle = new Point(xMiddle, yMiddle);
       return middle;
    }
    //GETTERS
   /**
    *  Returns the start point of the line.
    * @return start.
    */
   public Point start() {
       return this.start;
    }
    /**
     * Returns the end point of the line.
     * @return end.
     */
    public Point end() {
       return this.end;
   }
   /**
    * returns slope of the line.
    * @return slope.
    */
   public double getslope() {
    return this.slope;
    }
    /**
     * returns intersection with y.
     * @return b.
     */
public double getB() {
    return this.b;
    }
    /**
    * returns if two lines are intersecting.
    * @param other is the other line.
    * @return true for intersecting false for not.
    */
 public boolean isIntersecting(Line other) {
        //parallel lines
       if (this.getslope() == other.getslope()) {
           //non vertical lines
           if (this.getslope() != Double.POSITIVE_INFINITY) {
               //lines with same slope and diffrenet point with y axis (x=0) dont intgrate.
               if (this.getB() != other.getB()) {
                   return false;
               } else if ((other.minX().getX() < this.minX().getX()) && (other.maxX().getX() < this.minX().getX())) {
// first line before second
return false;
        } else if (other.minX().getX() > this.maxX().getX()) {
                   //first line is after second one
                return false;
               }
               // otherwise other (x coordinate) is bitween LINE.
                return true;
               } else  {
                // lines such as x=a
                // start and end point has same x
                if (this.start().getX() != other.start().getX()) {
                    //lines not on the same x
                    return false;
                } else if ((other.minY().getY() < this.minY().getY()) && (other.maxY().getY() < this.minY().getY())) {
                    return false;
                    //other line is below shapes.Line
                } else if (other.minY().getY() > this.maxY().getY()) {
                    return false;
                    //other line is above line
                }
                return true;
                // other is in bitween
              }
            } else {
             //non parallel lines slopes are diffrenet.
            //one of the lines is a dot or x=a.
            if (this.getslope() == Double.POSITIVE_INFINITY || other.getslope() == Double.POSITIVE_INFINITY) {
                //need to know which one
                if (this.getslope() == Double.POSITIVE_INFINITY) {
                    //other is not (their not parallel)
                    if (other.isbitweenX(this.start().getX())) {
                        // need to check intersection and see if its on the other line
                        double inter = ((other.getslope() * this.start().getX()) + other.getB());
                        //this is the y value of the line with intersection point
                        if (this.isbitweenY(inter)) {
                            return true;
                        }
                        return false;
                     }
                 } else if (this.isbitweenX(other.start().getX())) {
                            // need to check intersection and see if its on the other line
                            double inter = ((this.getslope() * other.start().getX()) + this.getB());
                            //this is the y value of the line with intersection point
                            if (this.isbitweenY(inter)) {
                                return true;
                            }
                            return false;
                         }
                        //other is x=a,dot.
                 }
            //theortical intersection point.
            double  inter = (other.getB() - this.getB()) / (this.getslope() - other.getslope());
            if ((this.isbitweenX(inter)) && (other.isbitweenX(inter))) {
                return true;
            }
            return false;
       }
   }
   /**
    * fucntion for if a x value of apoint is bitween two points.
    * @param inter the x value
    * @return true for is bitween fasle for not.
    */
   public boolean isbitweenX(double inter) {
       if (inter <= this.maxX().getX() && inter >= this.minX().getX()) {
           return true;
       }
       return false;
   }
   /**
    * fucntion for if a y value of apoint is bitween two points.
    * @param inter is y value.
    * @return  true for is bitween fasle for not.
    */
   public boolean isbitweenY(double inter) {
    if (inter <= this.maxY().getY() && inter >= this.minY().getY()) {
        return true;
    }
    return false;
}
/**
 * Returns the intersection point if the lines intersect,
 *  and null otherwise.
 * @param other second line.
 * @return true or null.
 */
   public Point intersectionWith(Line other) {
       if (isIntersecting(other)) {
           // lines are countinuing each other.
           if ((this.slope() == Double.POSITIVE_INFINITY) && (other.slope() == Double.POSITIVE_INFINITY)) {
               if (this.maxY().getY() == other.minY().getY()) { //this is before other and startaring each other.
                   return this.maxY();
               } else if (this.minY().getY() == other.maxY().getY()) { //this is after other and starting each other.
                   return this.minY();
               }
               return null;
           } else if (this.slope == Double.POSITIVE_INFINITY) {
               // one line is x=a diving inifinty not possbile.
           //understand which line it is.
               double interX = other.slope() * this.start().getX() + other.getB();
               Point interpoint = new Point(this.start().getX(), interX);
               return interpoint;
           } else if (other.slope() == Double.POSITIVE_INFINITY) {
            double interX = this.slope() * other.start().getX() + this.b;
            Point interpoint = new Point(other.start().getX(), interX);
            return interpoint;
           } else if (this.getslope() == other.getslope()) {
               //lines with same slope might overlap or not have same b means same (x,y)
               if (this.maxY().getY() == other.minY().getY()) { //this is before other and startaring each other.
                return other.minY();
            } else if (this.minY().getY() == other.maxY().getY()) { //this is after other and starting each other.
                return this.maxY();
                }
           } else {
            // lines with slope != infinity.
            double interX = (other.getB() - this.getB()) / (this.getslope() - other.getslope());
            double interY = this.slope() * interX + this.b;
            Point interpoint = new Point(interX, interY);
            return interpoint;
           }
       }
        return null;
    }

    /**
     * this function checks bitween two points which one is the closest to this line.
     * @param one is point one.
     * @param two is point two.
     * @return the closest point.
     */
    public Point checkpoints(Point one, Point two) {
       if (this.slope != Double.POSITIVE_INFINITY) {
            if (Math.abs(this.start.getX() - one.getX()) < Math.abs(this.start.getX() - two.getX())) {
                //need to check x distance if start of |line - one | smaller then two distance is shorter.
                return one;
            } else { //two is shorter.
                return two;
            }
       } //else slope is inifinty line is x=a; check y coords.
       if (Math.abs(this.start.getY() - one.getY()) < Math.abs(this.end.getY() - two.getY())) {
           return one;
       } else {
           return two;
       }
    }

    /**
     * check what is the closest intersection point of the line with the rectangle.
     * take in account line and trianlge can have max 2points of intersection.
     * @param rect is the rectangle.
     * @return the closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
       Point closestP = new Point();
       Line thisLine = new Line(this.start, this.end);
       if (rect.intersectionPoints(thisLine).isEmpty()) { //if list is empty no points.
           return null;
       } else if (rect.intersectionPoints(thisLine).size() == 1) { //list has one point this is the only one.
           closestP = rect.intersectionPoints(thisLine).get(0);
       } else { // two points check the closest to start of line.
           closestP = checkpoints(rect.intersectionPoints(thisLine).get(0), rect.intersectionPoints(thisLine).get(1));
       }
       return closestP;
    }
/**
 * return true is the lines are equal, false otherwise.
 * @param other second line.
 * @return true or false.
 */
   public boolean equals(Line other) {
       if ((this.start == other.start()) && (this.end == other.end())) {
           return true;
           } else if ((this.start == other.end()) && (this.end == other.start())) {
            return true;
        }
         return false;
       }
    }