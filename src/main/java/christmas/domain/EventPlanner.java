package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class EventPlanner {
    int visitDate;
    private Map<Category, Integer> orderCategory = new EnumMap<Category, Integer>(Category.class);

    public EventPlanner(int visitDate, Map<Category, Integer> orderCategory) {
        Category category = null;
        if (visitDate == 0) {
            category = Category.DESSERT;
        }
        if (visitDate == 1) {
            category = Category.MAIN_COURSE;
        }

        getDiscountAmount(category, orderCategory);
    }

    private int getDiscountAmount(Category category, Map<Category, Integer> orderCategory) {
        int discountAmount = 0;
        for (Map.Entry<Category, Integer> entry : orderCategory.entrySet()) {
            if (entry.getKey() == category) {
                discountAmount += 2023 * entry.getValue();
            }
        }
        return discountAmount;

    }
}
