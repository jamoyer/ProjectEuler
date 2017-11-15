import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem21AmicableNumbers {

    public static void main(String[] args) {
        int max = 10000;
        int[] divisorSums = IntStream.range(0, max)
                .map(num -> (int) EulerUtils.findProperDivisorSum(num))
                .toArray();

        Set<Set<Integer>> amicablePairs = IntStream.range(0, max)
                .skip(1)
                .filter(num -> divisorSums[num] < max && num == divisorSums[divisorSums[num]] && num != divisorSums[num])
                .mapToObj(num -> Set.of(num, divisorSums[num]))
                .collect(Collectors.toSet());
        System.out.println(amicablePairs);

        int sum = amicablePairs.stream()
                .flatMap(Set::stream)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }

}
