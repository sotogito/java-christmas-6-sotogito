package christmas.domain;

import java.util.*;

public class ComplimentaryItem {
    private static final int CAN_COMPLIMENTARY_MIN = 120000;
    private static final int COMPLIMENTARY_QUANTITY = 1;
    private static final int NOTHING = 0;
    private static MenuItem complimentaryItem;
    private static boolean isReceive;

    static {
        MenuItem menuItem = Menu.findMenuItem("샴페인");
        complimentaryItem = menuItem;
    }

    public static Map<String, Integer> getComplimentaryItem(int amount) {
        Map<String, Integer> itemNameAndQuantity = new LinkedHashMap<>();

        if (amount >= CAN_COMPLIMENTARY_MIN) {
            itemNameAndQuantity.put(complimentaryItem.getName(), COMPLIMENTARY_QUANTITY);
            isReceive = true;
        } else if((amount < CAN_COMPLIMENTARY_MIN)) {
            itemNameAndQuantity.put(complimentaryItem.getName(), NOTHING);
            isReceive = false;
        }
        return itemNameAndQuantity;
    }


    public static int getAmount(){
        int amount = NOTHING;
        String item = complimentaryItem.getName();
        if(isReceive){
            amount = Menu.findOrderMenuAndReturnPrice(item);
        }else if(!isReceive){
            amount = NOTHING;
        }
        return amount;
    }

}
