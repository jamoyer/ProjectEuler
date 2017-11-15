import java.util.ArrayList;
import java.util.List;

public class Problem3PrimeFactor {

    public static void main(final String[] args) {
        System.out.println(primeFactors(600851475143L));
    }

    private static List<Long> primeFactors(final long number) {
        long n = number;
        final List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }
}
