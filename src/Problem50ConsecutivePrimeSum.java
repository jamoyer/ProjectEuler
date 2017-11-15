import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem50ConsecutivePrimeSum {

    public static void main(final String[] args) {
        final List<Integer> primes = IntStream.range(1, 1000000)
                .filter(EulerUtils::isPrime)
                .boxed()
                .collect(Collectors.toList());

        final Pair<Integer, List<Integer>> maxConsecutivePrimeSum = primes.stream()
                .map(prime -> new Pair<>(prime, findLargestConsecutivePrimeSequenceForSum(primes, prime)))
                .max(Comparator.comparing(pair -> pair.getValue().size()))
                .orElseThrow(RuntimeException::new);

        System.out.println(maxConsecutivePrimeSum);
    }

    private static List<Integer> findLargestConsecutivePrimeSequenceForSum(final List<Integer> primes, final int sumToFind) {
        int sum = 0;
        int largestIndex = 0;
        int smallestIndex = 0;
        while (sum != sumToFind && largestIndex < primes.size()) {
            for (; largestIndex < primes.size() && sum < sumToFind; largestIndex++) {
                final Integer primeToAdd = primes.get(largestIndex);
                sum += primeToAdd;
            }

            if (sum > sumToFind) {
                // begin backtracking
                for (; smallestIndex < primes.size() && sum > sumToFind; smallestIndex++) {
                    final Integer primeToRemove = primes.get(smallestIndex);
                    sum -= primeToRemove;
                }
            }
        }
        if (sum == sumToFind) {
            return Collections.unmodifiableList(primes.subList(smallestIndex, largestIndex));
        }

        // no solution
        return Collections.emptyList();
    }

}