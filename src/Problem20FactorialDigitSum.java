import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem20FactorialDigitSum {

    public static void main(final String[] args) {
        final BigInteger factorial = IntStream.rangeClosed(1, 100)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);

        final int sumOfDigits = EulerUtils.streamDigits(factorial.toString()).sum();

        System.out.println(sumOfDigits);
    }
}
