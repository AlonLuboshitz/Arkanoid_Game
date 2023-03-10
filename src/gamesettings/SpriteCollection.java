package gamesettings;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;
// 312115090 Alon luboshitz

/**
 * this is the spritecollection class.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * defualt constructor.
     */
    public SpriteCollection() {
    this.listOfSprites = new ArrayList<Sprite>();
    }

    /**
     * non defualt constructor.
     * @param list put list in spirtes list.
     */
    public SpriteCollection(List<Sprite> list) {
        this.listOfSprites = list;
    }

    /**
     * gets the sprites list.
     * @return list of sprits.
     */
    public List<Sprite> getListOfSprites() {
        return listOfSprites;
    }

    /**
     * set new list of sprites.
     * @param listOfSprites is the list.
     */
    public void setListOfSprites(List<Sprite> listOfSprites) {
        this.listOfSprites = listOfSprites;
    }

    /**
     * function add sprite to collection, make sure its not null.
     * @param s is the sprite.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.listOfSprites.add(s);
        }
    }

    /**
     * deletes sprite s from the sprite collection.
     * @param s deletes the sprite.
     */
    public void removeSprite(Sprite s) {
        if (this.listOfSprites.contains(s)) {
            this.listOfSprites.remove(s);
        }
    }

    /**
     * call timePassed on all sprites.
     */
    public void notifyAllTimePassed() {
        if (this.listOfSprites != null) {
               List<Sprite> templist = new ArrayList<>(listOfSprites);
            for (Sprite s : templist) {
                s.timePassed();
            }
        }
    }

    /**
     * this function call drawOn on all sprites. making sure drawsurface exsits and list exsist.
     * @param d is the surface.
     */
    public void drawAllOn(DrawSurface d) {
        if (d != null && this.listOfSprites != null) {
            for (Sprite s : this.listOfSprites) {
                s.drawOn(d);
            }
        }
    }
}
