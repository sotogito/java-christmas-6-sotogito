package christmas.domain;

public class D_DayTest {



    public int getDDayDiscount(int date){
        int discountAmount = 0;
        if(!ScheduleManager.isWithinRangeDate()){
            int D_DAY_DISCOUNT_ERROR_CONSTANT = 0;
            return D_DAY_DISCOUNT_ERROR_CONSTANT;
        }else if(ScheduleManager.isWithinRangeDate()){
            discountAmount += date*100;
        }

        return discountAmount;
    }
}
