import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem28NumberSpiralDiagonals {

    private static final int SPIRAL_SIZE = 1001;
    private static final int MAX_VALUE = SPIRAL_SIZE * SPIRAL_SIZE;
    private static final int[][] SPIRAL = new int[SPIRAL_SIZE][SPIRAL_SIZE];
    private static final String FORMAT_STRING = getNumberFormatString();
    private static final String DIGIT_DELIMITER = " ";
    private static int currentRow = SPIRAL_SIZE / 2;
    private static int currentColumn = currentRow;
    private static int currentValue = 1;

    public static void main(final String[] args) {
        mark();
        while (true) {
            goRight();
            if (currentRow == 0 && currentColumn == SPIRAL_SIZE - 1) {
                break;
            }
            goDown();
            goLeft();
            goUp();
        }

        final int sum = IntStream.range(0, SPIRAL_SIZE)
                .map(Problem28NumberSpiralDiagonals::findDiagonalSumForColumn)
                .sum();
        System.out.println(sum);

        if (SPIRAL_SIZE < 50) {
            Arrays.stream(SPIRAL)
                    .map(row -> Arrays.stream(row)
                            .mapToObj(num -> String.format(FORMAT_STRING, num))
                            .collect(Collectors.joining(DIGIT_DELIMITER)))
                    .forEach(System.out::println);
        }
    }

    private static void mark() {
        SPIRAL[currentRow][currentColumn] = currentValue++;
    }

    private static void goRight() {
        do {
            currentColumn++;
            mark();
        } while (isWithinBounds() && SPIRAL[currentRow + 1][currentColumn] != 0 && currentColumn + 1 < SPIRAL_SIZE);
    }

    private static void goDown() {
        do {
            currentRow++;
            mark();
        } while (isWithinBounds() && SPIRAL[currentRow][currentColumn - 1] != 0);
    }

    private static void goLeft() {
        do {
            currentColumn--;
            mark();
        } while (isWithinBounds() && SPIRAL[currentRow - 1][currentColumn] != 0);
    }

    private static void goUp() {
        do {
            currentRow--;
            mark();
        } while (isWithinBounds() && SPIRAL[currentRow][currentColumn + 1] != 0);
    }

    private static boolean isWithinBounds() {
        return currentRow < SPIRAL_SIZE && currentColumn < SPIRAL_SIZE &&
                currentRow >= 0 && currentColumn >= 0;
    }

    private static String getNumberFormatString() {
        final int digits = (int) EulerUtils.streamDigits(MAX_VALUE).count();
        return "%" + digits + "d";
    }

    private static int findDiagonalSumForColumn(final int column) {
        final int maxRow = SPIRAL_SIZE - 1;
        final int lowerRowValue = SPIRAL[column][column];
        final int upperRowValue = SPIRAL[maxRow - column][column];
        return lowerRowValue == upperRowValue ? lowerRowValue : lowerRowValue + upperRowValue;
    }

}
