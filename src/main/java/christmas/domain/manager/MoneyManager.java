package christmas.domain.manager;

public class MoneyManager {

    public static final int MIN_AMOUNT = 10000;
    private int orderAmount;

    public MoneyManager(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getOrderAmount(){
        return orderAmount;
    }

    public boolean isAmountAboveMinimum(){
        return orderAmount >= MIN_AMOUNT;
    }

    public int totalAmountAfterDiscount(int discount){
        return orderAmount-discount;
    }

}
