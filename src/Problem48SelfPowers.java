import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem48SelfPowers {

    public static void main(String[] args) {

        final String sum = IntStream.rangeClosed(1, 1000)
                .mapToObj(num -> BigInteger.valueOf(num).pow(num))
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();

        final String lastTenDigits = sum.substring(sum.length() - 10);
        System.out.println(lastTenDigits);
    }

}
