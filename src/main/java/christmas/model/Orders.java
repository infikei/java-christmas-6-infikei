package christmas.model;

import christmas.constant.ExceptionType;
import christmas.constant.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Orders {
    private static final int COUNT_SUM_MAXIMUM = 20;

    private final List<Order> orders;

    public Orders(Map<String, Integer> orders) {
        this.orders = convert(orders);
        validate(this.orders);
    }

    private List<Order> convert(Map<String, Integer> orders) {
        List<Order> ordersConverted = new ArrayList<>();

        for (String menuName : orders.keySet()) {
            ordersConverted.add(new Order(menuName, orders.get(menuName)));
        }
        return ordersConverted;
    }

    private void validate(List<Order> orders) {
        if (!isMenuCountSumInRange(orders)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
        if (!hasMenuExceptDrink(orders)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private boolean isMenuCountSumInRange(List<Order> orders) {
        return getMenuCountSum(orders) <= COUNT_SUM_MAXIMUM;
    }

    private int getMenuCountSum(List<Order> orders) {
        int menuCountSum = 0;

        for (Order order : orders) {
            menuCountSum += order.getCount();
        }
        return menuCountSum;
    }

    private boolean hasMenuExceptDrink(List<Order> orders) {
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
        int priceSum = 0;

        for (Order order : orders) {
            priceSum += order.getPriceSum();
        }
        return priceSum;
    }

    public int getMenuCountSumOfCategory(MenuCategory category) {
        return orders.stream()
                .filter(order -> order.equalsCategory(category))
                .mapToInt(Order::getCount)
                .sum();
    }
}
