package christmas.model;

import christmas.constant.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {
    private void createOrdersAssertThatThrownBy(List<String> menus, List<Integer> counts) {
        Map<String, Integer> orders = toMap(menus, counts);
        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getPriceSumAssertThat(Map<String, Integer> orders, int expected) {
        assertThat(new Orders(orders).getPriceSum()).isEqualTo(expected);
    }

    private void getCountSumAssertThat(Map<String, Integer> orders, int expected) {
        assertThat(new Orders(orders).getCountSum()).isEqualTo(expected);
    }

    private void getCountSumOfCategoryAssertThat(Map<String, Integer> orders, MenuCategory category, int expected) {
        assertThat(new Orders(orders).getCountSumOfCategory(category)).isEqualTo(expected);
    }

    private Map<String, Integer> toMap(List<String> menus, List<Integer> counts) {
        Map<String, Integer> orders = new HashMap<>();

        for (int i = 0; i < menus.size(); i++) {
            orders.put(menus.get(i), counts.get(i));
        }
        return orders;
    }

    @DisplayName("음료 메뉴만 주어진 경우 예외 발생")
    @Test
    void createOrdersByOnlyDrinks() {
        createOrdersAssertThatThrownBy(List.of("제로콜라", "레드와인"), List.of(4, 2));
    }

    @DisplayName("음료 메뉴가 아닌 메뉴도 주어진 경우 결과 확인")
    @Test
    void createOrdersByMenusIncludingNonDrinks() {
        Map<String, Integer> orders = toMap(List.of("바비큐립", "레드와인"), List.of(2, 1));
        getPriceSumAssertThat(orders, 168_000);
        getCountSumAssertThat(orders, 3);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.APPETIZER, 0);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.MAIN, 2);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.DESSERT, 0);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.DRINK, 1);
    }

    @DisplayName("메뉴 개수의 총합이 21인 경우 예외 발생")
    @Test
    void createOrdersByMenuCountSum21() {
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(10, 11));
    }

    @DisplayName("메뉴 개수의 총합이 20인 경우 결과 확인")
    @Test
    void createOrdersByMenuCountSum20() {
        Map<String, Integer> orders = toMap(List.of("크리스마스파스타", "아이스크림"), List.of(10, 10));
        getPriceSumAssertThat(orders, 300_000);
        getCountSumAssertThat(orders, 20);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.APPETIZER, 0);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.MAIN, 10);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.DESSERT, 10);
        getCountSumOfCategoryAssertThat(orders, MenuCategory.DRINK, 0);
    }

    @DisplayName("주문 메뉴가 없는 메뉴인 경우 예외 발생")
    @Test
    void createOrdersByNotMenu() {
        createOrdersAssertThatThrownBy(List.of("없는메뉴", "레드와인"), List.of(4, 3));
    }

    @DisplayName("주문 메뉴가 빈 문자열인 경우 예외 발생")
    @Test
    void createOrdersByEmptyStringMenu() {
        createOrdersAssertThatThrownBy(List.of("", "레드와인"), List.of(4, 3));
    }

    @DisplayName("주문 개수가 0인 경우 예외 발생")
    @Test
    void createOrdersByZeroCount() {
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(4, 0));
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(0, 3));
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(0, 0));
    }

    @DisplayName("주문 개수가 음수인 경우 예외 발생")
    @Test
    void createOrdersByNegativeCount() {
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(4, -1));
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(-1, 3));
        createOrdersAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(-1, -1));
    }
}
