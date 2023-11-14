package christmas.domain.promotion.policy;

import christmas.domain.EventPlanner;
import christmas.domain.manager.ScheduleManager;
import christmas.domain.promotion.ComplimentaryItem;
import christmas.domain.promotion.policy.DiscountType;

import java.util.EnumMap;
import java.util.Map;

public class DiscountDetail {
    private Map<DiscountType, Integer> discountDetails = new EnumMap<DiscountType, Integer>(DiscountType.class);

    public DiscountDetail(EventPlanner eventPlanner, ScheduleManager scheduleManager) {
        int dDay = eventPlanner.getDDayDiscount(scheduleManager.calculateDDay());
        int weekday = eventPlanner.getWeekdayDiscount();
        int weekend = eventPlanner.getWeekendDiscount();
        int special = eventPlanner.getSpecialDiscount();
        int event = ComplimentaryItem.getAmount();

        discountDetails.put(DiscountType.CHRISTMAS_DDAY_DISCOUNT, dDay);
        discountDetails.put(DiscountType.WEEKDAY_DISCOUNT, weekday);
        discountDetails.put(DiscountType.WEEKEND_DISCOUNT, weekend);
        discountDetails.put(DiscountType.SPECIAL_DISCOUNT, special);
        discountDetails.put(DiscountType.COMPLIMENTARY_EVENT, event);
    }

    public Map<DiscountType, Integer> getDiscountDetails() {
        return discountDetails;
    }

}