import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem17NumberLetterCounts {

    private static final String SPACE = " ";

    private static final List<String> SMALLEST_NUMBERS = List.of(
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen");

    private static final List<String> TENS = List.of(
            "",
            "",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety");

    private static final String THOUSAND = "thousand";
    private static final String HUNDRED = "hundred";
    private static final String AND = "and";

    public static void main(final String[] args) {
        final int letterCount = IntStream.rangeClosed(1, 1000)
                .mapToObj(Problem17NumberLetterCounts::convertNumberToWords)
                .flatMap(words -> Stream.of(words.split(" ")))
                .mapToInt(String::length)
                .sum();

        System.out.println(letterCount);
    }

    private static String convertNumberToWords(final int number) {
        if (number >= 10000) {
            if (number == 10000) {
                return "ten thousand";
            }
            throw new UnsupportedOperationException("Numbers greater than ten thousand are not supported.");
        }
        if (number <= 0) {
            throw new UnsupportedOperationException("Non-positive numbers are not supported.");
        }

        final StringJoiner joiner = new StringJoiner(SPACE);
        final int thousands = number / 1000;
        final int thousandsRemainder = number % 1000;
        if (thousands > 0) {
            joiner.add(SMALLEST_NUMBERS.get(thousands)).add(THOUSAND);
        }

        final int hundreds = thousandsRemainder / 100;
        final int hundredsRemainder = thousandsRemainder % 100;
        if (hundreds > 0) {
            joiner.add(SMALLEST_NUMBERS.get(hundreds)).add(HUNDRED);
        }

        if (hundredsRemainder > 0) {
            if (number > 100) {
                joiner.add(AND);
            }
            if (hundredsRemainder >= 20) {
                final int tens = hundredsRemainder / 10;
                final int tensRemainder = hundredsRemainder % 10;
                joiner.add(TENS.get(tens));
                if (tensRemainder > 0) {
                    joiner.add(SMALLEST_NUMBERS.get(tensRemainder));
                }
            } else {
                joiner.add(SMALLEST_NUMBERS.get(hundredsRemainder));
            }
        }

        return joiner.toString();
    }

}
