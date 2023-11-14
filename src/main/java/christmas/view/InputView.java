package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validator.VisitDateValidator;

import java.util.*;

public class InputView {

    private static final String MESSAGE_INTRO_EVENT_PLANNER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n";
    private static final String MESSAGE_INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String MESSAGE_INPUT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n";
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    public static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";


    public int getVisitData() {
        System.out.print(MESSAGE_INPUT_VISIT_DATE);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_VISIT_DATE);
        }
    }

    public Map<String, Integer> getOrderMenu() {
        System.out.print(MESSAGE_INPUT_ORDER_MENU);
        String input = Console.readLine();
        String[] menuAndQuantity = input.split(",");
        return toSeparatedOrder(menuAndQuantity);
    }

    private Map<String, Integer> toSeparatedOrder(String[] menuAndQuantity) {
        Map<String, Integer> orderMap = new LinkedHashMap<>();
        Set<String> checkDuplicate = new HashSet<>();

        for (String part : menuAndQuantity) {
            Map.Entry<String, Integer> parsedEntry = parseMenuAndQuantity(part.trim());
            if (!checkDuplicate.add(parsedEntry.getKey())) {
                throw new IllegalArgumentException(ERROR_ORDER_MENU);
            }
            orderMap.put(parsedEntry.getKey(), parsedEntry.getValue());
        }
        return orderMap;
    }

    private Map.Entry<String, Integer> parseMenuAndQuantity(String menuAndQuantity) {
        String[] parts = menuAndQuantity.trim().split("-");
        if (parts.length < 2) {
            throw new IllegalArgumentException(ERROR_ORDER_MENU);
        }
        String menu = parts[0].trim();
        int quantity;
        try {
            quantity = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_ORDER_MENU);
        }
        return new AbstractMap.SimpleEntry<>(menu, quantity);
    }


    public static void introEventPlaner() {
        System.out.print(MESSAGE_INTRO_EVENT_PLANNER);
    }
}