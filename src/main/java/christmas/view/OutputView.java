package christmas.view;

import christmas.domain.Category;
import christmas.domain.MenuItem;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String MESSAGE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String NOTICE_INTRO_PREVIEW = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.\n";
    private static final String NOTICE_INTR_PREVIEW = "음료만 주문 시, 주문할 수 없습니다.\n";
    private static final String NOTCE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String MESSAGE_ORDER_MENU_LIST = "%s %d개\n";

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void printIntroPreviewToView(int visitDate) {
        String MSG = String.format(MESSAGE_INTRO_PREVIEW, visitDate);
        System.out.println(MSG);

    }

    public static void printMenuList(Category categorys, List<MenuItem> menus) {
        String category = "<" + categorys.getName() + ">";
        String menuList = "";

        for (int i = 0; i < menus.size(); i++) {
            menuList += menus.get(i).getName();
            menuList += "(" + String.format("%,d", menus.get(i).getPrice()) + ")";
            menuList += isEndOfLine(i, menus.size());
        }
        System.out.println(category);
        System.out.println(menuList);
    }

    private static String isEndOfLine(int i, int size) {
        String add = "";
        if (i < size - 1) {
            add += ", ";
        }
        if (i == size - 1) {
            add += "\n";
        }
        return add;
    }

    public static void printOrderMenu(Map<String, Integer> orderMenuAndQuantity) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : orderMenuAndQuantity.entrySet()) {
            String MSG = String.format(MESSAGE_ORDER_MENU_LIST, entry.getKey(), entry.getValue());
            System.out.print(MSG);
        }

    }
}
