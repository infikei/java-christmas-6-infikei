package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ExceptionType;
import christmas.util.TypeConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    private static final String INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDERS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int readDate() {
        System.out.println(INPUT_DATE);

        try {
            return TypeConverter.toInteger(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE.getMessage());
        }
    }

    public Map<String, Integer> readOrders() {
        System.out.println(INPUT_ORDERS);

        try {
            return convertReadOrders(splitReadOrders(Console.readLine()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private List<List<String>> splitReadOrders(String orders) {
        List<List<String>> ordersConverted = new ArrayList<>();

        for (String order : TypeConverter.splitExactly(orders, ',')) {
            ordersConverted.add(TypeConverter.splitExactly(order, '-'));
        }
        return ordersConverted;
    }

    private Map<String, Integer> convertReadOrders(List<List<String>> orders) {
        Map<String, Integer> ordersConverted = new HashMap<>();

        for (List<String> order : orders) {
            if (order.size() != 2) {
                throw new IllegalArgumentException();
            }
            if (ordersConverted.containsKey(order.get(0))) {
                throw new IllegalArgumentException();
            }
            ordersConverted.put(order.get(0), TypeConverter.toInteger(order.get(1)));
        }
        return ordersConverted;
    }
}
