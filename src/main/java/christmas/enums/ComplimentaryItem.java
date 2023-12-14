package christmas.enums;

import christmas.domain.Menu;

public enum ComplimentaryItem {
    CHAMPAGNE("샴페인",1,120000),
    NOTHING("없음,",0,0);

    private final String item;
    private final int quantity;
    private final int min_amount;

    ComplimentaryItem(String item,int quantity, int amount) {
        this.item = item;
        this.quantity = quantity;
        min_amount = amount;
    }

    public static boolean isGetItem(int amount){
        return amount >= CHAMPAGNE.min_amount;
    }

    public String getItem(){
        return item;
    }
    public int getQuantity(){
        return quantity;
    }

    public int getItemPrice(){
        return Menu.findMenuPrice(Menu.findMenuItem(CHAMPAGNE.item));
    }


}
