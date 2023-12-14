package christmas.domain;

import christmas.domain.promotion.calculators.DiscountCalculator;
import christmas.domain.rastaurant.Menu;
import christmas.domain.promotion.calculators.ChristmasDDayCalculator;
import christmas.domain.promotion.calculators.SpecialCalculator;
import christmas.domain.promotion.calculators.WeekdayCalculator;
import christmas.domain.promotion.calculators.WeekendCalculator;
import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.OrderMenuManager;
import christmas.domain.manager.ScheduleManager;
import christmas.domain.promotion.ComplimentaryItem;
import christmas.domain.promotion.policy.DailyDiscountItem;
import christmas.domain.promotion.policy.DiscountType;

import java.util.EnumMap;
import java.util.Map;

public class EventPlanner {

    private static final Map<DiscountType, Integer> discountList = new EnumMap<>(DiscountType.class);

    public EventPlanner(MoneyManager moneyManager, ScheduleManager scheduleManager, OrderMenuManager orderMenuManager) {

        if (moneyManager.isAmountAboveMinimum()) {
            calculateDiscount(scheduleManager, orderMenuManager);
            updateComplimentaryItemToList(moneyManager.getOrderAmount());
        }
    }

    private void calculateDiscount(ScheduleManager scheduleManager, OrderMenuManager orderMenuManager) {
        DailyDiscountItem dailyDiscountItem = scheduleManager.getDayOfWeek();

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
    }



    private int calculateChristmasDDay(ScheduleManager scheduleManager) {
        DiscountCalculator discountCalculator = new ChristmasDDayCalculator();
        int discountConstant = scheduleManager.calculateDDay();

        return discountCalculator.calculate(discountConstant);
    }

    private int calculateWeekday(OrderMenuManager orderMenuManager) {
        DiscountCalculator discountCalculator = new WeekdayCalculator();
        int discountConstant = orderMenuManager.findCategoryCount(WeekdayCalculator.DISCOUNT_CATEGORY);

        return discountCalculator.calculate(discountConstant);

    }

    private int calculateWeekend(OrderMenuManager orderMenuManager) {
        DiscountCalculator discountCalculator = new WeekendCalculator();
        int discountConstant = orderMenuManager.findCategoryCount(WeekendCalculator.DISCOUNT_CATEGORY);

        return discountCalculator.calculate(discountConstant);
    }

    private int calculateSpecial() {
        DiscountCalculator discountCalculator = new SpecialCalculator();

        return discountCalculator.calculate(SpecialCalculator.CONSTANT);
    }

    public void updateComplimentaryItemToList(int amount) {
        if (ComplimentaryItem.isGetItem(amount)) {

            String item = ComplimentaryItem.findItem(amount).getItem();
            int price = Menu.findMenuPrice(Menu.findMenuItem(item));
            discountList.put(DiscountType.COMPLIMENTARY_ITEM, price);
        }
    }


    public Map<DiscountType, Integer> getDiscountList() {
        return discountList;
    }

    public int getDiscountAmount() {
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
