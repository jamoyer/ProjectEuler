import java.util.stream.IntStream;

public class Problem5SmallestMultiple {

    private static final int MAX_DIVIDER = 20;

    public static void main(final String[] args) {

        int num;
        for (num = 1; !evenlyDivisibleUpToNum(num); num++);
        System.out.println(num);
    }

    private static boolean evenlyDivisibleUpToNum(final int multiple) {
        return IntStream.range(1, MAX_DIVIDER + 1)
                .allMatch(x -> multiple % x == 0);
    }
}
