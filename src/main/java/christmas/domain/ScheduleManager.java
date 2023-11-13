package christmas.domain;

import christmas.util.Validator.VisitDateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ScheduleManager {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int D_DAY_DISCOUNT_MIN = 1;
    private static final int D_DAY_DISCOUNT_MAX = 25;
    private static int visitDate;

    public ScheduleManager(int visitDate) {
        VisitDateValidator.visitDateValidator(visitDate);
        this.visitDate = visitDate;
    }

    public DailyDiscountItem getDayOfWeek(){
        if(visitDate == 25){
            return DailyDiscountItem.CHRISTMAS;
        }

        LocalDate date = LocalDate.of(YEAR, MONTH, visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return DailyDiscountItem.getDayOfWeek(dayOfWeek);
    }

    public int getVisitDate(){
        return visitDate;
    }
}
