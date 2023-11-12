package christmas.domain;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");


    private String name;

    Category(String name) {
        this.name = name;
    }
}
