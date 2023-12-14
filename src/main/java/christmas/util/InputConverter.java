package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class InputConverter {
    public static Map<String, Integer> parseMenu(String input) {
        Map<String, Integer> menuMap = new HashMap<>();
        String[] items = input.split(",");

        for (String item : items) {
            String[] parts = item.split("-");
            String menuName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            menuMap.put(menuName, quantity);
        }
        return menuMap;
    }

}
