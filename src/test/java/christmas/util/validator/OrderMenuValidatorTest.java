package christmas.util.validator;

import christmas.domain.restaurant.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderMenuValidatorTest {

    @Test
    @DisplayName("메뉴 20개 이상일 시 오류처리")
    void orderMenuValidator() {
        int quantity = 21;
        Map<Category,Integer> orderCategory = new HashMap<>();
        orderCategory.put(Category.APPETIZER,13);
        orderCategory.put(Category.MAIN_COURSE,8);

        //OrderMenuValidator.orderMenuValidator(quantity,orderCategory);

    }
}