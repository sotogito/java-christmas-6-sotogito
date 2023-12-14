package christmas.domain.manager;

import christmas.domain.rastaurant.Menu;
import christmas.domain.rastaurant.MenuItem;
import christmas.domain.rastaurant.Category;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMenuManager {
    private final Map<MenuItem, Integer> orderMenu = new LinkedHashMap<>();

    public OrderMenuManager(Map<String, Integer> orderMenus) {
        updateOrderMenu(orderMenus);
        //음료만 들어있는지 유효검사getOrderCategoryList()로
        //총 주문이 10000이 넘는지
    }

    public int getOrderTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<MenuItem, Integer> entry : orderMenu.entrySet()) {
            totalAmount += Menu.findMenuPrice(entry.getKey()) * entry.getValue();
        }
        return totalAmount;
    }

    public Map<Category, Integer> getOrderCategoryList() {
        Map<Category, Integer> result = new HashMap<>();
        for (Map.Entry<MenuItem, Integer> entry : orderMenu.entrySet()) {
            result.merge(Menu.findCategory(entry.getKey()), entry.getValue(), Integer::sum);
        }
        return result;
    }

    private void updateOrderMenu(Map<String, Integer> orderMenus) { //메뉴 업데이트
        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            orderMenu.put(Menu.findMenuItem(entry.getKey()), entry.getValue());
        }
    }

    public Map<MenuItem, Integer> getOrderMenu() {
        return orderMenu;
    }

    public int findCategoryCount(Category category) {
        return orderMenu.entrySet().stream()
                .filter(entry -> Menu.findCategory(entry.getKey()) == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

}
