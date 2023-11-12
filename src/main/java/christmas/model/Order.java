package christmas.model;

import christmas.constant.ExceptionType;
import christmas.constant.Menu;

public class Order {
    private static final int COUNT_MINIMUM = 1;

    private final Menu menu;
    private final int count;

    public Order(String menuName, int count) {
        this.menu = convertMenu(menuName);
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
}
