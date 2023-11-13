package christmas.domain;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public enum DailyDiscountItem {
    SUN(DayOfWeek.SUNDAY, 2023,0, 1000, "디저트"),
    MON(DayOfWeek.MONDAY, 2023,0, 0, "디저트"),
    TUES(DayOfWeek.TUESDAY, 2023,0, 0, "디저트"),
    WED(DayOfWeek.WEDNESDAY, 2023,0, 0, "디저트"),
    THURS(DayOfWeek.THURSDAY, 2023,0, 0, "디저트"),
    FRI(DayOfWeek.FRIDAY, 0,2023, 0, "메인"),
    SAT(DayOfWeek.SATURDAY, 0,2023, 0, "메인"),
    CHRISTMAS(DayOfWeek.SATURDAY, 2023,0, 1000, "디저트"),
    NOTHING(null, 0,0, 0, null);

    private final DayOfWeek dayOfWeek;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final String discountMenu;

    DailyDiscountItem(
            DayOfWeek dayOfWeek, int weekdayDiscount, int weekendDiscount, int specialDiscount, String discountMenu) {
        this.dayOfWeek = dayOfWeek;
        this.weekdayDiscount =weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.discountMenu = discountMenu;
    }

    public static DailyDiscountItem getDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(DailyDiscountItem.values())
                .filter(item -> item.dayOfWeek == dayOfWeek)
                .findFirst()
                .orElse(null);
    }

    /*
    public static int getWeekendDiscount(DailyDiscountItem discountItem) {
        return discountItem.weekendDiscount;
    }

    public static int getSpecialDiscount(DailyDiscountItem discountItem) {
        return discountItem.specialDiscount;
    }

     */
    public int getWeekdayDiscount(){
        return weekdayDiscount;
    }
    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public String getDiscountMenu() {
        return discountMenu;
    }

}
