import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem49PrimePermutations {

    public static void main(String[] args) {

        final Set<Integer> fourDigitPrimes = IntStream.range(1000, 10000)
                .filter(EulerUtils::isPrime)
                .boxed()
                .collect(Collectors.toSet());

        Iterator<Integer> primeIterator = fourDigitPrimes.iterator();
        while (primeIterator.hasNext()) {
            final Set<Integer> primePermutationSet = findPermutationsWithinSet(primeIterator.next(), fourDigitPrimes);
            final List<Integer> primePermutationList = new ArrayList<>(primePermutationSet);
            Collections.sort(primePermutationList);

            for (int i = 0; i < primePermutationList.size() - 2; i++) {
                final Integer permutation1 = primePermutationList.get(i);
                final Integer permutation2 = primePermutationList.get(i + 1);
                final int distance1To2 = permutation2 - permutation1;
                final int possiblePermutation3 = permutation2 + distance1To2;
                if (primePermutationSet.contains(possiblePermutation3)) {
                    System.out.println(List.of(permutation1, permutation2, possiblePermutation3));
                }
            }
            fourDigitPrimes.removeAll(primePermutationSet);
            primeIterator = fourDigitPrimes.iterator();
        }

    }

    private static Set<Integer> findPermutationsWithinSet(final Integer start, final Set<Integer> set) {
        final List<Integer> digits = EulerUtils.splitNumber(start);

        return EulerUtils.streamPermutations(digits)
                .map(permutation -> (int) EulerUtils.mergeDigits(permutation))
                .filter(set::contains)
                .collect(Collectors.toSet());
    }

}
