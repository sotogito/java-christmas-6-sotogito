package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validator.VisitDateValidator;

public class InputView {

    private static final String MESSAGE_INTRO_EVENT_PLANNER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n";
    private static final String MESSAGE_INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";


    public int getVisitData(){
        System.out.print(MESSAGE_INPUT_VISIT_DATE);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_VISIT_DATE);
        }
    }

    private String inputData(){
        return Console.readLine();
    }
    public static void introEventPlaner(){
        System.out.print(MESSAGE_INTRO_EVENT_PLANNER);
    }
}
