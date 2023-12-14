package christmas.controller;

import christmas.domain.*;
import christmas.domain.Menu;
import christmas.domain.MenuItem;
import christmas.enums.Category;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.awt.*;
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
