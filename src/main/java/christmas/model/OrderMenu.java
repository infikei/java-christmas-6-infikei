package christmas.model;

import christmas.constant.Menu;
import christmas.constant.MenuCategory;

import static christmas.constant.ExceptionType.INVALID_ORDERS;

public class OrderMenu {
    private static final String TO_STRING_FORMAT = "%s %d개";

    private static final int MINIMUM_COUNT = 1;

    private final Menu menu;
    private final int count;

    public OrderMenu(String name, int count) {
        this.menu = toMenu(name);
        validateCount(count);
        this.count = count;
    }

    private Menu toMenu(String name) {
        try {
            return Menu.nameOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private void validateCount(int count) {
        validateIsCountInRange(count);
    }

    private void validateIsCountInRange(int count) {
        if (!isCountInRange(count)) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private boolean isCountInRange(int count) {
        return count >= MINIMUM_COUNT;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, menu, count);
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuCategory getCategory() {
        return menu.getCategory();
    }

    public int getCount() {
        return count;
    }

    public int getAmount() {
        return menu.getPrice() * count;
    }
}
