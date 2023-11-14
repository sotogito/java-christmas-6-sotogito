package christmas.view;

import christmas.domain.restaurant.Category;
import christmas.domain.promotion.policy.DiscountType;
import christmas.domain.restaurant.MenuItem;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NOTICE_MIN_ORDER_AMOUNT = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.\n";
    private static final String NOTICE_CANT_ONLY_BEVERAGE = "음료만 주문 시, 주문할 수 없습니다.\n";
    private static final String NOTICE_MAX_ORDER_QUANTITY = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n" +
            "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)\n";

    private static final String MESSAGE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String MESSAGE_ORDER_MENU_LIST = "%s %d개\n";
    private static final String MESSAGE_PRICE = "%s원\n";
    private static final String MESSAGE_DISCOUNT_PRICE = "-%s원\n";
    private static final String MESSAGE_NOTHING = "없음\n";
    private static final int NON_QUANTITY = 0;

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void printIntroPreviewToView(int visitDate) {
        String MSG = String.format(MESSAGE_INTRO_PREVIEW, visitDate);
        System.out.println(MSG);
    }

    public static void printMenuList(Category category, List<MenuItem> menus) {
        String categoryHeader = "<" + category.getName() + ">";
        StringBuilder menuListBuilder = new StringBuilder();

        for (int i = 0; i < menus.size(); i++) {
            menuListBuilder.append(menus.get(i).getName())
                    .append("(")
                    .append(String.format("%,d", menus.get(i).getPrice()))
                    .append(")")
                    .append(isEndOfLine(i, menus.size()));
        }
        System.out.println(categoryHeader);
        System.out.println(menuListBuilder);
    }

    public static void printNotice() {
        System.out.print("<이벤트 주의 사항>\n");
        System.out.print("-" + NOTICE_MIN_ORDER_AMOUNT);
        System.out.print("-" + NOTICE_CANT_ONLY_BEVERAGE);
        System.out.println("-" + NOTICE_MAX_ORDER_QUANTITY);
    }

    public static void printOrderMenu(Map<String, Integer> orderMenuAndQuantity) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : orderMenuAndQuantity.entrySet()) {
            String MSG = String.format(MESSAGE_ORDER_MENU_LIST, entry.getKey(), entry.getValue());

            System.out.print(MSG);
        }
    }

    public static void printAmountBeforeDiscount(int amount) {
        System.out.println("\n<할인 전 총주문 금액>");
        String amountFormatted = String.format("%,d", amount);
        String MSG = String.format(MESSAGE_PRICE, amountFormatted);

        System.out.println(MSG);
    }

    public static void printComplimentaryMenu(Map<String, Integer> complimentaryMenu) {
        System.out.println("<증정 메뉴>");
        for (Map.Entry<String, Integer> entry : complimentaryMenu.entrySet()) {
            String MSG = String.format(MESSAGE_ORDER_MENU_LIST, entry.getKey(), entry.getValue());
            isPrintNothingOrMsg(entry.getValue(), MSG);
        }
    }

    public static void printDiscountDetails(Map<DiscountType, Integer> details, boolean nothing) {
        System.out.println("\n<혜택 내역>");
        if (nothing) {
            System.out.print(MESSAGE_NOTHING);
        }
        if (!nothing) {
            hasDiscountDetails(details);
        }
    }

    private static void hasDiscountDetails(Map<DiscountType, Integer> details) {
        for (Map.Entry<DiscountType, Integer> entry : details.entrySet()) {
            if (entry.getValue() > 0) { //0원인건 출력하지 않음
                String amountFormatted = String.format("%,d", entry.getValue());
                String MSG = entry.getKey().getDescription() + ": ";
                MSG += String.format(MESSAGE_DISCOUNT_PRICE, amountFormatted);

                System.out.print(MSG);
            }
        }
    }

    public static void printTotalDiscountAmount(int totalDiscountAmount) {
        System.out.println("\n<총혜택 금액>");
        String amountFormatted = String.format("%,d", totalDiscountAmount);
        String MSG = "";

        if (totalDiscountAmount == 0) {
            MSG = String.format(MESSAGE_PRICE, amountFormatted);
        } else if (totalDiscountAmount > 0) {
            MSG = String.format(MESSAGE_DISCOUNT_PRICE, amountFormatted);
        }
        System.out.print(MSG);
    }

    public static void printDiscountedAmount(int discountedAmount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        String amountFormatted = String.format("%,d", discountedAmount);
        String MSG = String.format(MESSAGE_PRICE, amountFormatted);

        System.out.print(MSG);
    }

    public static void printEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.print(badge);
    }

    private static String isEndOfLine(int i, int size) {
        if (i < size - 1) {
            return ", ";
        }
        return "\n";
    }

    private static void isPrintNothingOrMsg(int quantity, String msg) {
        if (quantity == NON_QUANTITY) {
            System.out.print(MESSAGE_NOTHING);
        } else if (quantity > NON_QUANTITY) {
            System.out.print(msg);
        }
    }

}