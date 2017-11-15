import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem40ChampernownesConstant {

    public static void main(final String[] args) {
        final List<Integer> digits = IntStream.iterate(1, num -> num + 1)
                .flatMap(EulerUtils::streamDigits)
                .limit(1000000)
                .boxed()
                .collect(Collectors.toList());

        final int result = IntStream.iterate(1, num -> num * 10)
                .limit(7)
                .map(num -> num - 1)
                .map(digits::get)
                .reduce(1, (num1, num2) -> num1 * num2);

        System.out.println(result);
    }

}
