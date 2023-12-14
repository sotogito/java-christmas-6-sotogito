package christmas.view;

public class OutputView {

    public static final String HEADING_TEMPLATE = "\n<%s>\n";
    public static final String ORDER_MENU_TEMPLATE = "%s %d개\n";
    public static final String ERROR_ORDER_MENU = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void startPrintOrderMenu(){
        System.out.printf(HEADING_TEMPLATE,"주문 메뉴");
    }

    public static void printVisitDate(int visitDate){
        String result = String.format(ERROR_ORDER_MENU,visitDate);
        System.out.print(result);
    }
    public static void printOrderMenu(String menuName, int count){
        String result = String.format(ORDER_MENU_TEMPLATE,menuName,count);
        System.out.print(result);
    }
}
