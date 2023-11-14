package christmas.view;

import christmas.model.Order;

import java.util.List;

public class OutputView {
    private static final String PRINT_PLANNER_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_RESULT_START_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String PRINT_ORDERS_START = "<주문 메뉴>";
    private static final String PRINT_ORDERS_PRICE_SUM_START = "<할인 전 총주문 금액>";
    private static final String PRINT_ORDERS_PRICE_SUM_FORMAT = "%,d원";
    private static final String PRINT_GIFTS_START = "<증정 메뉴>";
    private static final String PRINT_NONE = "없음";

    public void printPlannerStart() {
        System.out.println(PRINT_PLANNER_START);
    }

    public void printPlannerResultStart(int date) {
        System.out.println(String.format(PRINT_RESULT_START_FORMAT, date));
    }

    public void printOrders(List<Order> orders) {
        System.out.println(PRINT_ORDERS_START);

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void printOrdersPriceSum(int ordersPriceSum) {
        System.out.println(PRINT_ORDERS_PRICE_SUM_START);
        System.out.println(String.format(PRINT_ORDERS_PRICE_SUM_FORMAT, ordersPriceSum));
    }

    public void printGifts(List<Order> gifts) {
        System.out.println(PRINT_GIFTS_START);

        if (gifts.isEmpty()) {
            printNone();
            return;
        }

        for (Order gift : gifts) {
            System.out.println(gift);
        }
    }

    private void printNone() {
        System.out.println(PRINT_NONE);
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
