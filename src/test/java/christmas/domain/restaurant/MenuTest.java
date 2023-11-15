package christmas.domain.restaurant;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuTest {

    @Test
    @DisplayName("메뉴판에 없는 메뉴")
    void findMenuItem_에러() {
        String orderMenuName = "참이슬";

        assertThatThrownBy(() -> Menu.findMenuItem(orderMenuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("메뉴판에 존재하는 메뉴")
    void orderMenuValidator_존재() {
        String orderMenuName = "레드와인";

        assertDoesNotThrow(() -> Menu.findMenuItem(orderMenuName));
    }

    @Test
    @DisplayName("메뉴 가격 찾기")
    void findOrderMenuAndReturnPrice() {
        String orderMenuName = "아이스크림";

        int expect = 5000;
        int result = Menu.findOrderMenuAndReturnPrice(orderMenuName);

        assertEquals(expect, result);
    }

}