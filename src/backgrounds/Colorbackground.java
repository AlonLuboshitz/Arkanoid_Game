package backgrounds;

import biuoop.DrawSurface;
import gamesettings.GameLevel;
import interfaces.Sprite;

import java.awt.Color;
// id 312115090 alon luboshitz.

/**
 * this is a background of Directhit.
 */
public class Colorbackground implements Sprite {
    private Color backgroundcolor;

    /**
     * this is a background color sprite.
     * @param color color.
     */
    public Colorbackground(Color color) {
        if (color != null) {
            this.backgroundcolor = color;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundcolor);
        d.fillRectangle(0, 40, d.getWidth(), d.getHeight() - 40);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
