package christmas.view;

import christmas.domain.Category;
import christmas.domain.DiscountType;
import christmas.domain.MenuItem;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String MESSAGE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String NOTICE_INTRO_PREVIEW = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.\n";
    private static final String NOTICE_INTR_PREVIEW = "음료만 주문 시, 주문할 수 없습니다.\n";
    private static final String MESSAGE_D_DAY_DISCOUNT = "크리스마스 디데이 할인: -%s\n";
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

    public static void printAmountBeforeDiscount(int amount){
        System.out.println("\n<할인 전 총주문 금액>");
        String amountFormatted = String.format("%,d", amount);
        String MSG = String.format(MESSAGE_PRICE,amountFormatted);
        System.out.println(MSG);
    }

    public static void printComplimentaryMenu(Map<String, Integer> complimentaryMenu){
        System.out.println("<증정 메뉴>");
        for(Map.Entry<String,Integer> entry : complimentaryMenu.entrySet()){
            String MSG = String.format(MESSAGE_ORDER_MENU_LIST, entry.getKey(), entry.getValue());
            isPrintNothingOrMsg(entry.getValue(),MSG);
        }
    }
    public static void printDiscountDetails(Map<DiscountType,Integer> details,boolean nothing){
        System.out.println("\n<혜택 내역>");
        if(nothing){
            System.out.print(MESSAGE_NOTHING);
        }
        if(!nothing){
            hasDiscountDetails(details);
        }
    }
    private static void hasDiscountDetails(Map<DiscountType,Integer> details){
        for(Map.Entry<DiscountType,Integer> entry : details.entrySet()){
            if (entry.getValue() > 0) { //0원인건 출력하지 않음
                String amountFormatted = String.format("%,d", entry.getValue());
                String MSG = entry.getKey().getDescription() + ": ";
                MSG += String.format(MESSAGE_DISCOUNT_PRICE, amountFormatted);
                System.out.print(MSG);
            }
        }
    }

    public static void printTotalDiscountAmount(int totalDiscountAmount){
        System.out.println("\n<총혜택 금액>");

        String MSG = "";
        String amountFormatted = String.format("%,d", totalDiscountAmount);
        if(totalDiscountAmount<=0){
            MSG = String.format(MESSAGE_PRICE, amountFormatted);
        }else if(totalDiscountAmount>0){
            MSG = String.format(MESSAGE_DISCOUNT_PRICE, amountFormatted);
        }
        System.out.print(MSG);
    }
    public static void printDiscountedAmount(int discountedAmount){
        System.out.println("\n<할인 후 예상 결제 금액>");

        String amountFormatted = String.format("%,d", discountedAmount);
        String MSG = String.format(MESSAGE_PRICE, amountFormatted);
        System.out.print(MSG);

    }










    private static void isPrintNothingOrMsg(int quantity, String msg){
        if(quantity<=NON_QUANTITY){
            System.out.print(MESSAGE_NOTHING);
        }else if(quantity>NON_QUANTITY){
            System.out.print(msg);
        }
    }
}
