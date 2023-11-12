package christmas.validator;

import christmas.constant.ExceptionType;
import christmas.util.TypeConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputValidator {
    public int validateReadDate(String date) {
        try {
            return TypeConverter.toInteger(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE.getMessage());
        }
    }

    public Map<String, Integer> validateReadOrders(String orders) {
        try {
            List<List<String>> ordersSplit = splitReadOrders(orders);
            validateEachElementSizeTwo(ordersSplit);
            return convertReadOrders(ordersSplit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private List<List<String>> splitReadOrders(String orders) {
        List<List<String>> ordersConverted = new ArrayList<>();

        for (String order : TypeConverter.split(orders, ',')) {
            ordersConverted.add(TypeConverter.split(order, '-'));
        }
        return ordersConverted;
    }

    private Map<String, Integer> convertReadOrders(List<List<String>> orders) {
        Map<String, Integer> ordersConverted = new HashMap<>();

        for (List<String> order : orders) {
            if (ordersConverted.containsKey(order.get(0))) {
                throw new IllegalArgumentException();
            }
            ordersConverted.put(order.get(0), TypeConverter.toInteger(order.get(1)));
        }
        return ordersConverted;
    }

    private void validateEachElementSizeTwo(List<List<String>> values) {
        for (List<String> value : values) {
            validateSizeTwo(value);
        }
    }

    private void validateSizeTwo(List<String> values) {
        if (!isSizeTwo(values)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isSizeTwo(List<String> values) {
        return values.size() == 2;
    }
}
