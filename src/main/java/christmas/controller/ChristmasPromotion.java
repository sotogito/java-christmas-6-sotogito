package christmas.controller;

import christmas.domain.MenuItem;
import christmas.domain.interfaces.EventPlanner;
import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.OrderMenuManager;
import christmas.domain.manager.ScheduleManager;
import christmas.enums.ComplimentaryItem;
import christmas.enums.DiscountType;
import christmas.enums.EventBadge;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasPromotion {
    public void run(){
        InputView.startPrintEventPlanner();
        InputView.startInputVisitDate();
        ScheduleManager scheduleManager = createScheduleManager();
            //날짜
        InputView.startInputOrderMenu();
        OrderMenuManager orderMenuManager = createOrderMenuManager();
            //메뉴
        MoneyManager moneyManager = createMoneyManager(orderMenuManager);
            //돈
        sendVisitDate(scheduleManager);
        sendOrderMenuList(orderMenuManager);

        EventPlanner eventPlanner = createEventPlanner(moneyManager ,scheduleManager,orderMenuManager);

        sendTotalOrderAmountBeforeDiscount(moneyManager);
        sendComplimentaryItem(moneyManager);
        sendDiscountList(eventPlanner,moneyManager);
        sendTotalDiscountAmount(eventPlanner);
        sendEstimatedPaymentAfterDiscount(moneyManager,eventPlanner);
        sendEventBadge(moneyManager,eventPlanner);


    }

    private void sendEventBadge(MoneyManager moneyManager,EventPlanner eventPlanner){
        OutputView.startPrintEventBadge();

        if(moneyManager.isAmountAboveMinimum()){
            EventBadge eventBadge = EventBadge.getBadge(eventPlanner.getDiscountAmount());
            OutputView.printEventBadge(eventBadge.getBadgeType());
        } else if (!moneyManager.isAmountAboveMinimum()) {
            OutputView.printNothing();
        }
    }

    private void sendEstimatedPaymentAfterDiscount(MoneyManager moneyManager,EventPlanner eventPlanner){
        OutputView.startPrintEstimatedPaymentAfterDiscount();

        int result = moneyManager.totalAmountAfterDiscount(eventPlanner.getDiscountAmountForCalculate());
        OutputView.printEstimatedPaymentAfterDiscount(result);

    }

    private void sendTotalDiscountAmount(EventPlanner eventPlanner) {
        OutputView.startTotalDiscountAmount();
        OutputView.printTotalDiscountAmount(eventPlanner.getDiscountAmount());

    }

    private void sendComplimentaryItem(MoneyManager moneyManager){
        OutputView.startPrintComplimentaryItem();
        if(ComplimentaryItem.isGetItem(moneyManager.getOrderAmount())){
            OutputView.printComplimentaryItem(
                    ComplimentaryItem.CHAMPAGNE.getItem(), ComplimentaryItem.CHAMPAGNE.getQuantity());
        }else if(!ComplimentaryItem.isGetItem(moneyManager.getOrderAmount())){
            OutputView.printNothing();
        }
    }

    private void sendTotalOrderAmountBeforeDiscount(MoneyManager moneyManager){
        OutputView.startPrintTotalOrderAmountBeforeDiscount();
        OutputView.printTotalOrderAmountBeforeDiscount(moneyManager.getOrderAmount());
    }

    private void sendDiscountList(EventPlanner eventPlanner, MoneyManager moneyManager){
        OutputView.startPrintDiscountList();

        if(moneyManager.isAmountAboveMinimum()){
            for (Map.Entry<DiscountType,Integer> entry : eventPlanner.getDiscountList().entrySet()) {
                OutputView.printDiscountList(entry.getKey().getName(),entry.getValue());
            }
        } else if(!moneyManager.isAmountAboveMinimum()){
            OutputView.printNothing();
        }
    }

    private EventPlanner createEventPlanner(MoneyManager moneyManager,ScheduleManager scheduleManager, OrderMenuManager orderMenuManager){
        return new EventPlanner(moneyManager,scheduleManager,orderMenuManager);
    }

    private void sendOrderMenuList(OrderMenuManager orderMenuManager){
        OutputView.startPrintOrderMenu();

        for (Map.Entry<MenuItem,Integer> entry : orderMenuManager.getOrderMenu().entrySet()) {
            String menuName = entry.getKey().getName();
            int count = entry.getValue();
            OutputView.printOrderMenu(menuName,count);
        }
    }

    private MoneyManager createMoneyManager(OrderMenuManager orderMenuManager){
        return new MoneyManager(orderMenuManager.getOrderTotalAmount());
    }

    private void sendVisitDate(ScheduleManager scheduleManager){
        OutputView.printVisitDate(scheduleManager.getVisitDate());
    }

    private OrderMenuManager createOrderMenuManager(){
        try{
            return new OrderMenuManager(InputView.inputOrderMenu());
        } catch (IllegalArgumentException e){
            OutputView.printErrorMessage(e.getMessage());
            return createOrderMenuManager();
        }

    }

    private ScheduleManager createScheduleManager(){
        try{
            return new ScheduleManager(InputView.inputVisitDate());
        }catch (IllegalArgumentException e){
            OutputView.printErrorMessage(e.getMessage());
            return createScheduleManager();
        }
    }
}
