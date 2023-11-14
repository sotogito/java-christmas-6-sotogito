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
        sendMenuList();
        OrderManager orderManager = getOrderMenu(); //여기문제
        MoneyManager moneyManager = getAmountBeforeDiscount(orderManager);
        EventPlanner eventPlanner = runEventPlanner(scheduleManager,orderManager);

        /*
        int date = scheduleManager.calculateDDay();
        System.out.println(eventPlanner.getDDayDiscount(date)+"디데이할인");
        System.out.println(eventPlanner.getWeekdayDiscount()+"평일할인");
        System.out.println(eventPlanner.getWeekendDiscount()+"주말할인");
        System.out.println(eventPlanner.getSpecialDiscount()+"스페셩할인");
        //EnumMap으로 넣어서 출력
        eventPlanner.getComplimentaryMenu(moneyManager.getTotalOrderAmount());
        System.out.println(eventPlanner.getAmountToDiscount());
        System.out.println(eventPlanner.getTotalDiscountAmount());
        */



        System.out.println("-------------------");

        sendIntroPreview(scheduleManager);
        sendOrderList(orderManager);
        sendAmountBeforeDiscount(moneyManager);
        sendComplimentaryMenu(moneyManager,eventPlanner);
        sendDiscountDetails(eventPlanner,scheduleManager);

        sendAmount(moneyManager,eventPlanner);
        sendEventBadge(eventPlanner);

        //총혜택
        //할인 후 예산 결제 금액
        //12원 이벤트 배지




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





    private void sendIntroPreview(ScheduleManager scheduleManager){
        OutputView.printIntroPreviewToView(scheduleManager.getVisitDate());
    }
    private void sendMenuList(){
        for (Map.Entry<Category, List<MenuItem>> menu : Menu.getMenu().entrySet()) {
            OutputView.printMenuList(menu.getKey(),menu.getValue());
        }
    }
    private void sendOrderList(OrderManager orderManager){
        Map<String,Integer> orderMenuAndQuantity = orderManager.getOrderMenuAndQuantity();
        OutputView.printOrderMenu(orderMenuAndQuantity);
    }
    private void sendAmountBeforeDiscount(MoneyManager moneyManager){
        OutputView.printAmountBeforeDiscount(moneyManager.getTotalOrderAmount());

    }
    private void sendComplimentaryMenu(MoneyManager moneyManager,EventPlanner eventPlanner){
        int amount = moneyManager.getTotalOrderAmount();
        Map<String,Integer> complimentaryMenu = eventPlanner.getComplimentaryMenu(amount);
        OutputView.printComplimentaryMenu(complimentaryMenu);

    }
    private void sendDiscountDetails(EventPlanner eventPlanner,ScheduleManager scheduleManager ){
        Boolean nothing = false;
        if(!MoneyManager.isWithinRangeAmount()){
            nothing = true;
        }
        DiscountDetail discountDetail = new DiscountDetail(eventPlanner,scheduleManager);
        OutputView.printDiscountDetails(discountDetail.getDiscountDetails(),nothing);
    }

    private void sendAmount(MoneyManager moneyManager,EventPlanner eventPlanner){
        int totalDiscountAmount = eventPlanner.getTotalDiscountAmount();
        int discountedAmount = moneyManager.getTotalOrderAmount()-eventPlanner.getAmountToDiscount();

        OutputView.printTotalDiscountAmount(totalDiscountAmount);
        OutputView.printDiscountedAmount(discountedAmount);
    }
    private void sendEventBadge(EventPlanner eventPlanner){
        String BadgeType = EventBadge.findBadgeForAmount(eventPlanner.getTotalDiscountAmount());
        OutputView.printEventBadge(BadgeType);
    }




}
