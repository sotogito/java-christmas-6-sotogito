package christmas.domain.interfaces.calculate;

import christmas.domain.interfaces.DiscountCalculator;
import christmas.enums.Category;

public class WeekdayCalculator implements DiscountCalculator {
    private static final int DISCOUNT_AMOUNT = 2023;
    public static final Category DISCOUNT_CATEGORY = Category.DESSERT;
    @Override
    public int calculate(int constant){
        return DISCOUNT_AMOUNT*constant;
    }
}

