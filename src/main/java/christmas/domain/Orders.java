package christmas.domain;

import christmas.constant.ExceptionType;
import christmas.constant.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Orders {
    private static final int COUNT_SUM_MAXIMUM = 20;

    private final List<Order> orders;

    public Orders(Map<String, Integer> orders) {
        this.orders = convertOrders(orders);
        validateOrders();
    }

    private List<Order> convertOrders(Map<String, Integer> orders) {
        List<Order> ordersConverted = new ArrayList<>();

        for (String menuName : orders.keySet()) {
            ordersConverted.add(new Order(menuName, orders.get(menuName)));
        }
        return ordersConverted;
    }

    private void validateOrders() {
        validateIsMenuCountSumInRange();
        validateHasMenuExceptDrink();
    }

    private void validateIsMenuCountSumInRange() {
        if (!isMenuCountSumInRange()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private boolean isMenuCountSumInRange() {
        return getCountSum() <= COUNT_SUM_MAXIMUM;
    }

    private void validateHasMenuExceptDrink() {
        if (!hasMenuExceptDrink()) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private boolean hasMenuExceptDrink() {
        for (Order order : orders) {
            if (order.getMenu().getCategory() != MenuCategory.DRINK) {
                return true;
            }
        }
        return false;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getPriceSum() {
        return orders.stream()
                .mapToInt(Order::getPriceSum)
                .sum();
    }

    public int getCountSum() {
        return orders.stream()
                .mapToInt(Order::getCount)
                .sum();
    }

    public int getCountSumOfCategory(MenuCategory category) {
        return orders.stream()
                .filter(order -> order.getMenu().getCategory() == category)
                .mapToInt(Order::getCount)
                .sum();
    }
}
