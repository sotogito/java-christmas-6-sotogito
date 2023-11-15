package christmas.util.validator;

import christmas.domain.restaurant.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OrderMenuValidatorTest {

    @Test
    @DisplayName("정상입력시 예외 처리 없음")
    void orderMenuValidator() {
        Map<Category,Integer> orderCategory = new HashMap<>();
        orderCategory.put(Category.APPETIZER,7);
        orderCategory.put(Category.MAIN_COURSE,8);
        //총합 : 15개

        assertDoesNotThrow(() -> OrderMenuValidator.orderMenuValidator(orderCategory));
    }

    @Test
    @DisplayName("메뉴 20개 이상일시 예외 처리")
    void isWithinRangeNum() {
        Map<Category,Integer> orderCategory = new HashMap<>();
        orderCategory.put(Category.APPETIZER,13);
        orderCategory.put(Category.MAIN_COURSE,8);
        //총합 : 21개

        assertThatThrownBy(() -> OrderMenuValidator.orderMenuValidator(orderCategory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @Test
    @DisplayName("메뉴 1개 이하실시 예외 처리")
    void isOverMinQuantity() {
        Map<Category,Integer> orderCategory = new HashMap<>();
        orderCategory.put(Category.APPETIZER,0);
        //총합 : 0개

        assertThatThrownBy(() -> OrderMenuValidator.orderMenuValidator(orderCategory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료민 시킬시 예외 처리")
    void isOnlyBeverage() {
        Map<Category,Integer> orderCategory = new HashMap<>();
        orderCategory.put(Category.BEVERAGE,5);
        //총합 : 0개

        assertThatThrownBy(() -> OrderMenuValidator.orderMenuValidator(orderCategory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문 시, 주문할 수 없습니다.");
    }

}