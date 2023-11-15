package christmas.domain.manager;

import christmas.domain.restaurant.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {
    OrderManager orderManager;

    @BeforeEach
    void setUp(){
        Map<String, Integer> orderMenus = new LinkedHashMap<>();
        orderMenus.put("바비큐립",2);
        orderMenus.put("레드와인",1);

        orderManager = new OrderManager(orderMenus);
    }

    @Test
    @DisplayName("메뉴-슈량, 카테고리-수량으로 변경 확인")
    void getOrderCategoryAndQuantity(){
        Map<Category, Integer> expect = new EnumMap<Category, Integer>(Category.class);
        expect.put(Category.MAIN_COURSE,2);
        expect.put(Category.BEVERAGE,1);

        Map<Category, Integer> result = orderManager.getOrderCategoryAndQuantity();

        assertEquals(expect, result);
    }

}