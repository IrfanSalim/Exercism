import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

class Meetup {
    private int monthOfYear;
    private int year;

    Meetup(int monthOfYear, int year) {
        this.monthOfYear = monthOfYear;
        this.year = year;
    }

    LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        LocalDate meetupDay = LocalDate.of(year, monthOfYear, 13);
        switch (schedule) {
            case FIRST:
                return meetupDay.with(TemporalAdjusters.firstInMonth(dayOfWeek));
            case SECOND:
                return meetupDay.with(TemporalAdjusters.dayOfWeekInMonth(2, dayOfWeek));
            case THIRD:
                return meetupDay.with(TemporalAdjusters.dayOfWeekInMonth(3, dayOfWeek));
            case FOURTH:
                return meetupDay.with(TemporalAdjusters.dayOfWeekInMonth(4, dayOfWeek));
            case LAST:
                return meetupDay.with(TemporalAdjusters.lastInMonth(dayOfWeek));
            case TEENTH:
                return meetupDay.with(TemporalAdjusters.nextOrSame(dayOfWeek));
            default:
                return LocalDate.now();
        }
    }
}
