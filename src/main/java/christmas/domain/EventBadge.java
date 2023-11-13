package christmas.domain;

public enum EventBadge {
    STAR("별",5000),
    TREE("트리",10000),
    SANTA("산타",20000);

    private final String badgeType;
    private final int minimumAmount;

    EventBadge(String badgeType,int minimumAmount) {
        this.badgeType = badgeType;
        this.minimumAmount = minimumAmount;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    /////여기다가 바로 배치값 알아오게
    public static EventBadge getBadgeForAmount(int totalDiscountAmount) {
        if (totalDiscountAmount >= SANTA.getMinimumAmount()) {
            return SANTA;
        } else if (totalDiscountAmount >= TREE.getMinimumAmount()) {
            return TREE;
        } else if (totalDiscountAmount >= STAR.getMinimumAmount()) {
            return STAR;
        } else {
            return null;
        }
    }
}
