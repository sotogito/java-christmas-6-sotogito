package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComplimentaryItem {
    private static final int CAN_COMPLIMENTARY_MIN = 120000;
    private static final int COMPLIMENTARY_QUANTITY = 1;
    private static final int NOTHING = 0;
    private static final MenuItem complimentaryItem;
    private static boolean isReceive;

    static {
        complimentaryItem= Menu.findMenuItem("샴페인");
    }

    public static Map<String, Integer> getComplimentaryItem(int amount) {
        Map<String, Integer> itemNameAndQuantity = new LinkedHashMap<>();

        if(amount < CAN_COMPLIMENTARY_MIN){
            itemNameAndQuantity.put(complimentaryItem.getName(), NOTHING);
            isReceive = false;
            return itemNameAndQuantity;
        }
        itemNameAndQuantity.put(complimentaryItem.getName(), COMPLIMENTARY_QUANTITY);
        isReceive = true;
        return itemNameAndQuantity;
    }

    public static int getAmount() {
        String item = complimentaryItem.getName();

        if(!isReceive){
            return NOTHING;
        }
        return Menu.findOrderMenuAndReturnPrice(item);
    }

}
