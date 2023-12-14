package christmas.enums;

import christmas.domain.Menu;

import java.util.Arrays;
import java.util.Comparator;

public enum ComplimentaryItem {
    CHAMPAGNE("샴페인", 1, 120000),
    NOTHING("없음,", 0, 0);

    private final String item;
    private final int quantity;
    private final int min_amount;

    ComplimentaryItem(String item, int quantity, int amount) {
        this.item = item;
        this.quantity = quantity;
        min_amount = amount;
    }

    public String getItem() {
        return item;
    }

    public int getPrice(){
        return min_amount;
    }

    public static boolean isGetItem(int amount) {
        return amount >= CHAMPAGNE.min_amount;
    }

    public static ComplimentaryItem findItem(int amount) {
        return Arrays.stream(ComplimentaryItem.values())
                .filter(item -> amount >= item.min_amount) // 예산 이상의 항목 필터링
                .min(Comparator.comparingInt(item -> amount - item.min_amount)) // 가장 차이가 적은 항목 찾기
                .orElse(ComplimentaryItem.NOTHING); // 해당하는 항목이 없으면 NOTHING 반환
    }

    public static int findQuantity(String itemName) {
        return Arrays.stream(ComplimentaryItem.values())
                .filter(name -> name.item.equals(itemName))
                .map(name -> name.quantity)
                .findFirst()
                .orElse(0);
    }

    public static int findPrice(String itemName) {
        return Arrays.stream(ComplimentaryItem.values())
                .filter(name -> name.item.equals(itemName))
                .map(name -> name.min_amount)
                .findFirst()
                .orElse(0);
    }

}
