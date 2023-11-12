package christmas.util.Validator;

import christmas.domain.Menu;

public class OrderMenuValidator {
    private static final int MIN = 1;
    private static final int MAX = 20;
    private static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";
    private static int totalQuantity = 0;


    public static void orderMenuValidator(String menu, int quantity){
        if(!isWithinRangeNum(quantity)){
            totalQuantity=0;
            throw new IllegalArgumentException(ERROR_ORDER_MENU+"벨리데이터");
        }

    }



    private static boolean isWithinRangeNum(int quantity){
        totalQuantity += quantity;
        return totalQuantity>= MIN && totalQuantity <=MAX;
    }


}
