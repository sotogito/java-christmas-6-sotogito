package christmas.domain.promotion.calculators;

public class ChristmasDDayCalculator implements DiscountCalculator {
    private static final int BASE_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT = 100;

    @Override
    public int calculate(int constant) {
        return BASE_DISCOUNT_AMOUNT + DISCOUNT_AMOUNT * constant;
    }

}
