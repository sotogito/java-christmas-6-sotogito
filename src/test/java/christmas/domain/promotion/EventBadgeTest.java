package christmas.domain.promotion;

import christmas.domain.manager.MoneyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventBadgeTest {

    @BeforeEach
    void setUp(){
        int orderAmount = 12000; //만이천원
        MoneyManager moneyManager = new MoneyManager(orderAmount);
    }

    @Test
    @DisplayName("배지를 받지 못함")
    void findBadgeForAmount_없음(){
        int totalDiscountAmount = 2000; //이천원

        String expect = "없음";
        String result = EventBadge.findBadgeForAmount(totalDiscountAmount);

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("총 할인 금액이 5000원 이상일시, 별")
    void findBadgeForAmount_별(){
        int totalDiscountAmount = 5000; //오천원

        String expect = "별";
        String result = EventBadge.findBadgeForAmount(totalDiscountAmount);

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("총 할인 금액이 10,000원 이상일시, 트리")
    void findBadgeForAmount_트리(){
        int totalDiscountAmount = 10000; //만원

        String expect = "트리";
        String result = EventBadge.findBadgeForAmount(totalDiscountAmount);

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("총 할인 금액이 20,000원 이상일시, 산타")
    void findBadgeForAmount_산타(){
        int totalDiscountAmount = 20000; //이만원

        String expect = "산타";
        String result = EventBadge.findBadgeForAmount(totalDiscountAmount);

        assertThat(result).isEqualTo(expect);
    }

}