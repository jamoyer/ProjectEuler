import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem22NamesScores {

    public static void main(final String[] args) throws IOException {
        final List<Integer> nameScores = Files.lines(Paths.get("names.txt"))
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .map(nameWithQuotes -> nameWithQuotes.substring(1, nameWithQuotes.length() - 1))
                .sorted()
                .map(name -> name.chars()
                        .map(charAsNum -> 26 - ('Z' - charAsNum))
                        .sum())
                .collect(Collectors.toList());

        final Integer nameScoreSum = IntStream.range(0, nameScores.size())
                .map(i -> nameScores.get(i) * (i + 1))
                .sum();

        System.out.println(nameScoreSum);
    }
}