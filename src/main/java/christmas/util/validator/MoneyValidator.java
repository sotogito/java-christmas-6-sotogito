package christmas.util.validator;

public class MoneyValidator {
    private static final int MIN_AMOUNT = 10000;

    private static final String NOTICE_MIN_RANGE = ("총주문 금액 %s원 이상부터 이벤트가 적용됩니다.\n");

    public static void isWithinRangeAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            String minAmountFormatted = String.format("%,d", MIN_AMOUNT);
            String MSG = String.format(NOTICE_MIN_RANGE, minAmountFormatted);
            System.out.print(MSG);
        }
    }

}
