import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem93ArithmeticExpressions {

    private enum Operation {
        ADD((n1, n2) -> n1 + n2),
        SUBTRACT((n1, n2) -> n1 - n2),
        MULTIPLY((n1, n2) -> n1 * n2),
        DIVIDE((n1, n2) -> n1 / n2);

        private static final List<Operation> OPERATIONS = Arrays.asList(values());

        private final BiFunction<Double, Double, Double> mathFunc;

        Operation(final BiFunction<Double, Double, Double> mathFunc) {
            this.mathFunc = mathFunc;
        }
    }

    public static void main(final String[] args) {
        final int minimum = 1;
        final int maximum = 10;
        final ArithmeticExpression expression = IntStream.range(minimum, maximum)
                .mapToObj(digit4 -> IntStream.range(minimum, digit4)
                        .mapToObj(digit3 -> IntStream.range(minimum, digit3)
                                .mapToObj(digit2 -> IntStream.range(minimum, digit2)
                                        .mapToObj(digit1 -> List.of(digit1, digit2, digit3, digit4)))
                                .flatMap(Function.identity()))
                        .flatMap(Function.identity()))
                .flatMap(Function.identity())
                .map(ArithmeticExpression::new)
                .peek(System.out::println)
                .max(Comparator.comparing(ArithmeticExpression::getLengthOfLongestSequence))
                .orElseThrow(RuntimeException::new);

        System.out.println(expression);
    }

    static class ArithmeticExpression {
        final List<Integer> digits;
        final int lengthOfLongestSequence;

        int getLengthOfLongestSequence() {
            return lengthOfLongestSequence;
        }

        ArithmeticExpression(final List<Integer> digits) {
            this.digits = digits;
            this.lengthOfLongestSequence = calculateLengthOfLongestSequence(digits);
        }

        private int calculateLengthOfLongestSequence(final List<Integer> digits) {
            final Set<Integer> results = EulerUtils.streamPermutations(digits)
                    .flatMap(permutation -> Operation.OPERATIONS.stream()
                            .flatMap(operation1 -> Operation.OPERATIONS.stream()
                                    .flatMap(operation2 -> Operation.OPERATIONS.stream()
                                            .map(operation3 -> operation3.mathFunc.apply(
                                                    operation2.mathFunc.apply(
                                                            operation1.mathFunc.apply(
                                                                    (double) permutation.get(0),
                                                                    (double) permutation.get(1)),
                                                            (double) permutation.get(2)),
                                                    (double) permutation.get(3))))))
                    .filter(result -> result == result.intValue())
                    .map(Double::intValue)
                    .filter(num -> num > 0)
                    .collect(Collectors.toCollection(TreeSet::new));

            int[] resultsArray = results.stream().mapToInt(x -> x).toArray();
            int count = 1;
            int max = 1;

            for (int i = 1; i < resultsArray.length; i++) {
                if (resultsArray[i] == resultsArray[i - 1] + 1) {
                    count++;
                } else {
                    count = 1;
                }

                if (count > max) {
                    max = count;
                }
            }
            if (digits.equals(List.of(1, 2, 5, 8))) {
                System.out.println("");
            }
            return max;
        }

        @Override
        public String toString() {
            return digits + " " + lengthOfLongestSequence;
        }
    }
}
