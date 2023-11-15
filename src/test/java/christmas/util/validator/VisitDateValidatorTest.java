package christmas.util.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class VisitDateValidatorTest {

    @Test
    @DisplayName("정상입력시 예외 처리 없음")
    void orderMenuValidator() {
        int date = 25;

        assertDoesNotThrow(() -> VisitDateValidator.visitDateValidator(date));
    }

    @Test
    @DisplayName("메뉴 20개 이상일시 예외 처리")
    void isWithinRangeNum() {
        int date = 32;

        assertThatThrownBy(() -> VisitDateValidator.visitDateValidator(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}