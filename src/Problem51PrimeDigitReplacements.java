import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem51PrimeDigitReplacements {

    public static void main(final String[] args) {

        final PrimeChecker primeChecker = new PrimeChecker();
        for (long i = 2; ; i++) {
            final boolean isPrime = primeChecker.isPrime(i);
            if (!isPrime) {
                continue;
            }
            final long potential = i;
            final List<Integer> digits = EulerUtils.splitNumber(potential);
            final Optional<List<Long>> primeFamily = generateIndexPermutations(potential)
                    .map(indicesToReplace -> IntStream.rangeClosed(0, 9)
                            .mapToLong(replacementDigit -> swapDigits(replacementDigit, indicesToReplace, digits))
                            .filter(num -> num >= potential)
                            .filter(primeChecker::isPrime)
                            .boxed()
                            .collect(Collectors.toList()))
                    .filter(list -> list.size() == 8)
                    .findFirst();
            if (primeFamily.isPresent()) {
                System.out.println(primeFamily.get()); // answer is the first number in this list
                break;
            }
        }
    }

    /**
     * Object to cache prime check for numbers.
     */
    private static class PrimeChecker {
        private final Map<Long, Boolean> primeMap = new HashMap<>();

        boolean isPrime(final long number) {
            return primeMap.computeIfAbsent(number, EulerUtils::isPrime);
        }
    }

    private static long swapDigits(final int replacementDigit, final List<Integer> indicesToReplace, final List<Integer> digits) {
        final List<Integer> newDigits = new ArrayList<>(digits);
        for (final Integer index : indicesToReplace) {
            newDigits.set(index, replacementDigit);
        }
        return EulerUtils.mergeDigits(newDigits);
    }

    private static Stream<List<Integer>> generateIndexPermutations(final long input) {
        final int numDigits = EulerUtils.countDigits(input);

        return LongStream.range(0, (long) Math.pow(2, numDigits))
                .mapToObj(Long::toBinaryString)
                .map(binaryString -> IntStream.range(0, binaryString.length())
                        .filter(characterIndex -> binaryString.charAt(characterIndex) == '1')
                        .map(index -> binaryString.length() - 1 - index)
                        .boxed()
                        .collect(Collectors.toList()));

    }

}