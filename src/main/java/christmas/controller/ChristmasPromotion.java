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

        InputView.startInputOrderMenu();
        OrderMenuManager orderMenuManager = createOrderMenuManager();

        for (Map.Entry<MenuItem, Integer> entry : orderMenuManager.getOrderMenu().entrySet()) {
            System.out.println(entry.getKey().getName()+"메뉴 이름");
            System.out.println(entry.getKey().getPrice()+"가격");
            System.out.println(entry.getValue()+"수량");
        }

        for (Map.Entry<Category, Integer> entry : orderMenuManager.getOrderCategoryList().entrySet()) {
            System.out.println(entry.getKey().getName()+"카테고리 이름");
            System.out.println(entry.getValue()+"수량");
        }

        System.out.println(orderMenuManager.getOrderTotalAmount());



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
