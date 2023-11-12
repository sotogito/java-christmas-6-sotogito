package christmas.view;

import christmas.domain.Category;
import christmas.domain.MenuItem;

import java.util.List;

public class OutputView {

    private static final String MESSAGE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String NOTICE_INTRO_PREVIEW = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.\n";
    private static final String NOTICE_INTR_PREVIEW = "음료만 주문 시, 주문할 수 없습니다.\n";
    private static final String NOTCE_INTRO_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    public static void printErrorMessage(String error) {
        System.out.print("[ERROR] " + error);
    }

    public static void printIntroPreviewToView(int visitDate){
        String MSG = String.format(MESSAGE_INTRO_PREVIEW, visitDate);
        System.out.println(MSG);

    }
    public static void printMenuList(Category categorys, List<MenuItem> menus){
        String category = "<"+categorys.getName()+">";
        String menuList = "";

        for(int i = 0; i<menus.size(); i++){
            menuList +=  menus.get(i).getName();
            menuList += "("+String.format("%,d",menus.get(i).getPrice())+")";
            if(i<menus.size()-1){
                menuList += ", ";
            }
        }
        System.out.println(category);
        System.out.println(menuList);

    }
}
