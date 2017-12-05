import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem63PowerfulDigitCounts {

    /**
     * We only need to calculate up to 9^21.
     * The base upper limit is 9 because (10 or greater)^n is always at least n+1 digits and thus cannot satisfy the requirements.
     * The exponent upper limit can be solved for with some quick trial and error by taking 9^n and seeing that beyond 21, 9^n is always has less than n digits.
     * Thus we can just iterate through all combinations of base and exponent up to the limit and check how many combinations meet the requirements.
     */
    public static void main(final String[] args) {
        final long digitCounts = IntStream.rangeClosed(1, 9)
                .flatMap(base -> IntStream.rangeClosed(1, 21).filter(exp -> BigInteger.valueOf(base).pow(exp).toString().length() == exp))
                .count();
        System.out.println(digitCounts);
    }
}
