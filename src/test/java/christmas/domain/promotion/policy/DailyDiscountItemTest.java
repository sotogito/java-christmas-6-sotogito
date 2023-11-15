package christmas.domain.promotion.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class DailyDiscountItemTest {

    @Test
    @DisplayName("요일 받고 해당 Enum 반환")
    void getDayOfWeek(){
        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;

        DailyDiscountItem expect = DailyDiscountItem.SAT;
        DailyDiscountItem result = DailyDiscountItem.getDayOfWeek(dayOfWeek);

        assertThat(result).isEqualTo(expect);
    }

}