import java.util.Comparator;
import java.util.stream.IntStream;

public class Problem4LargestPalindromeProduct {

    public static void main(final String[] args) {
        final long before = System.currentTimeMillis();
        final int largestPalindrome = IntStream.range(100, 1000)
                .flatMap(num1 -> IntStream.range(100, 1000).map(num2 -> num1 * num2))
                .mapToObj(String::valueOf)
                .filter(EulerUtils::isPalindrome)
                .map(Integer::parseInt)
                .max(Comparator.naturalOrder())
                .orElseThrow(RuntimeException::new);
        final long after = System.currentTimeMillis();
        System.out.println(after - before);
        System.out.println(largestPalindrome);
    }

}
