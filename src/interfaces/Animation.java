package interfaces;

import biuoop.DrawSurface;
//Id 312115090 alon luboshitz

/**
 * this is an interface for diffrenet animation methods\objects.
 */
public interface Animation {
    /**
     * fucntion draws a frame.
     *
     * @param d drawsurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns boolean value for each animation if should stop or not.
     *
     * @return boolean value.
     */
    boolean shouldStop();
}
