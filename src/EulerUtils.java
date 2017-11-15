import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

final class EulerUtils {

    /**
     * Returns a {@link Set} of this number's proper divisors.
     *
     * @param num The number to find the divisors of.
     * @return A {@link Set} of this number's divisors.
     */
    static Set<Long> findDivisors(final long num) {
        if (num < 1) {
            return Collections.emptySet();
        }

        return LongStream.iterate(1, i -> i + 1)
                .takeWhile(i -> i < Math.sqrt(num))
                .filter(i -> num % i == 0)
                .flatMap(i -> LongStream.of(i, num / i))
                .boxed()
                .collect(Collectors.toSet());
    }

    /**
     * Returns a {@link LongStream} of this number's proper divisors.
     *
     * @param num
     * @return
     * @see EulerUtils#findDivisors(long)
     */
    static LongStream streamProperDivisors(final long num) {
        return EulerUtils.findDivisors(num)
                .stream()
                .mapToLong(Long::longValue)
                .filter(divisor -> divisor != num);
    }

    /**
     * Returns the sum of all this long's proper divisors.
     *
     * @param num
     * @return
     * @see EulerUtils#streamProperDivisors
     */
    static long findProperDivisorSum(final long num) {
        return streamProperDivisors(num).sum();
    }

    /**
     * Checks if the given long value is prime.
     *
     * @param input The input number to check.
     * @return True if this integer is prime, false if not.
     */
    static boolean isPrime(final long input) {
        if (input < 2) return false;
        if (input == 2 || input == 3) return true;
        if (input % 2 == 0 || input % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(input) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (input % (i - 1) == 0 || input % (i + 1) == 0) return false;
        }
        return true;
    }

    /**
     * Checks if the given string is a palindrome.
     *
     * @param input
     * @return
     */
    static boolean isPalindrome(final String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given number is a palindrome.
     *
     * @param input
     * @return
     */
    static boolean isPalindrome(final long input) {
        return isPalindrome(String.valueOf(input));
    }

    /**
     * Given a long value this method returns a list of its digits.
     *
     * @param input
     * @return
     */
    static List<Integer> splitNumber(final long input) {
        return streamDigits(input).boxed().collect(Collectors.toList());
    }

    /**
     * Given a long value this method returns a stream of its digits.
     *
     * @param input
     * @return
     */
    static IntStream streamDigits(final long input) {
        return String.valueOf(input).chars().map(num -> num - 48);
    }

    /**
     * Merges all the Integers of the input list into a single long value.
     *
     * @param digits
     * @return
     */
    static long mergeDigits(final List<Integer> digits) {
        long total = 0;
        for (final Integer i : digits) {
            total = 10 * total + i;
        }
        return total;
    }

    /**
     * Given a Collection, this method returns a stream of all the different permutations of that Collection.
     *
     * @param seed A {@link Collection} of items.
     * @param <T>  The type of the item.
     * @return A {@link Stream} of {@link List}s, where each list is a different permutation of the ordering of the
     * elements from the seed Collection.
     */
    static <T> Stream<List<T>> streamPermutations(final Collection<T> seed) {
        final Iterable<List<T>> iterableDigitPermutator = () -> new PermutationIterator<>(seed);
        return StreamSupport.stream(iterableDigitPermutator.spliterator(), false);
    }

}
