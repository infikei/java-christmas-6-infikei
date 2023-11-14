package christmas.domain;

import christmas.constant.ExceptionType;
import christmas.constant.Menu;

public class Order {
    private static final String TO_STRING_FORMAT = "%s %d개";

    private static final int COUNT_MINIMUM = 1;

    private final Menu menu;
    private final int count;

    public Order(String name, int count) {
        this.menu = convertMenu(name);
        validateCount(count);
        this.count = count;
    }

    private Menu convertMenu(String name) {
        try {
            return Menu.nameOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private void validateCount(int count) {
        validateIsCountInRange(count);
    }

    private void validateIsCountInRange(int count) {
        if (!isCountInRange(count)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private boolean isCountInRange(int count) {
        return count >= COUNT_MINIMUM;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public int getPriceSum() {
        return menu.getPrice() * count;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, menu.getName(), count);
    }
}
