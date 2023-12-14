package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.MenuItem;
import christmas.domain.OrderMenuManager;
import christmas.domain.ScheduleManager;
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
            //날자
        InputView.startInputOrderMenu();
        OrderMenuManager orderMenuManager = createOrderMenuManager();
            //메뉴
        sendVisitDate(scheduleManager);



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
