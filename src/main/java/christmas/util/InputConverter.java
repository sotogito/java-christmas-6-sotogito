package christmas.util;

import christmas.view.InputView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputConverter {
    public static Map<String, Integer> parseMenu(String input) {
        Map<String, Integer> menuMap = new LinkedHashMap<>();

        String[] items = input.split(",");
        for (String item : items) {
            String[] parts = item.split("-");
            if (parts.length < 2) {
                throw new IllegalArgumentException(InputView.ERROR_ORDER_MENU);
            }
            String menuName = parts[0].trim(); // 공백 제거
            int quantity = Integer.parseInt(parts[1].trim()); // 공백 제거 및 문자열을 정수로 변환
            menuMap.put(menuName, quantity);
        }
        return menuMap;
    }

}
