package backgrounds;

import biuoop.DrawSurface;
import gamesettings.GameLevel;
import interfaces.Sprite;
import shapes.Point;

import java.awt.Color;
// id 312115090 Alon luboshitz.

/**
 * this is a class to set the backgournd of directhit.
 */
public class DirectHitBackground implements Sprite {
    private Color backgroundcolor;
    private Point blockPoint;
    private int blockWidth, blockHieght;

    /**
     * this is a background color sprite.
     *
     * @param blockHieght block hieght.
     * @param color       color.
     * @param blockpoint  point of block.
     * @param blockWidth  the blockwidgths.
     */
    public DirectHitBackground(Color color, Point blockpoint, int blockWidth, int blockHieght) {
        if (color != null) {
            this.backgroundcolor = color;
        }
        if (blockpoint != null) {
            this.blockPoint = blockpoint;
        }
        if (blockWidth > 0) {
            this.blockWidth = blockWidth;
        }
        if (blockHieght > 0) {
            this.blockHieght = blockHieght;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundcolor);
        d.fillRectangle(0, 40, d.getWidth(), d.getHeight() - 40);
        int xcenter = ((int) this.blockPoint.getX() + this.blockWidth / 2);
        int ycenter = ((int) this.blockPoint.getY() + this.blockHieght / 2);
        int j = 40;
        int y = j;
        d.setColor(Color.blue);
        for (; y <= j * 4; y = y + j) {
            d.drawCircle(xcenter, ycenter, y);
        }
        d.drawLine(xcenter, ycenter, xcenter - y, ycenter);
        d.drawLine(xcenter, ycenter, xcenter + y, ycenter);
        d.drawLine(xcenter, ycenter, xcenter, ycenter + y);
        d.drawLine(xcenter, ycenter, xcenter, ycenter - y);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
