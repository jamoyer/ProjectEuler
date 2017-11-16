import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;

public class Problem20FactorialDigitSum {

    public static void main(final String[] args) {
        final BigInteger factorial = BigIntegerMath.factorial(100);
        final int sumOfDigits = EulerUtils.streamDigits(factorial.toString()).sum();

        System.out.println(sumOfDigits);
    }
}
