package christmas.util.validator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyValidatorTest {

    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("총 주문이 10.000원 이하인 경우 출력")
    void isWithinRangeAmount() {
        int minAmount = 10000;
        int sample = 1000;

        String minAmountFormatted = String.format("%,d", minAmount);
        String expect = String.format("총주문 금액 %s원 이상부터 이벤트가 적용됩니다.\n",minAmountFormatted);
        MoneyValidator.isWithinRangeAmount(sample);

        assertEquals(expect,outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("총 주문 금액이 최소 금액 이상일 때 아무것도 출력하지 않음")
    void doesNotPrintWhenAmountIsAboveMin() {
        int testAmount = 10000;

        MoneyValidator.isWithinRangeAmount(testAmount);

        assertTrue(outputStreamCaptor.toString().isEmpty());
    }

}