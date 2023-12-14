package christmas.domain.promotion.calculators;

public class SpecialCalculator implements DiscountCalculator {
    private static final int DISCOUNT_AMOUNT = 1000;
    public static final int CONSTANT = 1;

    @Override
    public int calculate(int constant) {
        return DISCOUNT_AMOUNT * constant;
    }

}
