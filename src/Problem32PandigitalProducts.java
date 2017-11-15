import java.util.List;
import java.util.Objects;

public class Problem32PandigitalProducts {

    public static void main(final String[] args) {
        final List<Integer> seed = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final long sum = EulerUtils.streamPermutations(seed)
                .map(Problem32PandigitalProducts::getPandigitalProduct)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .distinct()
                .sum();
        System.out.println(sum);
    }

    private static Long getPandigitalProduct(final List<Integer> digits) {
        final long product = EulerUtils.mergeDigits(digits.subList(5, 9));
        for (int i = 0; i < 5; i++) {
            final long first = EulerUtils.mergeDigits(digits.subList(0, i + 1));
            final long second = EulerUtils.mergeDigits(digits.subList(i + 1, 5));
            if (first * second == product) {
                return product;
            }
        }
        return null;
    }

}