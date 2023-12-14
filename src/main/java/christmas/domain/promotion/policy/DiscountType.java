package christmas.domain.promotion.policy;

public enum DiscountType {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    COMPLIMENTARY_ITEM("증정 이벤트"),
    NOTHING("없음");

    private final String name;

    DiscountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
