import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Problem35CircularPrimes {

    public static void main(final String[] args) {
        final long numCircularPrimes = IntStream.range(2, 1000000)
                .filter(Problem35CircularPrimes::isCircularPrime)
                .count();

        System.out.println(numCircularPrimes);
    }

    private static boolean isCircularPrime(final long input) {
        if (!EulerUtils.isPrime(input)) {
            return false;
        }

        if (input < 10) {
            return true;
        }

        final List<Integer> digits = EulerUtils.splitNumber(input);
        List<Integer> shiftedDigits = digits;
        for (int i = 0; i < digits.size() - 1; i++) {

            final Integer shiftDigit = shiftedDigits.get(0);
            final List<Integer> shiftBuffer = new ArrayList<>(digits.size());
            shiftBuffer.addAll(shiftedDigits.subList(1, digits.size()));
            shiftBuffer.add(shiftDigit);
            shiftedDigits = shiftBuffer;

            final long mergedDigits = EulerUtils.mergeDigits(shiftedDigits);
            if (!EulerUtils.isPrime(mergedDigits)) {
                return false;
            }
        }

        return true;
    }

}
