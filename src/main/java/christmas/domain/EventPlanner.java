package christmas.domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private static final int D_DAY_DISCOUNT_BASIC_AMOUNT = 1000;
    private static final int D_DAY_DISCOUNT_ADD_AMOUNT = 100;
    private static int D_DAY_DISCOUNT_ERROR_CONSTANT;
    private Map<Category, Integer> orderCategory;
    DailyDiscountItem discountData;
    private int amountToDiscount; //샴페인 금액 포함 안됨

    public EventPlanner(DailyDiscountItem dayOfWeek,Map<Category, Integer> orderCategory) {
        this.orderCategory = orderCategory;

        if(!MoneyManager.isWithinRangeAmount()){
            discountData = DailyDiscountItem.NOTHING;
            D_DAY_DISCOUNT_ERROR_CONSTANT=0;
        }else if(MoneyManager.isWithinRangeAmount()){
            discountData = dayOfWeek;
            D_DAY_DISCOUNT_ERROR_CONSTANT=1;
        }

    }
    public int getDDayDiscount(int date){
        int discountAmount = D_DAY_DISCOUNT_BASIC_AMOUNT;

        if(!ScheduleManager.isWithinRangeDate()){
            D_DAY_DISCOUNT_ERROR_CONSTANT = 0;
            return D_DAY_DISCOUNT_ERROR_CONSTANT;
        }else if(ScheduleManager.isWithinRangeDate()){
            discountAmount += date*D_DAY_DISCOUNT_ADD_AMOUNT;
            amountToDiscount+=discountAmount*D_DAY_DISCOUNT_ERROR_CONSTANT;
        }

        return discountAmount*D_DAY_DISCOUNT_ERROR_CONSTANT;
    }


    public int getWeekdayDiscount(){
        int discount = discountData.getWeekday();
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()){
            if(discountData.getDiscountMenu().equals(entry.getKey().getName())){
                discountAmount = discount * entry.getValue();
            }
        }
        amountToDiscount += discountAmount;
        return discountAmount;
    }
    public int getWeekendDiscount(){
        int discount = discountData.getWeekend();
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()){
            if(discountData.getDiscountMenu().equals(entry.getKey().getName())){
                discountAmount = discount * entry.getValue();
            }
        }
        amountToDiscount += discountAmount;
        return discountAmount;
    }
    public int getSpecialDiscount(){
        int discount = discountData.getSpecial();
        amountToDiscount += discount;
        return discount;
    }

    public int getTotalDiscountAmount(){ //총 혜택 금액 +샴페인 금액 <총 혜택 금액>
        return amountToDiscount + ComplimentaryItem.getAmount();
    }

    public int getAmountToDiscount(){ //할인 후 예상 금액 계산에 필요한 금액
        return amountToDiscount;
    }
}