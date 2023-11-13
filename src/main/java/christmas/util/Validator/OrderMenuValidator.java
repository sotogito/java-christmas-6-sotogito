package christmas.util.Validator;

import christmas.domain.Category;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderMenuValidator {
    private static final int MIN = 1;
    private static final int MAX = 20;
    private static final String ERROR_ORDER_MENU = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n";
    private static final String NOTICE_ONLY_BEVERAGE = "음료만 주문 시, 주문할 수 없습니다..\n";


    public static void orderMenuValidator(int quantity, Map<Category, Integer> orderCategory) {
        if (!isWithinRangeNum(quantity)) {
            throw new IllegalArgumentException(ERROR_ORDER_MENU);
        } else if (!isOnlyBeverage(orderCategory)) {
            throw new IllegalArgumentException(NOTICE_ONLY_BEVERAGE);
        }

    }

    private static boolean isWithinRangeNum(int quantity) {
        return quantity >= MIN && quantity <= MAX;
    }

    private static boolean isOnlyBeverage(Map<Category, Integer> orderCategorys) {
        Set<Category> checkBeverage = new HashSet<>();
        for (Category category : orderCategorys.keySet()) {
            checkBeverage.add(category);
        }
        if (checkBeverage.size() == 1 && checkBeverage.contains(Category.BEVERAGE)) {
            return false;
        }
        return true;
    }


}
