import java.util.stream.LongStream;

public class Problem125PalindromicSums {

    public static void main(final String[] args) {
        final long sum = LongStream.range(2, 100000000)
                .filter(EulerUtils::isPalindrome)
                .filter(Problem125PalindromicSums::isSumOfConsecutiveSquares)
                .sum();
        System.out.println(sum);
    }

    private static boolean isSumOfConsecutiveSquares(final long input) {
        for (long i = 1, currentSum = i * i; currentSum < input; ++i, currentSum = i * i) {
            long iterator = i + 1;
            while (currentSum < input) {
                currentSum += iterator * iterator;
                iterator++;
            }
            if (currentSum == input) {
                return true;
            }
        }
        return false;
    }

}
