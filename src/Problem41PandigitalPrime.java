import java.util.Comparator;
import java.util.List;

public class Problem41PandigitalPrime {

    public static void main(final String[] args) {
        final List<Integer> seed = List.of(1, 2, 3, 4, 5, 6, 7);
        final long maxPandigitalPrime = EulerUtils.streamPermutations(seed)
                .map(EulerUtils::mergeDigits)
                .filter(EulerUtils::isPrime)
                .max(Comparator.naturalOrder())
                .orElseThrow(RuntimeException::new);
        System.out.println(maxPandigitalPrime);
    }

}