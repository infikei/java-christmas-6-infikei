package christmas.model;

import christmas.constant.ExceptionType;
import christmas.constant.Menu;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(String name, int count) {
        this.menu = convertMenu(name);
        this.count = validateCount(count);
    }

    private Menu convertMenu(String name) {
        try {
            return Menu.nameOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
    }

    private int validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ExceptionType.INVALID_ORDERS.getMessage());
        }
        return count;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
