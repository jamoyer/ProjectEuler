import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public class Problem12HighlyDivisibleTriangularNumber {

    private static final int NUM_DIVISORS_TO_FIND = 500;

    public static void main(final String[] args) {
        final long before = System.currentTimeMillis();

        final LongWithFactors value = LongStream.iterate(1, i -> i + 1)
                .map(i -> LongStream.rangeClosed(0, i)
                        .reduce(1L, (i1, i2) -> i1 + i2 - 1))
                .mapToObj(LongWithFactors::new)
                .filter(LongWithFactors::hasEnoughFactors)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        final long after = System.currentTimeMillis();
        System.out.println(String.format("Time: %d ms", after - before));
        System.out.println(value);
    }

    private static class LongWithFactors {
        private final long value;
        private final Set<Long> factors;

        LongWithFactors(final long value) {
            this.value = value;
            this.factors = EulerUtils.findDivisors(value);
        }

        boolean hasEnoughFactors() {
            return factors.size() > NUM_DIVISORS_TO_FIND;
        }

        @Override
        public String toString() {
            final List<Long> factorList = new ArrayList<>(factors);
            Collections.sort(factorList);
            return "LongWithFactors{" +
                    "value=" + value +
                    ", factors=" + factorList +
                    '}';
        }
    }

}
