package christmas.domain.manager;

import christmas.domain.restaurant.Category;
import christmas.domain.restaurant.Menu;
import christmas.domain.restaurant.MenuItem;
import christmas.util.validator.MoneyValidator;
import christmas.util.validator.OrderMenuValidator;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {
    private final Map<String, Integer> orderMenu;
    private final Map<Category, Integer> orderCategory = new HashMap<>();
    private int totalOrderAmount = 0;

    public OrderManager(Map<String, Integer> orderMenus) {
        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            MenuItem menuItem = Menu.findMenuItem(menu);
            totalOrderAmount += Menu.findOrderMenuAndReturnPrice(menu) * quantity;
            orderCategory.merge(Menu.findCategory(menuItem), quantity, Integer::sum);
        }
        validate();
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

    private void validate() {
        MoneyValidator.isWithinRangeAmount(totalOrderAmount);
        OrderMenuValidator.orderMenuValidator(orderCategory);
    }

}
