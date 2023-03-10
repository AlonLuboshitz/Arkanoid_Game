package gamelevels;

import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;
// id 32115090 Alon luboshitz.

/**
 * this class is to create levels getting args from main function in ass6game.
 */
public class LevelsSetter {
    private List<LevelInformation> list;

    /**
     * constructor for levels, create he levels using functions in class.
     *
     * @param args
     */
    public LevelsSetter(String[] args) {
        this.list = createLevels(argsToInts(args));
    }

    /**
     * getter for list.
     *
     * @return level information.
     */
    public List<LevelInformation> getList() {
        return list;
    }

    /**
     * create list of level infromation taking the interger list. if its empty create defualt list.
     * else create list of levels using switch case helper function.
     *
     * @param list interger list.
     * @return list of levels.
     */
    private List<LevelInformation> createLevels(List<Integer> list) {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        if (list.size() == 0) {
            levelInformationList.add(new Directhit());
            levelInformationList.add(new WideEasy());
            levelInformationList.add(new EyeoftheTiger());
            levelInformationList.add(new Pyramide());
        } else {
            return switchcaselist(list, levelInformationList);
        }

        return levelInformationList;
    }

    private List<LevelInformation> switchcaselist(List<Integer> list,
                                                  List<LevelInformation> levelInformationList) {

        for (Integer integer : list) {
            switch (integer) {
                default:
                    return levelInformationList;
                  case 1:
                    levelInformationList.add(new Directhit());
                    break;
                case 2:
                    levelInformationList.add(new WideEasy());
                    break;
                case 3:
                    levelInformationList.add(new EyeoftheTiger());
                    break;
                case 4:
                    levelInformationList.add(new Pyramide());
                    break;
            }
        }
        return levelInformationList;
    }

    /**
     * using args from user and turns them into a list of intergers.
     * cleans the list from number which are not between 1-4.
     *
     * @param args from usr.
     * @return list of intergers.
     */
    private List<Integer> argsToInts(String[] args) {
        List<Integer> list = new ArrayList<>();
        int j = 0;
        for (String s : args) {
            try {
                j = Integer.parseInt(s);
            } catch (NumberFormatException exception) {
                continue;
            }
            if (j >= 1 && j <= 4) {
                list.add(j);
            }
        }
        return list;
    }
}
