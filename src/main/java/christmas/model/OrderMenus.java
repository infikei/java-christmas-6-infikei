package christmas.model;

import christmas.constant.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.constant.ExceptionType.INVALID_ORDERS;
import static christmas.constant.MenuCategory.BEVERAGE;

public class OrderMenus {
    private static final int COUNT_SUM_MAXIMUM = 20;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(Map<String, Integer> orderMenus) {
        this.orderMenus = toListOfOrderMenu(orderMenus);
        validateOrderMenus();
    }

    private List<OrderMenu> toListOfOrderMenu(Map<String, Integer> orderMenus) {
        List<OrderMenu> ordersConverted = new ArrayList<>();

        for (String menuName : orderMenus.keySet()) {
            ordersConverted.add(new OrderMenu(menuName, orderMenus.get(menuName)));
        }
        return ordersConverted;
    }

    private void validateOrderMenus() {
        validateIsCountSumInRange();
        validateHasNonBeverageMenu();
    }

    private void validateIsCountSumInRange() {
        if (!isCountSumInRange()) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private boolean isCountSumInRange() {
        return getTotalCount() <= COUNT_SUM_MAXIMUM;
    }

    private void validateHasNonBeverageMenu() {
        if (!hasNonBeverageMenu()) {
            throw new IllegalArgumentException(INVALID_ORDERS.getMessage());
        }
    }

    private boolean hasNonBeverageMenu() {
        for (OrderMenu orderMenu : orderMenus) {
            if (orderMenu.getCategory() != BEVERAGE) {
                return true;
            }
        }
        return false;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public int getAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getAmount)
                .sum();
    }

    public int getTotalCount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getCount)
                .sum();
    }

    public int getTotalCountOfCategory(MenuCategory category) {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.getCategory() == category)
                .mapToInt(OrderMenu::getCount)
                .sum();
    }
}
