package christmas.util;

public enum EventPlannerPrintList {
    ORDER_MENU("주문 메뉴"),
    ORDER_AMOUNT_BEFORE_DISCOUNT("할인 전 총주문 금액"),
    COMPLIMENTARY_ITEM("증정 메뉴"),
    DISCOUNT_LIST("혜택 내역"),
    TOTAL_DISCOUNT_AMOUNT("총혜택 금액"),
    ESTIMATED_PAYMENT_AFTER_DISCOUNT("할인 후 예상 결제 금액"),
    EVENT_BADGE("12월 이벤트 배지");
    private final String name;

    EventPlannerPrintList(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
