import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Problem56PowerfulDigitSum {

    public static void main(final String[] args) {
        final int maximumDigitalSum = IntStream.range(1, 100)
                .mapToObj(base -> IntStream.range(1, 100)
                        .mapToObj(exp -> BigInteger.valueOf(base).pow(exp)))
                .flatMap(Function.identity())
                .mapToInt(num -> EulerUtils.streamDigits(num.toString()).sum())
                .max()
                .orElseThrow(RuntimeException::new);
        System.out.println(maximumDigitalSum);
    }

}