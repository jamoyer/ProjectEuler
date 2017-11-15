import java.util.stream.IntStream;

public class Problem30DigitFifthPowers {

    public static void main(final String[] args) {
        final int[] digtFifthPowers = IntStream.range(0, 10)
                .map(num -> (int) Math.pow(num, 5))
                .toArray();

        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += digtFifthPowers[9];
            System.out.println(String.format("Digits: %d, Largest Possible Outcome: %d", i, sum));
        }

        final int outcome = IntStream.rangeClosed(2, 1000000)
                .filter(num -> EulerUtils.streamDigits(num).map(digit -> digtFifthPowers[digit]).sum() == num)
                .peek(System.out::println)
                .sum();
        System.out.println(outcome);
    }

}