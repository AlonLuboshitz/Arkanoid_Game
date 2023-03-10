package gamesettings;

import animations.GameOver;
import animations.YouWin;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;
// Id 312115090 Alon luboshitz

/**
 * this is the gameflow class. it is in charge to run the game with difrennet leves.
 * it holds a keyborad, animation runner, static score.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private static Counter score = new Counter();

    /**
     * defualt constrcutor.
     */
    public GameFlow() {
        this.animationRunner = new AnimationRunner();
        this.keyboardSensor = animationRunner.getGui().getKeyboardSensor();
    }

    /**
     * construcotr.
     *
     * @param ks keybaord.
     * @param ar animation runner.
     */
    public GameFlow(KeyboardSensor ks, AnimationRunner ar) {
        if (ar != null) {
            this.animationRunner = ar;
        }
        if (ks != null) {
            this.keyboardSensor = ks;
        }

    }

    /**
     * function to run diffrenet levels.
     *
     * @param levels list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = levels.size();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(this.animationRunner,
                    this.keyboardSensor,
                    this.score, levelInfo);

            level.initialize();

            while ((level.getBlockCounter().getValue() > 0) && (level.getBallCounter().getValue() > 0)) {
                level.run();
            }
            i--;

            if (level.getBallCounter().getValue() == 0) {

                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        this.keyboardSensor.SPACE_KEY, new GameOver(this.score)));

                System.exit(0);
            }
            if (i == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        this.keyboardSensor.SPACE_KEY, new YouWin(this.score)));
                System.exit(0);
            }

        }
    }


}
