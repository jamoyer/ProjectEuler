import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem24LexicographicPermutations {

    public static void main(String[] args) {
        final int PERMUTATION_NUM = 1000000;
        final List<String> digits = IntStream.range(0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
        final PriorityQueue<List<String>> queue = new PriorityQueue<>(PERMUTATION_NUM, new ListComparator().reversed());
        EulerUtils.streamPermutations(digits).forEach(permutation -> {
            queue.add(permutation);
            if (queue.size() > PERMUTATION_NUM) {
                queue.remove();
            }
        });
        final List<String> desiredPermutation = queue.peek();
        final String outCome = join(desiredPermutation);
        System.out.println(outCome);
        System.out.println(queue.size());
    }

    private static String join(List<String> strings) {
        return String.join("", strings);
    }

    private static class ListComparator implements Comparator<List<String>> {

        @Override
        public int compare(List<String> o1, List<String> o2) {
            return join(o1).compareTo(join(o2));
        }
    }

}
