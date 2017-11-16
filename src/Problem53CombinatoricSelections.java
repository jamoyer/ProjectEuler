import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.function.Function;
import java.util.stream.LongStream;

public class Problem53CombinatoricSelections {

    public static void main(final String[] args) {
        final long count = LongStream.rangeClosed(1, 100)
                .mapToObj(n -> LongStream.rangeClosed(1, n)
                        .mapToObj(r -> CombinatoricsUtils.binomialCoefficientDouble((int) n, (int) r)))
                .flatMap(Function.identity())
                .filter(combinations -> combinations > 1000000)
                .count();
        System.out.println(count);
    }

}