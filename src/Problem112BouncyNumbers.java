import java.util.List;

public class Problem112BouncyNumbers {

    public static void main(final String[] args) {
        long currentNumber = 0;
        long bouncy = 0;
        long notBouncy = 0;
        do {
            if (isBouncy(++currentNumber)) {
                bouncy++;
            } else {
                notBouncy++;
            }
        } while (((double) bouncy) / (bouncy + notBouncy) < 0.99);
        System.out.println(currentNumber);
    }

    private static boolean isBouncy(final long number) {
        boolean increases = false;
        boolean decreases = false;
        final List<Integer> digits = EulerUtils.splitNumber(number);
        int previous = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            final int current = digits.get(i);
            if (current > previous) {
                increases = true;
            }
            if (current < previous) {
                decreases = true;
            }
            previous = current;
        }
        return increases && decreases;
    }
}
