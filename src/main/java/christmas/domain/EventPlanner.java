package christmas.domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {

    private static final int CAN_COMPLIMENTARY_MIN = 120000;
    private static final String COMPLIMENTARY_MENU = "샴페인";
    private static final int COMPLIMENTARY_QUANTITY = 1;
    private Map<Category, Integer> orderCategory = new EnumMap<Category, Integer>(Category.class);
    DailyDiscountItem discountData;
    private boolean canComplimentary;
    private int amountToDiscount; //샴페인 금액 포함 안됨

    public EventPlanner(DailyDiscountItem dayOfWeek,Map<Category, Integer> orderCategory) {
        discountData = dayOfWeek;
        this.orderCategory = orderCategory;

        if(!MoneyManager.isWithinRangeAmount()){
            discountData = DailyDiscountItem.NOTHING;
        }

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




    private int getDiscountAmount(Category category, Map<Category, Integer> orderCategory) {
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()) {
            if (entry.getKey() == category) {
                discountAmount += 2023 * entry.getValue();
            }
        }
        return discountAmount;
        //만약 금액이 1000원 이하면 그냥 바로 0원을 반환

    }








    public Map<String,Integer> getComplimentaryMenu(int amount){ //샴페인
        Map<String,Integer> complimentary = new LinkedHashMap<>();
        if(amount>=CAN_COMPLIMENTARY_MIN){
            complimentary.put(COMPLIMENTARY_MENU,COMPLIMENTARY_QUANTITY);
            System.out.println("넘어");
            canComplimentary = true;
        }
        else if(amount<CAN_COMPLIMENTARY_MIN){
            complimentary.put(COMPLIMENTARY_MENU,0);
        }
        return complimentary;
    }

    public int getTotalDiscountAmount(){ //총혜택금액 +샴페인 금액 <총혜택금액>
        if(canComplimentary){
            return amountToDiscount += Menu.findOrderMenuAndReturnPrice(COMPLIMENTARY_MENU);
        }
        return amountToDiscount;
    }

    public int getAmountToDiscount(){ //할인 후 예상 금액에 필요한 금액
        return amountToDiscount;
    }
}
