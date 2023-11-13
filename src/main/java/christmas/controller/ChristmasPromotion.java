package christmas.controller;

import christmas.domain.*;
import christmas.util.Validator.VisitDateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChristmasPromotion {
    InputView inputView = new InputView();

    public void startPlaning(){
        int visitDate = getVisitDate();
        sendMenuListToView();
        OrderManager orderManager = getOrderMenu(); //여기문제
        sendIntroPreviewToView(visitDate);
        sendOrderListToView(orderManager);



        /*
        Map<Category, Integer> immutableMenu = orderManager.getOrderCategoryAndQuantity();
        for (Map.Entry<Category,Integer> entry : orderManager.getOrderCategoryAndQuantity().entrySet()) {
            System.out.println(entry.getKey()+"카테고리 출력"+entry.getValue());
        }

         */


    }


    private int getVisitDate(){
        try {
            int date = inputView.getVisitData();
            VisitDateValidator.visitDateValidator(date);
            return date;
        } catch (IllegalArgumentException e){
            OutputView.printErrorMessage(e.getMessage());
            return getVisitDate();
        }
    }

    private OrderManager getOrderMenu(){
        try {
            return new OrderManager(inputView.getOrderMenu());
        }catch (IllegalArgumentException e){
            OutputView.printErrorMessage(e.getMessage());
            return getOrderMenu();
        }
    }
    private void sendIntroPreviewToView(int visitDate){
        OutputView.printIntroPreviewToView(visitDate);
    }
    private void sendMenuListToView(){
        for (Map.Entry<Category, List<MenuItem>> menu : Menu.getMenu().entrySet()) {
            OutputView.printMenuList(menu.getKey(),menu.getValue());
        }
    }
    private void sendOrderListToView(OrderManager orderManager){
        Map<String,Integer> orderMenuAndQuantity = orderManager.getOrderMenuAndQuantity();
        OutputView.printOrderMenu(orderMenuAndQuantity);
    }




}
