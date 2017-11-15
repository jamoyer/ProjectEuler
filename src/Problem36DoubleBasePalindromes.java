import java.util.stream.IntStream;

public class Problem36DoubleBasePalindromes {

    public static void main(final String[] args) {
        final int result = IntStream.range(1, 1000000)
                .filter(num -> num % 2 != 0)
                .filter(EulerUtils::isPalindrome)
                .filter(num -> EulerUtils.isPalindrome(Integer.toBinaryString(num)))
                .peek(System.out::println)
                .sum();

        System.out.println(result);
    }

}