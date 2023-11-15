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

    private int getMinimumAmount() {
        return minimumAmount;
    }

    public static String findBadgeForAmount(int totalDiscountAmount) {
        boolean isWithinRangeAmount = MoneyManager.isWithinRangeAmount();

        if (!isWithinRangeAmount) {
            return NOTHING.badgeType;
        }
        return getBadge(totalDiscountAmount).badgeType;
    }

    private static EventBadge getBadge(int totalDiscountAmount) {
        if (totalDiscountAmount >= SANTA.getMinimumAmount()) {
            return SANTA;
        } else if (totalDiscountAmount >= TREE.getMinimumAmount()) {
            return TREE;
        } else if (totalDiscountAmount >= STAR.getMinimumAmount()) {
            return STAR;
        }
        return NOTHING;
    }

}
