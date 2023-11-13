package christmas.util.Validator;

import christmas.view.OutputView;

public class VisitDateValidator {
    private static final int MIN = 1;
    private static final int MAX = 31;
    private static final String ERROR_VISIT_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.\n";


    public static void visitDateValidator(int date) {
        if (!isWithinRangeNum(date)) {
            throw new IllegalArgumentException(ERROR_VISIT_DATE);
        }
    }

    private static boolean isWithinRangeNum(int date) {
        if (date >= MIN && date <= MAX) {
            return true;
        }
        return false;
    }

}
