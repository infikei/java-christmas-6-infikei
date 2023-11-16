package christmas.view;

import christmas.constant.Badge;
import christmas.constant.EventType;
import christmas.model.GiftMenu;
import christmas.model.OrderMenu;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PLANNER_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PLANNER_RESULT_START_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENUS_START = "<주문 메뉴>";
    private static final String ORDER_MENUS_AMOUNT_START = "<할인 전 총주문 금액>";
    private static final String ORDER_MENUS_AMOUNT_FORMAT = "%,d원";
    private static final String GIFT_MENUS_START = "<증정 메뉴>";
    private static final String EVENTS_START = "<혜택 내역>";
    private static final String EVENT_FORMAT = "%s: %,d원";
    private static final String TOTAL_DISCOUNT_START = "<총혜택 금액>";
    private static final String TOTAL_DISCOUNT_FORMAT = "%,d원";
    private static final String RESULT_AMOUNT_START = "<할인 후 예상 결제 금액>";
    private static final String RESULT_AMOUNT_FORMAT = "%,d원";
    private static final String BADGE_START = "<12월 이벤트 배지>";
    private static final String NONE = "없음";

    public void printPlannerStart() {
        System.out.println(PLANNER_START);
    }

    public void printPlannerResultStart(int date) {
        System.out.println(String.format(PLANNER_RESULT_START_FORMAT, date));
    }

    public void printOrderMenus(List<OrderMenu> orderMenus) {
        System.out.println(ORDER_MENUS_START);

        for (OrderMenu orderMenu : orderMenus) {
            System.out.println(orderMenu);
        }
    }

    public void printOrderMenusAmount(int orderMenusAmount) {
        System.out.println(ORDER_MENUS_AMOUNT_START);
        System.out.println(String.format(ORDER_MENUS_AMOUNT_FORMAT, orderMenusAmount));
    }

    public void printGiftMenus(List<GiftMenu> giftMenus) {
        System.out.println(GIFT_MENUS_START);

        if (giftMenus.isEmpty()) {
            printNone();
            return;
        }

        for (GiftMenu giftMenu : giftMenus) {
            System.out.println(giftMenu);
        }
    }

    public void printEvents(Map<EventType, Integer> events) {
        System.out.println(EVENTS_START);

        if (events.isEmpty()) {
            printNone();
            return;
        }

        for (Map.Entry<EventType, Integer> event : events.entrySet()) {
            System.out.println(String.format(EVENT_FORMAT, event.getKey(), -1 * event.getValue()));
        }
    }

    public void printTotalDiscount(int totalDiscount) {
        System.out.println(TOTAL_DISCOUNT_START);
        System.out.println(String.format(TOTAL_DISCOUNT_FORMAT, -1 * totalDiscount));
    }

    public void printResultAmount(int resultAmount) {
        System.out.println(RESULT_AMOUNT_START);
        System.out.println(String.format(RESULT_AMOUNT_FORMAT, resultAmount));
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
