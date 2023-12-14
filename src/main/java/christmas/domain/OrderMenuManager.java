package christmas.domain;

import christmas.enums.Category;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMenuManager {
    private final Map<MenuItem,Integer> orderMenu = new LinkedHashMap<>();

    public OrderMenuManager(Map<String, Integer> orderMenus) {
        updateOrderMenu(orderMenus);
    }


    public int getOrderTotalAmount(){
        int totalAmount = 0;
        for (Map.Entry<MenuItem, Integer> entry : orderMenu.entrySet()) {
            totalAmount += Menu.findMenuPrice(entry.getKey()) * entry.getValue();
        }
        return totalAmount;
    }

    public Map<Category,Integer> getOrderCategoryList(){
        Map<Category,Integer> result = new HashMap<>();
        for (Map.Entry<MenuItem, Integer> entry : orderMenu.entrySet()) {
            result.merge(Menu.findCategory(entry.getKey()),entry.getValue(),Integer::sum);
        }
        return result;
    }

    private void updateOrderMenu(Map<String, Integer> orderMenus){ //메뉴 업데이트
        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            orderMenu.put(Menu.findMenuItem(entry.getKey()),entry.getValue());
        }
    }

    public Map<MenuItem,Integer> getOrderMenu(){
        return orderMenu;
    }





}
