package christmas.controller;

import christmas.domain.rastaurant.MenuItem;
import christmas.domain.EventPlanner;
import christmas.domain.manager.MoneyManager;
import christmas.domain.manager.OrderMenuManager;
import christmas.domain.manager.ScheduleManager;
import christmas.domain.promotion.ComplimentaryItem;
import christmas.domain.promotion.policy.DiscountType;
import christmas.domain.promotion.EventBadge;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasPromotion {
    public void run() {
        InputView.startPrintEventPlanner();
        InputView.startInputVisitDate();
        ScheduleManager scheduleManager = createScheduleManager();

        InputView.startInputOrderMenu();
        OrderMenuManager orderMenuManager = createOrderMenuManager();

        MoneyManager moneyManager = createMoneyManager(orderMenuManager);
        EventPlanner eventPlanner = createEventPlanner(moneyManager, scheduleManager, orderMenuManager);

        eventPlanPrinter(scheduleManager, moneyManager, orderMenuManager, eventPlanner);
    }

    private void eventPlanPrinter(
            ScheduleManager scheduleManager,
            MoneyManager moneyManager,
            OrderMenuManager orderMenuManager,
            EventPlanner eventPlanner) {

        sendVisitDate(scheduleManager);
        sendOrderMenuList(orderMenuManager);
        sendTotalOrderAmountBeforeDiscount(moneyManager);
        sendComplimentaryItem(moneyManager);
        sendDiscountList(eventPlanner, moneyManager);
        sendTotalDiscountAmount(eventPlanner);
        sendEstimatedPaymentAfterDiscount(moneyManager, eventPlanner);
        sendEventBadge(moneyManager);
    }

    private void sendEventBadge(MoneyManager moneyManager) {
        OutputView.startPrintEventBadge();
        OutputView.printEventBadge(EventBadge.getEventBadge(moneyManager.getOrderAmount()));
    }

    private void sendEstimatedPaymentAfterDiscount(MoneyManager moneyManager, EventPlanner eventPlanner) {
        OutputView.startPrintEstimatedPaymentAfterDiscount();

        int result = moneyManager.totalAmountAfterDiscount(eventPlanner.getDiscountAmountForCalculate());
        OutputView.printEstimatedPaymentAfterDiscount(result);
    }

    private void sendTotalDiscountAmount(EventPlanner eventPlanner) {
        OutputView.startTotalDiscountAmount();
        OutputView.printTotalDiscountAmount(eventPlanner.getDiscountAmount());
    }

    private void sendComplimentaryItem(MoneyManager moneyManager) {
        OutputView.startPrintComplimentaryItem();
        String item = ComplimentaryItem.findItem(moneyManager.getOrderAmount()).getItem();
        int quantity = ComplimentaryItem.findQuantity(item);

        if (quantity == 0) {
            OutputView.printNothing();
            return;
        }
        OutputView.printComplimentaryItem(item, quantity);
    }

    private void sendTotalOrderAmountBeforeDiscount(MoneyManager moneyManager) {
        OutputView.startPrintTotalOrderAmountBeforeDiscount();
        OutputView.printTotalOrderAmountBeforeDiscount(moneyManager.getOrderAmount());
    }

    private void sendDiscountList(EventPlanner eventPlanner, MoneyManager moneyManager) {
        OutputView.startPrintDiscountList();
        if (eventPlanner.getDiscountList().isEmpty()) {
            OutputView.printNothing();
            return;
        }
        for (Map.Entry<DiscountType, Integer> entry : eventPlanner.getDiscountList().entrySet()) {
            OutputView.printDiscountList(entry.getKey().getName(), entry.getValue());
        }
    }

    private void sendOrderMenuList(OrderMenuManager orderMenuManager) {
        OutputView.startPrintOrderMenu();

        for (Map.Entry<MenuItem, Integer> entry : orderMenuManager.getOrderMenu().entrySet()) {
            String menuName = entry.getKey().getName();
            int count = entry.getValue();
            OutputView.printOrderMenu(menuName, count);
        }
    }

    private void sendVisitDate(ScheduleManager scheduleManager) {
        OutputView.printVisitDate(scheduleManager.getVisitDate());
    }

    private EventPlanner createEventPlanner(MoneyManager moneyManager, ScheduleManager scheduleManager, OrderMenuManager orderMenuManager) {
        return new EventPlanner(moneyManager, scheduleManager, orderMenuManager);
    }

    private MoneyManager createMoneyManager(OrderMenuManager orderMenuManager) {
        return new MoneyManager(orderMenuManager.getOrderTotalAmount());
    }

    private OrderMenuManager createOrderMenuManager() {
        try {
            return new OrderMenuManager(InputView.inputOrderMenu());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createOrderMenuManager();
        }
    }

    private ScheduleManager createScheduleManager() {
        try {
            return new ScheduleManager(InputView.inputVisitDate());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createScheduleManager();
        }
    }

}
