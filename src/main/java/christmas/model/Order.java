package christmas.model;

import christmas.constant.ExceptionType;
import christmas.constant.Menu;
import christmas.constant.MenuCategory;

public class Order {
    private static final int COUNT_MINIMUM = 1;

    private final Menu menu;
    private final int count;

    public Order(String name, int count) {
        this.menu = convertMenu(name);
        validateCount(count);
        this.count = count;
    }

    public Order(Menu menu, int count) {
        this.menu = menu;
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

    public int getPriceSum() {
        return menu.getPrice() * count;
    }
}
