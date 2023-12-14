package christmas.view;

public class OutputView {

    public static final String ERROR_ORDER_MENU = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void printVisitDate(int visitDate){
        String result = String.format(ERROR_ORDER_MENU,visitDate);
        System.out.print(result);
    }
}
