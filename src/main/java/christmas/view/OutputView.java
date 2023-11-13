package christmas.view;

public class OutputView {
    private static final String PRINT_PLANNER_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_RESULT_START_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printPlannerStart() {
        System.out.println(PRINT_PLANNER_START);
    }

    public void printPlannerResultStart(int date) {
        System.out.println(String.format(PRINT_RESULT_START_FORMAT, date));
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
