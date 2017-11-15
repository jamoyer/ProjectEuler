import java.util.List;

public class Problem43SubStringDivisibility {

    public static void main(final String[] args) {
        final List<Integer> pandigitalDigits = List.of(0, 1, 2,3, 4,5, 6, 7, 8, 9);
        final long sum = EulerUtils.streamPermutations(pandigitalDigits)
                .filter(Problem43SubStringDivisibility::hasSubStringDivisibilityProperty)
                .mapToLong(EulerUtils::mergeDigits)
                .peek(System.out::println)
                .sum();
        System.out.println(sum);
    }

    private static boolean hasSubStringDivisibilityProperty(final List<Integer> digits) {
        return EulerUtils.mergeDigits(digits.subList(7, 10)) % 17 == 0 &&
                EulerUtils.mergeDigits(digits.subList(6, 9)) % 13 == 0 &&
                EulerUtils.mergeDigits(digits.subList(5, 8)) % 11 == 0 &&
                EulerUtils.mergeDigits(digits.subList(4, 7)) % 7 == 0 &&
                EulerUtils.mergeDigits(digits.subList(3, 6)) % 5 == 0 &&
                EulerUtils.mergeDigits(digits.subList(2, 5)) % 3 == 0 &&
                EulerUtils.mergeDigits(digits.subList(1, 4)) % 2 == 0;
    }

}