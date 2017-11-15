import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Problem44PentagonNumbers {

    public static void main(String[] args) {
        final Set<Long> pentagonals = new LinkedHashSet<>();

        for (long i = 1; ; i++) {
            final long pentagon = i * (3 * i - 1) / 2;
            pentagonals.add(pentagon);
            final Optional<Long> pentagonalNum = findPentagonalSumAndDifference(pentagon, pentagonals);
            if (pentagonalNum.isPresent()) {
                System.out.println(pentagonalNum.get());
                break;
            }
        }
    }

    private static Optional<Long> findPentagonalSumAndDifference(final long pentagonal, final Set<Long> pentagonals) {
        return pentagonals.stream()
                .sorted(Collections.reverseOrder())
                .filter(num -> pentagonals.contains(num + pentagonal))
                .map(num -> pentagonal - num)
                .filter(pentagonals::contains)
                .findFirst();
    }

}
