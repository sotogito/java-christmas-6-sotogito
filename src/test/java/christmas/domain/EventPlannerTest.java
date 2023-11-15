package christmas.domain;

import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.ScheduleManager;
import christmas.domain.promotion.ComplimentaryItem;
import christmas.domain.promotion.policy.DailyDiscountItem;
import christmas.domain.restaurant.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventPlannerTest {

    MoneyManager moneyManager;
    ScheduleManager scheduleManager;
    EventPlanner eventPlanner;

    @BeforeEach
    void setup() {
        int orderAmount = 90000;
        moneyManager = new MoneyManager(orderAmount);
        ComplimentaryItem.getComplimentaryItem(orderAmount);

        int visitDate = 25;
        scheduleManager = new ScheduleManager(visitDate);

        DailyDiscountItem dayOfWeek = DailyDiscountItem.CHRISTMAS;
        Map<Category, Integer> orderCategory = new EnumMap<Category, Integer>(Category.class);
        orderCategory.put(Category.DESSERT, 2); //초코케이크
        orderCategory.put(Category.BEVERAGE, 1); //레드와인
        eventPlanner = new EventPlanner(dayOfWeek, orderCategory);
    }

    /*
        크리스마스에 적용되는 예상 할인
        1. 크리스마스 디데이 할인 : -3,400원
        2. 평일 할인 : -4,046원
        3. 특별 할인 : -1,000원

        총 혜택 금액 : 8,446원
         */

    @Test
    @DisplayName("크리스마스 디데이 할인")
    void getDDayDiscount() {
        int date = scheduleManager.calculateDDay(); //24

        int expect = 3400;
        int result = eventPlanner.getDDayDiscount(date);

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("평일 할인")
    void getWeekdayDiscount() {
        int expect = 4046;
        int result = eventPlanner.getWeekdayDiscount();

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("주말 할인")
    void getWeekendDiscount() {
        int expect = 0;
        int result = eventPlanner.getWeekendDiscount();

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("특별 할인")
    void getSpecialDiscount() {
        int expect = 1000;
        int result = eventPlanner.getSpecialDiscount();

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("총 혜택 금액 +샴페인 금액")
    void getTotalDiscountAmount() {
        getDDayDiscount();
        getWeekdayDiscount();
        getWeekendDiscount();
        getSpecialDiscount();

        int expect = 8446;
        int result = eventPlanner.getTotalDiscountAmount();

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("총 혜택 금액 +샴페인 금액")
    void getAmountToDiscount() {
        getDDayDiscount();
        getWeekdayDiscount();
        getWeekendDiscount();
        getSpecialDiscount();

        int expect = 8446;
        int result = eventPlanner.getAmountToDiscount();

        assertEquals(expect, result);
    }

}