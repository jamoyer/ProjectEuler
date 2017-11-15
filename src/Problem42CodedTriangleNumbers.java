import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem42CodedTriangleNumbers {

    public static void main(final String[] args) throws IOException {
        final TriangleValues values = new TriangleValues();
        final long count = Files.lines(Paths.get("ProjectEulerMaven/words.txt"))
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .map(wordsWithQuotes -> wordsWithQuotes.substring(1, wordsWithQuotes.length() - 1))
                .map(word -> word.chars()
                        .map(Problem42CodedTriangleNumbers::getPositionInAlphabet)
                        .sum())
                .filter(values::isTriangleValue)
                .count();
        System.out.println(count);
    }

    private static int getPositionInAlphabet(final int letter) {
        return letter - 64;
    }

    private static class TriangleValues {
        private final Set<Integer> calculatedValues = new HashSet<>();
        private int maxValue;

        boolean isTriangleValue(final int input) {
            // calculate up to what is needed for this input
            while (input > maxValue) {
                final int iteration = calculatedValues.size() + 1;
                final int nextValue = iteration * (iteration + 1) / 2;
                calculatedValues.add(nextValue);
                maxValue = nextValue;
            }

            return calculatedValues.contains(input);
        }
    }
}