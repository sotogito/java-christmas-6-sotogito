package christmas.util.validator;

import christmas.domain.restaurant.Category;
import christmas.view.InputView;

import java.util.Map;

public class OrderMenuValidator {
    private static final int MIN = 1;
    private static final int MAX = 20;
    private static final String NOTICE_ORDER_MENU = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n";
    private static final String NOTICE_ONLY_BEVERAGE = "음료만 주문 시, 주문할 수 없습니다.\n";

    public static void orderMenuValidator(Map<Category, Integer> orderCategory) {
        int quantity = orderCategory.values().stream().mapToInt(Integer::intValue).sum();

        if (!isWithinRangeNum(quantity)) {
            throw new IllegalArgumentException(NOTICE_ORDER_MENU);
        } else if (isOverMinQuantity(quantity)) {
            throw new IllegalArgumentException(InputView.ERROR_ORDER_MENU);
        } else if (isOnlyBeverage(orderCategory)) {
            throw new IllegalArgumentException(NOTICE_ONLY_BEVERAGE);
        }
    }

    private static boolean isWithinRangeNum(int quantity) {
        return quantity <= MAX;
    }

    private static boolean isOverMinQuantity(int quantity) {
        return quantity < MIN;
    }

    private static boolean isOnlyBeverage(Map<Category, Integer> orderCategories) {
        return orderCategories.size() == 1 && orderCategories.containsKey(Category.BEVERAGE);
    }

}
