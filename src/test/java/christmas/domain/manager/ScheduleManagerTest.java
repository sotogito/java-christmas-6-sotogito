package christmas.domain.manager;

import christmas.domain.promotion.policy.DailyDiscountItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleManagerTest {


    @Test
    @DisplayName("크리스마스 이외 반환")
    void getDayOfWeek_크리스마스_이외(){
        int date = 5; // 화요일
        ScheduleManager scheduleManager = new ScheduleManager(date);

        DailyDiscountItem expect = DailyDiscountItem.TUES;
        DailyDiscountItem result = scheduleManager.getDayOfWeek();

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("크리스마스 반환")
    void getDayOfWeek_크리스마스(){
        int date = 25; //크리스마스
        ScheduleManager scheduleManager = new ScheduleManager(date);

        DailyDiscountItem expect = DailyDiscountItem.CHRISTMAS;
        DailyDiscountItem result = scheduleManager.getDayOfWeek();

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("디데이 할인에 필요한 정수 반환")
    void calculateDDay(){
        int date = 25; //크리스마스
        ScheduleManager scheduleManager = new ScheduleManager(date);

        int expect = 24;
        int result = scheduleManager.calculateDDay();

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("디데이 할인 범위 안")
    void isWithinRangeAmount_범위_안() {
        int date = 20;
        ScheduleManager scheduleManager = new ScheduleManager(date);

        boolean expect = true;
        boolean result = ScheduleManager.isWithinRangeDate();

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("디데이 할인 범위 밖")
    void isWithinRangeAmount_범위_밖() {
        int date = 30;
        ScheduleManager scheduleManager = new ScheduleManager(date);

        boolean expect = false;
        boolean result = ScheduleManager.isWithinRangeDate();

        assertThat(expect).isEqualTo(result);
    }

}