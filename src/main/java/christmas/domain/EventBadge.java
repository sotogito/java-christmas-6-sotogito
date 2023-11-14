package christmas.domain;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NOTHING("없음", 5000);

    private final String badgeType;
    private final int minimumAmount;

    EventBadge(String badgeType, int minimumAmount) {
        this.badgeType = badgeType;
        this.minimumAmount = minimumAmount;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public static String findBadgeForAmount(int totalDiscountAmount) {
        boolean isWithinRangeAmount = MoneyManager.isWithinRangeAmount();
        String badgeType = NOTHING.badgeType;

        if (!isWithinRangeAmount) {
            badgeType = NOTHING.badgeType;
        } else if (isWithinRangeAmount) {
            badgeType = getBadge(totalDiscountAmount).badgeType;
        }
        return badgeType;
    }

    private static EventBadge getBadge(int totalDiscountAmount) {
        EventBadge eventBadge = NOTHING;
        if (totalDiscountAmount >= SANTA.getMinimumAmount()) {
            eventBadge = SANTA;
        } else if (totalDiscountAmount >= TREE.getMinimumAmount()) {
            eventBadge = TREE;
        } else if (totalDiscountAmount >= STAR.getMinimumAmount()) {
            eventBadge = STAR;
        } else if (totalDiscountAmount < NOTHING.getMinimumAmount()) {
            eventBadge = NOTHING;
        }
        return eventBadge;
    }
}
