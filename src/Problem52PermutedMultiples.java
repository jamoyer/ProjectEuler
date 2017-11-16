import java.util.Set;
import java.util.stream.LongStream;

public class Problem52PermutedMultiples {

    public static void main(final String[] args) {
        final long output = LongStream.iterate(1, i -> i + 1)
                .filter(Problem52PermutedMultiples::permutedMultiplesHaveSameDigits)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        System.out.println(output);
    }

    private static boolean permutedMultiplesHaveSameDigits(final long num) {
        final Set<Integer> digits = EulerUtils.getDigitSet(num);
        return digits.equals(EulerUtils.getDigitSet(num * 6)) &&
                digits.equals(EulerUtils.getDigitSet(num * 5)) &&
                digits.equals(EulerUtils.getDigitSet(num * 4)) &&
                digits.equals(EulerUtils.getDigitSet(num * 3)) &&
                digits.equals(EulerUtils.getDigitSet(num * 2));
    }

}