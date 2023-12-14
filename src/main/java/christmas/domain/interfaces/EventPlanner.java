package christmas.domain.interfaces;

import christmas.domain.MenuItem;
import christmas.domain.interfaces.calculate.ChristmasDDayCalculator;
import christmas.domain.interfaces.calculate.SpecialCalculator;
import christmas.domain.interfaces.calculate.WeekdayCalculator;
import christmas.domain.interfaces.calculate.WeekendCalculator;
import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.OrderMenuManager;
import christmas.domain.manager.ScheduleManager;
import christmas.enums.Category;
import christmas.enums.ComplimentaryItem;
import christmas.enums.DailyDiscountItem;
import christmas.enums.DiscountType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {

    private static final Map<DiscountType,Integer> discountList = new EnumMap<>(DiscountType.class);

    public EventPlanner(MoneyManager moneyManager,ScheduleManager scheduleManager, OrderMenuManager orderMenuManager) {
        DailyDiscountItem dailyDiscountItem = scheduleManager.getDayOfWeek();
        //일단 구매 금액이 10000원이 넘는지 확인
        if (scheduleManager.isWithinRangeDate()) {
            discountList.put(DiscountType.CHRISTMAS_D_DAY, calculateChristmasDDay(scheduleManager));
        }
        if (dailyDiscountItem.getWeekend()) {
            discountList.put(DiscountType.WEEKEND, calculateWeekend(orderMenuManager));
        } else if (dailyDiscountItem.getWeekday()) {
            discountList.put(DiscountType.WEEKDAY, calculateWeekday(orderMenuManager));
        }
        if (dailyDiscountItem.getSpecial()) {
            discountList.put(DiscountType.SPECIAL, calculateSpecial());
        }
        updateComplimentaryItemToList(moneyManager.getOrderAmount());
    }

    private int calculateChristmasDDay(ScheduleManager scheduleManager){
        DiscountCalculator discountCalculator = new ChristmasDDayCalculator();
        int discountConstant = scheduleManager.calculateDDay();

        return discountCalculator.calculate(discountConstant);
    }

    private int calculateWeekday(OrderMenuManager orderMenuManager){
        DiscountCalculator discountCalculator = new WeekdayCalculator();
        int discountConstant = orderMenuManager.findCategoryCount(WeekdayCalculator.DISCOUNT_CATEGORY);

        return discountCalculator.calculate(discountConstant);

    }

    private int calculateWeekend(OrderMenuManager orderMenuManager){
        DiscountCalculator discountCalculator = new WeekendCalculator();
        int discountConstant = orderMenuManager.findCategoryCount(WeekendCalculator.DISCOUNT_CATEGORY);

        return discountCalculator.calculate(discountConstant);
    }

    private int calculateSpecial(){
        DiscountCalculator discountCalculator = new SpecialCalculator();

        return discountCalculator.calculate(SpecialCalculator.CONSTANT);
    }

    public void updateComplimentaryItemToList(int amount){
        if(ComplimentaryItem.isGetItem(amount)){
            discountList.put(DiscountType.COMPLIMENTARY_ITEM,ComplimentaryItem.CHAMPAGNE.getItemPrice());
        }
    }

    public Map<DiscountType,Integer> getDiscountList(){
        return discountList;
    }

    public int getDiscountAmount(){
        return discountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getDiscountAmountForCalculate() {
        return discountList.entrySet().stream()
                .filter(entry -> entry.getKey() != DiscountType.COMPLIMENTARY_ITEM)
                .mapToInt(entry -> entry.getValue())
                .sum();
    }



}