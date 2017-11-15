import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

public class Problem19CountingSundays {

    public static void main(final String[] args) {
        final LocalDate lastDay = LocalDate.of(2000, Month.DECEMBER, 31);
        final LocalDate firstDay = LocalDate.of(1901, Month.JANUARY, 1);
        final long numSundays = Stream.iterate(firstDay, date -> date.plusMonths(1))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SUNDAY)
                .takeWhile(date -> lastDay.isAfter(date) || lastDay.isEqual(date))
                .count();
        System.out.println(numSundays);
    }
}
