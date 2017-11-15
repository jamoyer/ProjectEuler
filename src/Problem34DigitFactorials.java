import com.google.common.math.BigIntegerMath;

import java.util.stream.IntStream;

public class Problem34DigitFactorials {

    public static void main(final String[] args) {
        final int[] digitFactorials = IntStream.range(0, 10)
                .map(num -> BigIntegerMath.factorial(num).intValue())
                .toArray();

        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += digitFactorials[9];
            System.out.println(String.format("Digits: %d, Largest Possible Outcome: %d", i, sum));
        }

        final int outcome = IntStream.rangeClosed(3, 2540160) // 7 digits is the upper bound, 2540160 = (9!) * 7
                .filter(num -> EulerUtils.streamDigits(num).map(digit -> digitFactorials[digit]).sum() == num)
                .sum();

        System.out.println(outcome);
    }

}