package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.InputConverter;

import java.util.Map;

public class InputView {

    private static final String START_EVENT_PLANNER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n";
    private static final String INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String INPUT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n";

    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";
    public static final String ERROR_ORDER_MENU = "유효하지 않은 주문입니다. 다시 입력해 주세요.\n";

    public static void startPrintEventPlanner(){
        System.out.print(START_EVENT_PLANNER);
    }
    public static void startInputVisitDate(){
        System.out.print(INPUT_VISIT_DATE);
    }

    public static void startInputOrderMenu(){
        System.out.print(INPUT_ORDER_MENU);
    }

    public static int inputVisitDate(){
        try{
            return Integer.parseInt(Console.readLine());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ERROR_VISIT_DATE);
        }
    }

    public static Map<String,Integer> inputOrderMenu(){
        try{
            return InputConverter.parseMenu(Console.readLine());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ERROR_ORDER_MENU);
        }
    }

}
