package christmas.domain.manager;
import christmas.domain.promotion.policy.DailyDiscountItem;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ScheduleManager {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int D_DAY_DISCOUNT_MIN = 1;
    private static final int D_DAY_DISCOUNT_MAX = 25;
    private static final int D_DAY_DISCOUNT_ERROR_CONSTANT = 0;
    private final int visitDate;

    public ScheduleManager(int visitDate) {
        //날짜 추가 유효 검사
        this.visitDate = visitDate;
    }

    public int getVisitDate(){
        return visitDate;
    }

    public DailyDiscountItem getDayOfWeek() {
        if (visitDate == 25) {
            return DailyDiscountItem.CHRISTMAS;
        }

        LocalDate date = LocalDate.of(YEAR, MONTH, visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return DailyDiscountItem.getDayOfWeek(dayOfWeek);
    }

    public int calculateDDay() {
        if (visitDate >= D_DAY_DISCOUNT_MIN && visitDate <= D_DAY_DISCOUNT_MAX) {
            return visitDate - D_DAY_DISCOUNT_MIN;
        }
        return D_DAY_DISCOUNT_ERROR_CONSTANT;
    }

    public boolean isWithinRangeDate() {
        return visitDate >= D_DAY_DISCOUNT_MIN && visitDate <= D_DAY_DISCOUNT_MAX;
    }


}
