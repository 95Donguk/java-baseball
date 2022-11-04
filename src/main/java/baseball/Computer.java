package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Computer {
    private static final int MAX_NUMBER_OF_DIGITS = 3;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 9;

    private final List<Integer> number = new ArrayList<>();

    public void generateRandomNumber() {
        while (number.size() < MAX_NUMBER_OF_DIGITS) {
            int randomNumber = Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
            if (!number.contains(randomNumber)) {
                number.add(randomNumber);
            }
        }
    }

    public List<Integer> getNumber() {
        return number;
    }
}
