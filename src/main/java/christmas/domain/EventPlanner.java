package christmas.domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {

    private static final int CAN_COMPLIMENTARY_MIN = 120000;
    private static final String COMPLIMENTARY_MENU = "샴페인";
    private static final int COMPLIMENTARY_QUANTITY = 1;
    private static final int D_DAY_DISCOUNT_BASIC_AMOUNT = 1000;
    private static final int D_DAY_DISCOUNT_ADD_AMOUNT = 100;
    private static int D_DAY_DISCOUNT_ERROR_CONSTANT = 1;
    private Map<Category, Integer> orderCategory = new EnumMap<Category, Integer>(Category.class);
    DailyDiscountItem discountData;
    private boolean canComplimentary;
    private int amountToDiscount; //샴페인 금액 포함 안됨

    public EventPlanner(DailyDiscountItem dayOfWeek,Map<Category, Integer> orderCategory) {
        discountData = dayOfWeek;
        this.orderCategory = orderCategory;

        if(!MoneyManager.isWithinRangeAmount()){
            discountData = DailyDiscountItem.NOTHING;
            D_DAY_DISCOUNT_ERROR_CONSTANT = 0;
        }

    }
    public int getDDayDiscount(int date){
        int discountAmount = D_DAY_DISCOUNT_BASIC_AMOUNT;
        D_DAY_DISCOUNT_ERROR_CONSTANT=1;
        if(!ScheduleManager.isWithinRangeDate()){
            D_DAY_DISCOUNT_ERROR_CONSTANT = 0;
            return D_DAY_DISCOUNT_ERROR_CONSTANT;
        }else if(ScheduleManager.isWithinRangeDate()){
            discountAmount += date*D_DAY_DISCOUNT_ADD_AMOUNT;
            amountToDiscount+=discountAmount*D_DAY_DISCOUNT_ERROR_CONSTANT;
        }
        //System.out.println(D_DAY_DISCOUNT_ERROR_CONSTANT);
        //System.out.println(discountAmount*D_DAY_DISCOUNT_ERROR_CONSTANT+"wefwefwfe");
        return discountAmount*D_DAY_DISCOUNT_ERROR_CONSTANT;
    }


    public int getWeekdayDiscount(){
        int discount = discountData.getWeekdayDiscount();
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()){
            if(discountData.getDiscountMenu() == entry.getKey().getName()){
                discountAmount = discount * entry.getValue();
            }
        }
        amountToDiscount += discountAmount;
        return discountAmount;
    }
    public int getWeekendDiscount(){
        int discount = discountData.getWeekendDiscount();
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()){
            if(discountData.getDiscountMenu() == entry.getKey().getName()){
                discountAmount = discount * entry.getValue();
            }
        }
        amountToDiscount += discountAmount;
        return discountAmount;
    }
    public int getSpecialDiscount(){
        int discount = discountData.getSpecialDiscount();
        amountToDiscount += discount;
        return discount;
    }


    public Map<String,Integer> getComplimentaryMenu(int amount){ //샴페인
        Map<String,Integer> complimentary = new LinkedHashMap<>();
        if(amount>=CAN_COMPLIMENTARY_MIN){
            complimentary.put(COMPLIMENTARY_MENU,COMPLIMENTARY_QUANTITY);
            canComplimentary = true;
        }
        else if(amount<CAN_COMPLIMENTARY_MIN){
            complimentary.put(COMPLIMENTARY_MENU,0);
        }
        return complimentary;
    }

    public int getTotalDiscountAmount(){ //총혜택금액 +샴페인 금액 <총혜택금액>
        if(canComplimentary){
            return amountToDiscount + Menu.findOrderMenuAndReturnPrice(COMPLIMENTARY_MENU);
        }
        return amountToDiscount;
    }

    public int getAmountToDiscount(){ //할인 후 예상 금액에 필요한 금액
        return amountToDiscount;
    }
}