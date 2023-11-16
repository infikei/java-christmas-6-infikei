package christmas.model;

import christmas.constant.Menu;

import static christmas.constant.ExceptionType.INVALID_ORDERS;

public class GiftMenu {
    private static final String TO_STRING_FORMAT = "%s %d개";

    private static final int MINIMUM_COUNT = 1;

    private final Menu menu;
    private final int count;

    public GiftMenu(Menu menu, int count) {
        this.menu = menu;
        validateCount(count);
        this.count = count;
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

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public int getAmount() {
        return menu.getPrice() * count;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, menu.getName(), count);
    }
}
