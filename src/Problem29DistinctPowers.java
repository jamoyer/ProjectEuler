import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Problem29DistinctPowers {

    public static void main(final String[] args) {
        final long numDistinctPowers = IntStream.rangeClosed(2, 100)
                .mapToObj(base -> IntStream.rangeClosed(2, 100)
                        .mapToObj(exp -> BigInteger.valueOf(base).pow(exp)))
                .flatMap(Function.identity())
                .distinct()
                .count();
        System.out.println(numDistinctPowers);
    }

}
