import java.math.BigInteger;

public class Problem97LargeNonMersennePrime {

    public static void main(final String[] args) {
        final String digits = BigInteger.valueOf(2)
                .pow(7830457)
                .multiply(BigInteger.valueOf(28433))
                .add(BigInteger.ONE)
                .toString();
        final String lastTen = digits.substring(digits.length() - 10);
        System.out.println(lastTen);
    }
}
