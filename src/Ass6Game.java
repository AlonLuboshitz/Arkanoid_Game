
import biuoop.KeyboardSensor;


import gamelevels.LevelsSetter;

import animations.AnimationRunner;
import gamesettings.GameFlow;
// 312115090 Alon Luboshitz


/**
 * this class does a main function calls the game.
 */
public class Ass6Game {

    /**
     * this is the main function.
     *
     * @param args from user.
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor keyboardSensor = ar.getGui().getKeyboardSensor();
        LevelsSetter levels = new LevelsSetter(args);
        GameFlow game = new GameFlow(keyboardSensor, ar);
        game.runLevels(levels.getList());
    }
}
