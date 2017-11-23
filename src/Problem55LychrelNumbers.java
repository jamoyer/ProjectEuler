import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem55LychrelNumbers {

    public static void main(final String[] args) {
        final long numLychrelNumbers = IntStream.range(1, 10000)
                .filter(num -> Stream.iterate(BigInteger.valueOf(num), previous -> previous.add(EulerUtils.reverseBigInteger(previous)))
                        .skip(1)
                        .limit(50)
                        .map(BigInteger::toString)
                        .noneMatch(EulerUtils::isPalindrome))
                .count();
        System.out.println(numLychrelNumbers);
    }

}