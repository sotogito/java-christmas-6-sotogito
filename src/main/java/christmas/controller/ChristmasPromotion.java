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
        ScheduleManager scheduleManager = getVisitDate();
        sendMenuListToView();
        OrderManager orderManager = getOrderMenu(); //여기문제
        MoneyManager moneyManager = getAmountBeforeDiscount(orderManager);
        EventPlanner eventPlanner = runEventPlanner(scheduleManager,orderManager);


        System.out.println(eventPlanner.getWeekdayDiscount()+"평일할인");
        System.out.println(eventPlanner.getWeekendDiscount()+"주말할인");
        System.out.println(eventPlanner.getSpecialDiscount()+"스페셩할인");
        eventPlanner.getComplimentaryMenu(moneyManager.getTotalOrderAmount());
        System.out.println(eventPlanner.getAmountToDiscount());
        System.out.println(eventPlanner.getTotalDiscountAmount());

        sendComplimentaryMenuToView();



        sendIntroPreviewToView(scheduleManager);
        sendOrderListToView(orderManager);
        sendAmountBeforeDiscountToView(moneyManager);




        /*
        Map<Category, Integer> immutableMenu = orderManager.getOrderCategoryAndQuantity();
        for (Map.Entry<Category,Integer> entry : orderManager.getOrderCategoryAndQuantity().entrySet()) {
            System.out.println(entry.getKey()+"카테고리 출력"+entry.getValue());
        }

         */


    }


    private ScheduleManager getVisitDate(){
        try {
            return new ScheduleManager(inputView.getVisitData());
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
    private MoneyManager getAmountBeforeDiscount(OrderManager orderManager){
        int amountBeforeDiscount =orderManager.getTotalOrderAmount();
        return new MoneyManager(amountBeforeDiscount);

    }
    private EventPlanner runEventPlanner(ScheduleManager scheduleManager,OrderManager orderManager){
        DailyDiscountItem dayOfWeek = scheduleManager.getDayOfWeek();
        Map<Category, Integer> orderCategory = orderManager.getOrderCategoryAndQuantity();
        return new EventPlanner(dayOfWeek,orderCategory);

    }





    private void sendIntroPreviewToView(ScheduleManager scheduleManager){
        OutputView.printIntroPreviewToView(scheduleManager.getVisitDate());
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
    private void sendAmountBeforeDiscountToView(MoneyManager moneyManager){
        OutputView.printAmountBeforeDiscount(moneyManager.getTotalOrderAmount());

    }
    private void sendComplimentaryMenuToView(){

    }




}
