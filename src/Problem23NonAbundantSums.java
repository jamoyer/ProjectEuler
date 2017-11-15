import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem23NonAbundantSums {

    private static final int NON_ABUNDANT_NUMBER_LIMIT = 28123;

    public static void main(String[] args) {
        final Set<Integer> abundantNums = IntStream.rangeClosed(1, NON_ABUNDANT_NUMBER_LIMIT)
                .filter(num -> EulerUtils.findProperDivisorSum(num) > num)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));

        final int result = IntStream.rangeClosed(1, NON_ABUNDANT_NUMBER_LIMIT)
                .filter(num -> abundantNums.stream()
                        .noneMatch(splitSum -> abundantNums.contains(num - splitSum)))
                .sum();
        System.out.println(result);

    }

}
