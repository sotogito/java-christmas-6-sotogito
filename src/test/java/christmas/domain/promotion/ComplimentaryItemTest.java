package christmas.domain.promotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ComplimentaryItemTest {

    @Test
    @DisplayName("총 구매 금액이 12만원이 넘을 때, 증정품")
    void getComplimentaryItem_12만원_넘을_때(){
        int amount = 150000; //십오만원

        Map<String, Integer> expect = new HashMap<>();
        expect.put("샴페인",1);

        Map<String, Integer> result = ComplimentaryItem.getComplimentaryItem(amount);

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("총 구매 금액이 12만원이 넘지 못할 때, 증정품")
    void getComplimentaryItem_12만원_넘지_못할_때(){
        int amount = 10000; //만원

        Map<String, Integer> expect = new HashMap<>();
        expect.put("샴페인",0);

        Map<String, Integer> result = ComplimentaryItem.getComplimentaryItem(amount);

        assertThat(result).isEqualTo(expect);
    }

}