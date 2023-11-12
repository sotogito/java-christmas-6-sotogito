package christmas.controller;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.MenuItem;

import java.util.List;
import java.util.Map;

public class ChristmasPromotion {

    public void startPlaning(){
        Map<Category, List<MenuItem>> immutableMenu = Menu.getMenu();
        for (Map.Entry<Category, List<MenuItem>> entry : immutableMenu.entrySet()) {
            System.out.println(entry.getKey()); // 카테고리 출력
            List<MenuItem> items = entry.getValue();
            for (MenuItem item : items) {
                System.out.println(item.getName() + " - " + item.getPrice() + "원"); // 메뉴 이름과 가격 출력
            }
        }

    }

    private void getOrderMenu(){

    }

    private void getComplimentaryItem(){

    }

    private void getTotalAmountBeforeDiscount(){

    }
    private void getBenefitDetails(){

    }

    private void getTotalDiscountAmount(){

    }

    private void getEstimatedAmountAfterDiscount(){

    }

    private void getEventBadge(){

    }


}
