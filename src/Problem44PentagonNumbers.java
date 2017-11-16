import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Problem44PentagonNumbers {

    public static void main(String[] args) {
        final Set<Long> pentagonals = new HashSet<>();

        for (long i = 1; ; i++) {
            final long pentagon = i * (3 * i - 1) / 2;
            pentagonals.add(pentagon);
            final Optional<Long> pentagonalNum = pentagonals.stream()
                    .filter(num -> pentagonals.contains(pentagon - num))
                    .map(num -> num * 2 - pentagon)
                    .filter(pentagonals::contains)
                    .findFirst();
            if (pentagonalNum.isPresent()) {
                System.out.println(pentagonalNum.get());
                break;
            }
        }
    }

}
