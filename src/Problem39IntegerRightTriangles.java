import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem39IntegerRightTriangles {

    public static void main(final String[] args) {

        final List<List<Integer>> triangles = new ArrayList<>();
        for (int a = 1; a < 500; a++) {
            final int aSquared = a * a;
            for (int b = 1; b < 500; b++) {
                final int cSquared = aSquared + b * b;
                final double cDouble = Math.sqrt(cSquared);
                final int c = (int) cDouble;
                if (cDouble == c) {
                    triangles.add(List.of(a, b, c));
                }
            }
        }

        final Map<Integer, Long> perimeterCounts = triangles.stream()
                .map(triangle -> triangle.stream().mapToInt(Integer::intValue).sum())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final int maxPerimeter = perimeterCounts.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(RuntimeException::new)
                .getKey();

        System.out.println(maxPerimeter);
    }

}
