package christmas.domain;

import christmas.util.Validator.MoneyValidator;
import christmas.util.Validator.OrderMenuValidator;

import java.util.*;

public class OrderManager { //이벤트 계산에 필요한 데이터

    private static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    private Map<String, Integer> orderMenu = new LinkedHashMap<>();
    private Map<Category, Integer> orderCategory = new HashMap<>();
    private int totalOrderAmount = 0;

    public OrderManager(Map<String, Integer> orderMenus) {
        int quantityCount = 0;

        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            quantityCount += quantity;
            MenuItem menuItem = Menu.findMenuItem(menu);
            totalOrderAmount += Menu.findOrderMenuAndReturnPrice(menu)*quantity;
            orderCategory.merge(Menu.findCategory(menuItem), quantity, Integer::sum);
        }
        validate(quantityCount);
        orderMenu = orderMenus;
    }

    public Map<String, Integer> getOrderMenuAndQuantity() {
        return orderMenu;
    }

    public Map<Category, Integer> getOrderCategoryAndQuantity() {
        return orderCategory;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    private void validate(int quantityCount) {
        //MoneyValidator.isWithinRangeAmount(totalOrderAmount);
        OrderMenuValidator.orderMenuValidator(quantityCount, orderCategory);
    }


}
