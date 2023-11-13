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
    private int discountAmount;

    public EventPlanner(Map<Category, Integer> orderCategory) {
        this.orderCategory = orderCategory;
        discountData = ScheduleManager.getDayOfWeek();

        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()) {
            if(entry.getKey().getName() == discountData.getDiscountMenu()){
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
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
        return discountAmount;
    }
    public int getSpecialDiscount(){
        int discount = discountData.getSpecialDiscount();
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()){
            if(discountData.getDiscountMenu() == entry.getKey().getName()){
                discountAmount = discount * entry.getValue();
            }
        }
        return discountAmount;
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
            canComplimentary = true;
        }
        else if(amount<CAN_COMPLIMENTARY_MIN){
            complimentary.put(COMPLIMENTARY_MENU,0);
        }
        return complimentary;
    }

    public int getTotalDiscountAmount(){ //총혜택금액 +샴페인 금액
        if(canComplimentary){
            return discountAmount += Menu.findOrderMenuAndReturnPrice(COMPLIMENTARY_MENU);
        }
        return discountAmount;
    }
}
