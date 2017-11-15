import java.util.HashSet;
import java.util.Set;

public class Problem45TriangularPentagonalAndHexagonal {

    public static void main(String[] args) {
        final int sizeToFind = 4;

        final Set<Long> triangles = new HashSet<>();
        final Set<Long> pentagonals = new HashSet<>();
        final Set<Long> hexagonals = new HashSet<>();

        final Set<Long> triples = new HashSet<>(sizeToFind);
        for (long i = 1; triples.size() < sizeToFind; i++) {
            final long triangle = i * (i + 1) / 2;
            triangles.add(triangle);
            final long pentagon = i * (3 * i - 1) / 2;
            pentagonals.add(pentagon);
            final long hexagon = i * (2 * i - 1);
            hexagonals.add(hexagon);

            if (pentagonals.contains(triangle) && hexagonals.contains(triangle)) {
                triples.add(triangle);
            }
            if (triangles.contains(pentagon) && hexagonals.contains(pentagon)) {
                triples.add(pentagon);
            }
            if (triangles.contains(hexagon) && pentagonals.contains(hexagon)) {
                triples.add(hexagon);
            }
        }
        System.out.println(triples);
    }

}
