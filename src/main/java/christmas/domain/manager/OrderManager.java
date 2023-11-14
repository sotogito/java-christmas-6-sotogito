package christmas.domain.manager;

import christmas.domain.restaurant.Category;
import christmas.domain.restaurant.Menu;
import christmas.domain.restaurant.MenuItem;
import christmas.util.validator.MoneyValidator;
import christmas.util.validator.OrderMenuValidator;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {
    private Map<String, Integer> orderMenu;
    private Map<Category, Integer> orderCategory = new HashMap<>();
    private int totalOrderAmount = 0;

    public OrderManager(Map<String, Integer> orderMenus) {
        int quantityCount = 0;

        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            quantityCount += quantity;
            MenuItem menuItem = Menu.findMenuItem(menu);
            totalOrderAmount += Menu.findOrderMenuAndReturnPrice(menu) * quantity;
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
        MoneyValidator.isWithinRangeAmount(totalOrderAmount);
        OrderMenuValidator.orderMenuValidator(quantityCount, orderCategory);
    }

}
