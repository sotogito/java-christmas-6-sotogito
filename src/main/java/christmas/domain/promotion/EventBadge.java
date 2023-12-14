package christmas.domain.promotion;

import christmas.domain.manager.MoneyManager;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NOTHING("없음", 0);

    private final String badgeType;
    private final int minimumAmount;

    EventBadge(String badgeType, int minimumAmount) {
        this.badgeType = badgeType;
        this.minimumAmount = minimumAmount;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public static String getEventBadge(int totalDiscountAmount) {
        if (totalDiscountAmount >= MoneyManager.MIN_AMOUNT) {
            return getBadge(totalDiscountAmount).badgeType;
        }
        return NOTHING.getBadgeType();
    }


    public static EventBadge getBadge(int totalDiscountAmount) {
        if (totalDiscountAmount >= SANTA.minimumAmount) {
            return SANTA;
        } else if (totalDiscountAmount >= TREE.minimumAmount) {
            return TREE;
        } else if (totalDiscountAmount >= STAR.minimumAmount) {
            return STAR;
        }
        return NOTHING;
    }

}
