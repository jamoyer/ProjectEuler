import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Problem92SquareDigitChains {

    public static void main(final String[] args) {
        final SquareDigitChainChecker checker = new SquareDigitChainChecker();
        final long num89s = IntStream.range(1, 10000000)
                .filter(checker::doesNumberArriveAt89)
                .count();
        System.out.println(num89s);
    }

    /**
     * Can check if a number will arrive at 1 or 89 using the Sum of Squares of Digits problem.
     * Optimized by keeping track of numbers which are already known.
     */
    private static class SquareDigitChainChecker {
        private final Set<Long> arriveAt1;
        private final Set<Long> arriveAt89;

        SquareDigitChainChecker() {
            arriveAt1 = new HashSet<>(List.of(1L, 10L, 13L, 32L, 44L));
            arriveAt89 = new HashSet<>(List.of(85L, 89L, 145L, 42L, 20L, 4L, 16L, 37L, 58L));
        }

        boolean doesNumberArriveAt89(final long number) {
            if (arriveAt1.contains(number)) {
                return false;
            }
            if (arriveAt89.contains(number)) {
                return true;
            }
            final List<Long> chain = new ArrayList<>();
            long iterator = number;
            while (!arriveAt1.contains(iterator) && !arriveAt89.contains(iterator)) {
                chain.add(iterator);
                iterator = EulerUtils.streamDigits(iterator).map(num -> num * num).sum();
            }
            if (arriveAt1.contains(iterator)) {
                arriveAt1.addAll(chain);
                return false;
            }
            arriveAt89.addAll(chain);
            return true;
        }
    }
}
