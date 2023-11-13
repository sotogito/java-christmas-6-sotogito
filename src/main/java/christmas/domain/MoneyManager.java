package christmas.domain;

public class MoneyManager {
    private static final int MIN_AMOUNT = 10000;
    private static int totalOrderAmount = 0;

    public MoneyManager(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public int getTotalOrderAmount(){
        return totalOrderAmount;
    }

    public static boolean isWithinRangeAmount(){
        return totalOrderAmount>=10000;
    }

    public int getTotalAmountAfterDiscount(int discount){
        return totalOrderAmount - discount;
    }


}
