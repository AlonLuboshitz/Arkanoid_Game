package gamesettings;
//Id :312115090 alon luboshitz
/**
 * this class is a counter class. counts balls, and blocks atm.
 */
public class Counter {
    private int value = 0;
    // add number to current count.
     void increase(int number) {
        this.value += number;
    }
    // subtract number from current count.
     void decrease(int number) {
        int tempValue = this.value - number;
        if (tempValue < 0) {
            throw new RuntimeException("counter cant be lower then 0");
        } else {
            this.value = tempValue;
        }
    }

    /**
     * get current count.
      * @return count.
     */
    public int getValue() {
        return this.value;
    }

}
