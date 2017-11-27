import java.math.BigInteger;

public class Problem97LargeNonMersennePrime {

    public static void main(final String[] args) {
        final BigInteger digits = BigInteger.valueOf(2)
                .pow(7830457)
                .multiply(BigInteger.valueOf(28433))
                .add(BigInteger.ONE)
                .mod(BigInteger.valueOf(10000000000L));
        System.out.println(digits);
    }
}
