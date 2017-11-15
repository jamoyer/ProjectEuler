import java.util.stream.IntStream;

public class Problem6SumSquareDifference {

    public static void main(final String[] args) {
        final long sumOfSquares = IntStream.rangeClosed(1, 100)
                .map(num -> num * num)
                .sum();

        final int sum = IntStream.rangeClosed(1, 100)
                .sum();
        final long squareOfSum = sum * sum;
        System.out.println(squareOfSum - sumOfSquares);
    }
}
