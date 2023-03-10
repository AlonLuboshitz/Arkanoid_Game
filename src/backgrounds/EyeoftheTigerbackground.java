package backgrounds;

import biuoop.DrawSurface;
import gamesettings.GameLevel;
import interfaces.Sprite;
import shapes.Point;

import java.awt.Color;
//312115090 Alon luboshitz

/**
 * this is a class to create a backround for TIGER.
 */
public class EyeoftheTigerbackground implements Sprite {
    private int xLeftLim, yUpperLim, xRightLim, yLowerLim;
    private int screendivider, widthofBlock;
    private int eyeradius;
    private Point lefteye, righteye;

    /**
     * constructor.
     * @param xLeftLim leftlim of screen.
     * @param xRightLim right lim of screen.
     * @param yLowerLim lower lim of screen.
     * @param yUpperLim upper lim of screen.
     */
    public EyeoftheTigerbackground(int xLeftLim, int xRightLim, int yLowerLim, int yUpperLim) {
        if (xLeftLim < 0 || yLowerLim < 0 || xRightLim < 0 || yUpperLim < 0) {
            throw new RuntimeException("screen limits are negative, cannot happen");
        } else if (xRightLim < xLeftLim) {
            throw new RuntimeException("xright lim is smaller then x left lim");
        } else if (yLowerLim > yUpperLim) {
            throw new RuntimeException("ylower lim is bigger then upper.");
        } else if (yUpperLim == 0 || xRightLim == 0) {
            throw new RuntimeException("screen is 0 dimentions");
        } else {
            this.xLeftLim = xLeftLim;
            this.xRightLim = xRightLim;
            this.yLowerLim = yLowerLim;
            this.yUpperLim = yUpperLim;
            setScreendividerndWidthofBlock();
        }
    }
//setters
    /**
     * setter for eye radius.
     * @param eyeradius eye radius.
     */
    public void setEyeradius(int eyeradius) {
        this.eyeradius = eyeradius;
    }

    /**
     * setter for left eye point.
     * @param lefteye left eye point.
     */
    public void setLefteye(Point lefteye) {
        this.lefteye = lefteye;
    }

    /**
     * setter for righteye point.
     * @param righteye right eye point.
     */
    public void setRighteye(Point righteye) {
        this.righteye = righteye;
    }

    /**
     * this function divides the screen to at least 6 difrrenet parts each of which.
     * gonna be painted with oragne\black. assuming screen demensions are ints no decimls required.
     * and screen gonna be bigger then 20.
     * if % divider is 0 then it divides perfectly!
     */
    public void setScreendividerndWidthofBlock() {
        int divider = 6;
        int screen = xRightLim - xLeftLim;
        while ((screen % divider) != 0) {
            divider++;
        }
        this.screendivider = divider;
        this.widthofBlock = screen / this.screendivider;
    }

    @Override
    /**
     * itterates on the divider paintaing the screen with black and orange.
     */
    public void drawOn(DrawSurface d) {

        for (int i = 0; i < this.screendivider; i++) {
            if ((i % 2) == 0) {
                d.setColor(Color.orange);
            } else {
                d.setColor(Color.BLACK);
            }
            d.fillRectangle(xLeftLim + (this.widthofBlock * i), yLowerLim, this.widthofBlock, yUpperLim - yLowerLim);
        }
        d.setColor(Color.CYAN);
        d.fillCircle((int) lefteye.getX(), (int) lefteye.getY(), eyeradius);
        d.fillCircle((int) righteye.getX(), (int) righteye.getY(), eyeradius);
        d.setColor(Color.BLACK);
        d.fillCircle((int) lefteye.getX(), (int) lefteye.getY(), eyeradius / 3);
        d.fillCircle((int) righteye.getX(), (int) righteye.getY(), eyeradius / 3);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
