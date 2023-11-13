package christmas.domain;

import christmas.util.Validator.OrderMenuValidator;

import java.util.*;

public class OrderManager { //이벤트 계산에 필요한 데이터

    private static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    private Map<MenuItem, Integer> orderMenu = new LinkedHashMap<>();
    private Map<Category, Integer> orderCategory = new HashMap<>();
    private int totalOrderAmount = 0;

    public OrderManager(Map<String, Integer> orderMenus) {
        List<Map.Entry<String, Integer>> orderMenuAndQuantity = new ArrayList<>(orderMenus.entrySet());
        System.out.println(orderMenuAndQuantity.size());
        int quantityCount = 0;

        for (Map.Entry<String, Integer> entry : orderMenuAndQuantity) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            quantityCount += quantity;
            MenuItem menuItem = Menu.findMenuItem(menu);
            //totalOrderAmount += menuItem.getPrice()*quantity; //역시 3개 분리해야될듯

            totalOrderAmount += Menu.findOrderMenuAndReturnPrice(menu)*quantity;
            orderMenu.put(menuItem, quantity);


            orderCategory.merge(Menu.findCategory(menuItem), quantity, Integer::sum);



        }
        validate(quantityCount);
        System.out.println(totalOrderAmount);

    }

    public Map<MenuItem, Integer> getOrderMenuAndQuantity() {
        return orderMenu;
    }

    public Map<Category, Integer> getOrderCategoryAndQuantity() {
        return orderCategory;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    private void validate(int quantityCount) {
        //MoneyValidator.isWithinRangeAmount(totalOrderAmount); 이게 오류네
        OrderMenuValidator.orderMenuValidator(quantityCount, orderCategory);
    }


}
