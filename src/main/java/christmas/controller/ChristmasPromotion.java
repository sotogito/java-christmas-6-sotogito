package christmas.controller;

import christmas.domain.MenuItem;
import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.OrderMenuManager;
import christmas.domain.manager.ScheduleManager;
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
