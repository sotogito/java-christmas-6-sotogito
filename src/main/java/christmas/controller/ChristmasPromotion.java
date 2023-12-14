package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.MenuItem;
import christmas.domain.OrderMenuManager;
import christmas.domain.ScheduleManager;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasPromotion {
    public void run(){
        InputView.startPrintEventPlanner();
        InputView.startInputVisitDate();
        ScheduleManager scheduleManager = createScheduleManager();



    }

    private OrderMenuManager createOrderMenuManager(){

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
