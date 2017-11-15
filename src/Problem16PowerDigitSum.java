import java.math.BigInteger;
import java.util.Arrays;

public class Problem16PowerDigitSum {

    private static final int POWER_TO_FIND = 1000000;

    public static void main(final String[] args) {
        final BigInteger powered = BigInteger.valueOf(2L).pow(POWER_TO_FIND);
        final String[] digits = powered.toString().split("");
        final int digitSum = Arrays.stream(digits)
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(digitSum);
    }

}