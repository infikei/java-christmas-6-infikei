package christmas.model;

import christmas.constant.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.MenuCategory.APPETIZER;
import static christmas.constant.MenuCategory.BEVERAGE;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.MAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenusTest {
    private void createOrderMenusAssertThatThrownBy(List<String> menus, List<Integer> counts) {
        Map<String, Integer> orderMenus = toMap(menus, counts);
        assertThatThrownBy(() -> new OrderMenus(orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getAmountAssertThat(Map<String, Integer> orderMenus, int expected) {
        assertThat(new OrderMenus(orderMenus).getAmount()).isEqualTo(expected);
    }

    private void getTotalCountAssertThat(Map<String, Integer> orderMenus, int expected) {
        assertThat(new OrderMenus(orderMenus).getTotalCount()).isEqualTo(expected);
    }

    private void getTotalCountOfCategoryAssertThat(Map<String, Integer> orderMenus,
                                                   MenuCategory category, int expected) {
        assertThat(new OrderMenus(orderMenus).getTotalCountOfCategory(category)).isEqualTo(expected);
    }

    private Map<String, Integer> toMap(List<String> menus, List<Integer> counts) {
        Map<String, Integer> orderMenus = new HashMap<>();

        for (int i = 0; i < menus.size(); i++) {
            orderMenus.put(menus.get(i), counts.get(i));
        }
        return orderMenus;
    }

    @DisplayName("음료 메뉴만 주어진 경우 예외 발생")
    @Test
    void createOrderMenusByOnlyDrinks() {
        createOrderMenusAssertThatThrownBy(List.of("제로콜라", "레드와인"), List.of(4, 2));
    }

    @DisplayName("음료 메뉴가 아닌 메뉴도 주어진 경우 결과 확인")
    @Test
    void createOrderMenusByMenusIncludingNonBeverage() {
        Map<String, Integer> orders = toMap(List.of("바비큐립", "레드와인"), List.of(2, 1));
        getAmountAssertThat(orders, 168_000);
        getTotalCountAssertThat(orders, 3);
        getTotalCountOfCategoryAssertThat(orders, APPETIZER, 0);
        getTotalCountOfCategoryAssertThat(orders, MAIN, 2);
        getTotalCountOfCategoryAssertThat(orders, DESSERT, 0);
        getTotalCountOfCategoryAssertThat(orders, BEVERAGE, 1);
    }

    @DisplayName("메뉴 개수의 총합이 21인 경우 예외 발생")
    @Test
    void createOrderMenusByTotalCount21() {
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(10, 11));
    }

    @DisplayName("메뉴 개수의 총합이 20인 경우 결과 확인")
    @Test
    void createOrderMenusByTotalCount20() {
        Map<String, Integer> orderMenus = toMap(List.of("크리스마스파스타", "아이스크림"), List.of(10, 10));
        getAmountAssertThat(orderMenus, 300_000);
        getTotalCountAssertThat(orderMenus, 20);
        getTotalCountOfCategoryAssertThat(orderMenus, APPETIZER, 0);
        getTotalCountOfCategoryAssertThat(orderMenus, MAIN, 10);
        getTotalCountOfCategoryAssertThat(orderMenus, DESSERT, 10);
        getTotalCountOfCategoryAssertThat(orderMenus, BEVERAGE, 0);
    }

    @DisplayName("주문 메뉴가 없는 메뉴인 경우 예외 발생")
    @Test
    void createOrderMenusByNotMenu() {
        createOrderMenusAssertThatThrownBy(List.of("없는메뉴", "레드와인"), List.of(4, 3));
    }

    @DisplayName("주문 메뉴가 빈 문자열인 경우 예외 발생")
    @Test
    void createOrderMenusByEmptyStringMenu() {
        createOrderMenusAssertThatThrownBy(List.of("", "레드와인"), List.of(4, 3));
    }

    @DisplayName("주문 개수가 0인 경우 예외 발생")
    @Test
    void createOrderMenusByZeroCount() {
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(4, 0));
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(0, 3));
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(0, 0));
    }

    @DisplayName("주문 개수가 음수인 경우 예외 발생")
    @Test
    void createOrderMenusByNegativeCount() {
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(4, -1));
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(-1, 3));
        createOrderMenusAssertThatThrownBy(List.of("바비큐립", "레드와인"), List.of(-1, -1));
    }
}
