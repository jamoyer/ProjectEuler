import java.util.function.Function;
import java.util.stream.IntStream;

public class Problem9SpecialPythagoreanTriplet {

    private static final int SUM_TO_FIND = 1000;

    public static void main(final String[] args) {
        final long before = System.currentTimeMillis();
        final Triplet triplet = IntStream.range(0, 1000)
                .mapToObj(a -> IntStream.range(0, 1000)
                        .mapToObj(b -> IntStream.range(0, 1000)
                                .mapToObj(c -> new Triplet(a, b, c)))
                        .flatMap(Function.identity()))
                .flatMap(Function.identity())
                .filter(Triplet::satisfiesAllConditions)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        final long after = System.currentTimeMillis();
        System.out.println(after - before);

        System.out.println(triplet);
        System.out.println(triplet.getProduct());
    }

    private static class Triplet {
        private final int a;
        private final int b;
        private final int c;

        Triplet(final int a, final int b, final int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        boolean satisfiesTriplet() {
            return a < b && b < c;
        }

        boolean satisfiesSum() {
            return a + b + c == SUM_TO_FIND;
        }

        boolean satisfiesPythagoreanTheorem() {
            return ((a * a) + (b * b)) == (c * c);
        }

        boolean satisfiesAllConditions() {
            return satisfiesTriplet() && satisfiesSum() && satisfiesPythagoreanTheorem();
        }

        long getProduct() {
            return a * b * c;
        }

        @Override
        public String toString() {
            return "Triplet{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }
}
