import java.util.List;
import java.util.stream.IntStream;

public class Problem37TruncatablePrimes {

    public static void main(final String[] args) {
        final long truncatablePrimeSum = IntStream.iterate(10, i -> i + 1)
                .filter(Problem37TruncatablePrimes::isTruncatablePrime)
                .limit(11)
                .peek(System.out::println)
                .sum();
        System.out.println(truncatablePrimeSum);
    }

    private static boolean isTruncatablePrime(final long input) {
        if (!EulerUtils.isPrime(input)) {
            return false;
        }

        final List<Integer> digits = EulerUtils.splitNumber(input);
        for (int i = 1; i < digits.size(); i++) {
            final long leftTruncated = EulerUtils.mergeDigits(digits.subList(i, digits.size()));
            if (!EulerUtils.isPrime(leftTruncated)) {
                return false;
            }

            final long rightTruncated = EulerUtils.mergeDigits(digits.subList(0, digits.size() - i));
            if (!EulerUtils.isPrime(rightTruncated)) {
                return false;
            }
        }
        return true;
    }

}