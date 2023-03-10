package backgrounds;

import biuoop.DrawSurface;
import gameplayables.Block;
import gamesettings.GameLevel;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;
// id 312115090 Alon luboshitz.

/**
 * this is a class to set wideeasy background.
 */
public class WiseEasyBackground implements Sprite {
    private List<Block> blockList;
    private int screenStart, screenWidth;

    /**
     * constructor.
     *
     * @param blockList   list of blocks from wiseeasy level.
     * @param screenWidth as is.
     * @param screenStart buttom edge of screen.
     */
    public WiseEasyBackground(List<Block> blockList, int screenWidth, int screenStart) {
        if (blockList != null) {
            this.blockList = blockList;
        }
        if (screenStart > 0) {
            this.screenStart = screenStart;
        }
        if (screenWidth > 0) {
            this.screenWidth = screenWidth;
        }
    }

    /**
     * helper function to draw the sun.
     *
     * @param d            draw surface.
     * @param color        color of sun.
     * @param radius       radius.
     * @param yCenterofsun center of sun.
     * @param xCenterofsun center of sun.
     */
    public void drawSun(DrawSurface d, Color color, int radius, int yCenterofsun, int xCenterofsun) {

        if (yCenterofsun - radius < this.screenStart) {
            radius = Math.abs(yCenterofsun - this.screenStart);
        }
        d.setColor(color);
        d.fillCircle(xCenterofsun, yCenterofsun, radius);
    }

    /**
     * helper function to set width of blocks.
     *
     * @return width of blocks.
     */
    public int widthofblocks() {
        int lastindex = this.blockList.size() - 1;
        return (int) (this.blockList.get(lastindex).getCollisionRectangle().getUpperRight().getX()
                - this.blockList.get(0).getCollisionRectangle().getUpperleft().getX());
    }

    @Override
    public void drawOn(DrawSurface d) {
        int yCenterofSun = (int) (this.blockList.get(0).getCollisionRectangle().getUpperleft().getY()
                - 80);
        int xCenterofSun = (int) this.blockList.get(1).getCollisionRectangle().getUpperRight().getX();
        int widthOfblocks = widthofblocks();
        int dividetoParts = widthOfblocks / 4;
        int yofblocks = (int) this.blockList.get(0).getCollisionRectangle().getUpperleft().getY();
        int xofblocks = (int) this.blockList.get(0).getCollisionRectangle().getUpperleft().getX();
        for (int i = 1; i <= 4; i++) {
            Color color = setColor(i);
            drawSun(d, color, 80 - (i * 10), yCenterofSun, xCenterofSun);
            if (dividetoParts * i <= widthOfblocks) {
                int j = dividetoParts / 15;
                int y = j;
                while (j < dividetoParts) {
                    d.setColor(color);
                    d.drawLine(xCenterofSun, yCenterofSun, xofblocks + (i - 1) * dividetoParts + j, yofblocks);
                    j += y;
                }
            }
        }

    }

    /**
     * sets color for sun and lines.
     *
     * @param n from 1 to 4.
     * @return color.
     */
    private Color setColor(int n) {
        switch (n) {
            case 1:
                return new Color(255, 204, 0);

            case 2:
                return new Color(255, 255, 0);

            case 3:
                return new Color(255, 255, 153);

            case 4:
                return new Color(255, 255, 204);

            default:
                return Color.WHITE;
           }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
