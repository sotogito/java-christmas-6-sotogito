package christmas.domain;

import christmas.util.Validator.OrderMenuValidator;

import java.util.*;

public class OrderManager { //이벤트 계산에 필요한 데이터

    private static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    private Map<MenuItem,Integer> orderMenu=new LinkedHashMap<>();
    private Map<Category,Integer> orderCategory = new HashMap<>();

    public OrderManager(Map<String, Integer> orderMenus) {
        List<Map.Entry<String, Integer>> orderMenuAndQuantity = new ArrayList<>(orderMenus.entrySet());

        for (Map.Entry<String, Integer> entry : orderMenuAndQuantity) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            OrderMenuValidator.orderMenuValidator(menu,quantity);

            MenuItem menuItem = Menu.findMenuItem(menu);
            orderMenu.put(menuItem,quantity);
            orderCategory.merge(Menu.findCategory(menuItem), quantity, Integer::sum);
        }
    }

    public Map<MenuItem,Integer> getOrderMenuAndQuantity(){
        return orderMenu;
    }

    public Map<Category,Integer> getOrderCategoryAndQuantity(){
        return orderCategory;
    }




}
