package shapes;

import java.util.ArrayList;
import java.util.List;
// 312115090 Alon luboshitz

/**
 * this is a Rectanlge class. its a rectangle holding arr of lines represeting its shape.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line[] rectangleasLines;
    private double width, height;

    /**
     * defualt constructor.
     */
    public Rectangle() {
        this(new Point(), 0, 0);
    }
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft upperleft point.
     * @param width width.
     * @param height hieght.
     */
    //NEED TO MAKE SURE REC IS INSIDE LIMITS OF GUI
    public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.height = height;
    this.width = width;
    this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    this.lowerRight = new Point(this.upperRight.getX(), this.lowerLeft.getY());
    this.rectangleasLines = recasLines();
    }
//Setters.

    /**
     * set hieght.
     * @param height is the new hieght.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * set width.
     * @param width is the new width.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * create arr of lines of rectangle.
     * @return the arr.
     */
    public Line[] recasLines() {
        Line[] recasLines = new Line[4];
        recasLines[0] = new Line(this.upperLeft, this.upperRight);
        recasLines[1] = new Line(this.upperRight, this.lowerRight);
        recasLines[2] = new Line(this.lowerRight, this.lowerLeft);
        recasLines[3] = new Line(this.lowerLeft, this.upperLeft);
        return recasLines;
    }
    /**
     * Return a (possibly empty) List of intersection points.
     * with the specified line.
     * if list is empty there is no intersection points with lines.
     * there can be max 2 points.
     * first point with upper part of rectagnle and goes clockwise.
     * @param line is line intersecting with.
     * @return the list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List interPoints = new ArrayList<Point>();
        for (Line l : rectangleasLines) {
            if (l.isIntersecting(line)) {
                interPoints.add(l.intersectionWith(line));
            }
        }
        return interPoints;
    }
//Getters

    /**
     * gets the width.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * gets the hieght.
     * @return hieght.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * gets the upperright point.
     * @return uuperright.
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * returns lowerleft point.
     * @return lower left.
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }

    /**
     * gets lwoerright point.
     * @return lower right.
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * gets upper left.
     * @return upperleft.
     */
    public Point getUpperleft() {
        return this.upperLeft;
    }
}
