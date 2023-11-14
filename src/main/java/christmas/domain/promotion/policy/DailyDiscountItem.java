package christmas.domain.promotion.policy;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum DailyDiscountItem {
    SUN(DayOfWeek.SUNDAY, 2023, 0, 1000, "디저트"),
    MON(DayOfWeek.MONDAY, 2023, 0, 0, "디저트"),
    TUES(DayOfWeek.TUESDAY, 2023, 0, 0, "디저트"),
    WED(DayOfWeek.WEDNESDAY, 2023, 0, 0, "디저트"),
    THURS(DayOfWeek.THURSDAY, 2023, 0, 0, "디저트"),
    FRI(DayOfWeek.FRIDAY, 0, 2023, 0, "메인"),
    SAT(DayOfWeek.SATURDAY, 0, 2023, 0, "메인"),
    CHRISTMAS(DayOfWeek.SATURDAY, 2023, 0, 1000, "디저트"),
    NOTHING(null, 0, 0, 0, "null");

    private final DayOfWeek dayOfWeek;
    private final int weekday;
    private final int weekend;
    private final int special;
    private final String discountMenu;

    DailyDiscountItem(
            DayOfWeek dayOfWeek, int weekday, int weekend, int special, String discountMenu) {
        this.dayOfWeek = dayOfWeek;
        this.weekday = weekday;
        this.weekend = weekend;
        this.special = special;
        this.discountMenu = discountMenu;
    }

    public static DailyDiscountItem getDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(DailyDiscountItem.values())
                .filter(item -> item.dayOfWeek == dayOfWeek)
                .findFirst()
                .orElse(null);
    }

    public int getWeekday() {
        return weekday;
    }

    public int getWeekend() {
        return weekend;
    }

    public int getSpecial() {
        return special;
    }

    public String getDiscountMenu() {
        return discountMenu;
    }

}
