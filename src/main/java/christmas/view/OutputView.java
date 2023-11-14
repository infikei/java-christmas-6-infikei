package christmas.view;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.model.Order;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PLANNER_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_START_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERS_START = "<주문 메뉴>";
    private static final String ORDERS_PRICE_SUM_START = "<할인 전 총주문 금액>";
    private static final String ORDERS_PRICE_SUM_FORMAT = "%,d원";
    private static final String GIFTS_START = "<증정 메뉴>";
    private static final String EVENTS_START = "<혜택 내역>";
    private static final String EVENT_FORMAT = "%s: %,d원";
    private static final String SALE_SUM_START = "<총혜택 금액>";
    private static final String SALE_SUM_FORMAT = "%,d원";
    private static final String RESULT_PRICE_SUM_START = "<할인 후 예상 결제 금액>";
    private static final String RESULT_PRICE_SUM_FORMAT = "%,d원";
    private static final String BADGE_START = "<12월 이벤트 배지>";
    private static final String NONE = "없음";

    public void printPlannerStart() {
        System.out.println(PLANNER_START);
    }

    public void printPlannerResultStart(int date) {
        System.out.println(String.format(RESULT_START_FORMAT, date));
    }

    public void printOrders(List<Order> orders) {
        System.out.println(ORDERS_START);

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void printOrdersPriceSum(int ordersPriceSum) {
        System.out.println(ORDERS_PRICE_SUM_START);
        System.out.println(String.format(ORDERS_PRICE_SUM_FORMAT, ordersPriceSum));
    }

    public void printGifts(List<Order> gifts) {
        System.out.println(GIFTS_START);

        if (gifts.isEmpty()) {
            printNone();
            return;
        }

        for (Order gift : gifts) {
            System.out.println(gift);
        }
    }

    public void printEvents(Map<Event, Integer> events) {
        System.out.println(EVENTS_START);

        if (events.isEmpty()) {
            printNone();
            return;
        }

        for (Map.Entry<Event, Integer> event : events.entrySet()) {
            System.out.println(String.format(EVENT_FORMAT, event.getKey(), -1 * event.getValue()));
        }
    }

    public void printEventsSaleSum(int saleSum) {
        System.out.println(SALE_SUM_START);
        System.out.println(String.format(SALE_SUM_FORMAT, -1 * saleSum));
    }

    public void printResultPriceSum(int resultPriceSum) {
        System.out.println(RESULT_PRICE_SUM_START);
        System.out.println(String.format(RESULT_PRICE_SUM_FORMAT, resultPriceSum));
    }

    public void printBadge(Badge badge) {
        System.out.println(BADGE_START);
        System.out.println(badge);
    }

    private void printNone() {
        System.out.println(NONE);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
