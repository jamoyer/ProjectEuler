import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem14LongestCollatzSequence {

    private static final int STARTING_NUMBER_LIMIT = 1000000;

    public static void main(final String[] args) {
        final CollatzSequence largestSequence = IntStream.range(1, STARTING_NUMBER_LIMIT)
                .parallel()
                .mapToObj(CollatzSequence::new)
                .max(Comparator.comparing(CollatzSequence::getLength))
                .orElseThrow(RuntimeException::new);
        System.out.println(largestSequence.start);
    }

    static class CollatzSequence {
        final int start;
        final int length;

        int getLength() {
            return length;
        }

        CollatzSequence(final int start) {
            this.start = start;
            this.length = computeCollatzSequenceLength(start);
        }

        private int computeCollatzSequenceLength(final int startingNumber) {
            return (int) LongStream.iterate(startingNumber, num -> (num % 2 == 0) ? (num / 2) : (3 * num + 1))
                    .takeWhile(num -> num != 1)
                    .boxed()
                    .count();
        }
    }
}