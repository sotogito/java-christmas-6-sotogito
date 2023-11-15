package christmas.domain.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyManagerTest {

    @Test
    @DisplayName("구입 금액이 10,000보다 작을 때")
    void isWithinRangeAmount_작을_때() {
        int amount = 123;
        MoneyManager moneyManager = new MoneyManager(amount);
        boolean result = MoneyManager.isWithinRangeAmount();
        boolean expect = false;

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("구입 금액이 10,000보다 클 때")
    void isWithinRangeAmount_클_때() {
        int amount = 100000;
        MoneyManager moneyManager = new MoneyManager(amount);
        boolean result = MoneyManager.isWithinRangeAmount();
        boolean expect = true;

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산")
    void getDiscountedAmount() {
        int amount = 12345;
        int discount = 2345;
        MoneyManager moneyManager = new MoneyManager(amount);
        int result = moneyManager.getDiscountedAmount(discount);
        int expect = 10000;

        assertThat(expect).isEqualTo(result);
    }

}