package christmas.domain;

public class MoneyManager {
    int totalOrderAmount = 0;

    public MoneyManager(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public int getTotalOrderAmount(){
        return totalOrderAmount;
    }

    public int getTotalAmountAfterDiscount(int discount){
        return totalOrderAmount - discount;
    }


}
