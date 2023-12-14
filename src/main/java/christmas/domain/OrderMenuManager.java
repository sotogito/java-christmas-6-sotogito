package christmas.domain;

import java.util.Map;

public class OrderMenuManager {
    private Map<MenuItem,Integer> orderMenu;

    public OrderMenuManager(Map<String, Integer> orderMenu) {
        //String -> MenumItem으로 변환
        this.orderMenu = orderMenu;
    }

}
