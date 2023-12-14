package christmas.view;

public class OutputView {

    public static final String HEADING_TEMPLATE = "\n<%s>\n";
    public static final String ORDER_MENU_TEMPLATE = "%s %d개\n";
    public static final String PRICE = "%,d원\n";
    public static final String DISCOUNT_PRICE = "-%,d원\n";
    public static final String NOTHING = "없음\n";
    public static final String ERROR_ORDER_MENU = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void startPrintOrderMenu(){
        System.out.printf(HEADING_TEMPLATE,"주문 메뉴");
    }
    public static void startPrintTotalOrderAmountBeforeDiscount(){
        System.out.printf(HEADING_TEMPLATE,"할인 전 총주문 금액");
    }

    public static void startPrintComplimentaryItem(){
        System.out.printf(HEADING_TEMPLATE,"증정 메뉴");
    }

    public static void startPrintDiscountList(){
        System.out.printf(HEADING_TEMPLATE,"혜택 내역");
    }

    public static void startTotalDiscountAmount(){
        System.out.printf(HEADING_TEMPLATE,"총혜택 금액");
    }

    public static void startPrintEstimatedPaymentAfterDiscount(){
        System.out.printf(HEADING_TEMPLATE,"할인 후 예상 결제 금액");
    }

    public static void startPrintEventBadge(){
        System.out.printf(HEADING_TEMPLATE,"12월 이벤트 배지");
    }

    public static void printNothing(){
        System.out.print(NOTHING);
    }

    public static void printVisitDate(int visitDate){
        String result = String.format(ERROR_ORDER_MENU,visitDate);
        System.out.print(result);
    }
    public static void printOrderMenu(String menuName, int count){
        String result = String.format(ORDER_MENU_TEMPLATE,menuName,count);
        System.out.print(result);
    }

    public static void printTotalOrderAmountBeforeDiscount(int amount){
        System.out.printf(PRICE,amount);
    }

    public static void printComplimentaryItem(String item, int quantity){
        System.out.printf(ORDER_MENU_TEMPLATE,item,quantity);
    }

    public static void printDiscountList(String discountType, int amount){
        String discountAmount = String.format(DISCOUNT_PRICE,amount);
        System.out.printf("%s: "+discountAmount, discountType);
    }

    public static void printTotalDiscountAmount(int amount){
        if(amount == 0){
            System.out.printf(PRICE,amount);
            return;
        }
        System.out.printf(DISCOUNT_PRICE,amount);
    }

    public static void printEstimatedPaymentAfterDiscount(int amount){
        System.out.printf(PRICE,amount);
    }

    public static void printEventBadge(String badge){
        System.out.println(badge);
    }

}
