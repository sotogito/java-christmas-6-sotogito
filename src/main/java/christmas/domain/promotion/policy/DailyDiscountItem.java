package christmas.domain.promotion.policy;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum DailyDiscountItem {
    SUN(DayOfWeek.SUNDAY, true, false, true),
    MON(DayOfWeek.MONDAY, true, false, false),
    TUES(DayOfWeek.TUESDAY, true, false, false),
    WED(DayOfWeek.WEDNESDAY, true, false, false),
    THURS(DayOfWeek.THURSDAY, true, false, false),
    FRI(DayOfWeek.FRIDAY, false, true, false),
    SAT(DayOfWeek.SATURDAY, false, true, false),
    CHRISTMAS(DayOfWeek.MONDAY, true, false, true),
    NOTHING(null, false, false, false);

    private final DayOfWeek dayOfWeek;
    private final boolean weekday;
    private final boolean weekend;
    private final boolean special;

    DailyDiscountItem(DayOfWeek dayOfWeek, boolean weekday, boolean weekend, boolean special) {
        this.dayOfWeek = dayOfWeek;
        this.weekday = weekday;
        this.weekend = weekend;
        this.special = special;
    }

    public static DailyDiscountItem getDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(DailyDiscountItem.values())
                .filter(item -> item.dayOfWeek == dayOfWeek)
                .findFirst()
                .orElse(null);
    }

    public boolean getWeekday() {
        return weekday;
    }

    public boolean getWeekend() {
        return weekend;
    }

    public boolean getSpecial() {
        return special;
    }


}
