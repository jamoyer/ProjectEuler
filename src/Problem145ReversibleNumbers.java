import java.util.stream.IntStream;

public class Problem145ReversibleNumbers {

    public static void main(final String[] args) {
        final long numReversible = IntStream.range(1, 1000000000)
                .filter(num -> num % 10 != 0)
                .filter(Problem145ReversibleNumbers::isReversible)
                .count();

        System.out.println(numReversible);

        System.out.println(isReversible(409));
        System.out.println(isReversible(36));
    }

    private static boolean isReversible(final long input) {
        final String inputStr = String.valueOf(input);
        final String reverseInputStr = new StringBuilder(inputStr).reverse().toString();
        final long reversedInput = Integer.parseInt(reverseInputStr);
        final long sum = reversedInput + input;
        return String.valueOf(sum).chars().allMatch(digit -> digit % 2 != 0);
    }

}