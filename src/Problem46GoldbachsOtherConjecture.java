import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.LongStream;

public class Problem46GoldbachsOtherConjecture {

    public static void main(String[] args) {
        final Set<Long> primes = new LinkedHashSet<>();

        final long result = LongStream.iterate(2, num -> num + 1)
                .filter(num -> isOddComposite(num, primes))
                .filter(num -> !isSumOfAPrimeAndTwiceASquare(num, primes))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        System.out.println(result);

    }

    private static boolean isOddComposite(final long input, final Set<Long> primes) {
        if (input % 2 == 0) {
            return false;
        }

        if (primes.contains(input)) {
            return false;
        }

        if (EulerUtils.isPrime(input)) {
            primes.add(input);
            return false;
        }

        return true;
    }

    private static boolean isSumOfAPrimeAndTwiceASquare(final long input, final Set<Long> primes) {
        for (final Long prime : primes) {
            long sum = 0;
            for (long root = 1; sum < input; root++) {
                final long twiceASquare = 2 * root * root;
                sum = twiceASquare + prime;
            }
            if (sum == input) {
                return true;
            }
        }
        return false;
    }

}
