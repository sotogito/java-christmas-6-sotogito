package christmas.domain.promotion.calculators;

import christmas.domain.rastaurant.Category;

public class WeekendCalculator implements DiscountCalculator {
    private static final int DISCOUNT_AMOUNT = 2023;
    public static final Category DISCOUNT_CATEGORY = Category.MAIN_COURSE;

    @Override
    public int calculate(int constant) {
        return DISCOUNT_AMOUNT * constant;
    }

}