package baseball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Application {
    private static final String STRIKE = "스트라이크";
    private static final String BALL = "볼";
    private static final String NOTING = "낫싱";
    private static final int REQUIRED_STRIKE_COUNT = 3;

    private final Map<String, Integer> result = new HashMap<>();
    private boolean gameOver = false;

    public static void main(String[] args) {
        Application application = new Application();
        GameTextPrinter.printGameStartText();
        application.playGame();
    }

    private void playGame() {
        Computer computer = new Computer();
        User user = new User();

        computer.generateRandomNumber();
        while (!gameOver) {
            GameTextPrinter.printUserInputText();
            user.inputNumber();
            compareTwoNumbers(computer.getNumber(), user.getNumber());
            clearUserNumberAndResult(user);
        }
    }

    private void clearUserNumberAndResult(User user) {
        user.clearNumber();
        result.clear();
    }

    private void compareTwoNumbers(List<Integer> computerNumber, List<Integer> userNumber) {
        int sameDigitCount = Math.toIntExact(userNumber.stream().filter(computerNumber::contains).count());
        int strikeCount = getStrikeCount(computerNumber, userNumber);
        int ballCount = sameDigitCount - strikeCount;

        if (isNothing(sameDigitCount)) {
            GameTextPrinter.printNoting(NOTING);
        }

        if (!isNothing(sameDigitCount)) {
            result.put(BALL, ballCount);
            result.put(STRIKE, strikeCount);

            GameTextPrinter.printCompareResult(result);
        }

        checkGameOver(strikeCount);
    }

    private boolean isNothing(int sameDigitCount) {
        return Objects.equals(sameDigitCount, 0);
    }

    private int getStrikeCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int strikeCount = 0;
        for (int index = 0; index < computerNumber.size(); index++) {
            if (Objects.equals(computerNumber.get(index), userNumber.get(index))) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    private void checkGameOver(int strikeCount) {
        if (isGameOver(strikeCount)) {
            gameOver = true;
        }
    }

    private boolean isGameOver(int strikeCount) {
        return Objects.equals(strikeCount, REQUIRED_STRIKE_COUNT);
    }
}
