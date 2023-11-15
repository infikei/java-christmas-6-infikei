package christmas.model;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    private void createOrderAssertThatThrownBy(String name, int count) {
        assertThatThrownBy(() -> new Order(name, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getMenuAssertThat(String name, Menu expected) {
        assertThat(new Order(name, 1).getMenu()).isEqualTo(expected);
    }

    private void getMenuNameAssertThat(String name, String expected) {
        assertThat(new Order(name, 1).getMenu().getName()).isEqualTo(expected);
    }

    private void getCountAssertThat(int count, int expected) {
        assertThat(new Order("양송이수프", count).getCount()).isEqualTo(expected);
    }

    private void getPriceSumAssertThat(String name, int count, int expected) {
        assertThat(new Order(name, count).getPriceSum()).isEqualTo(expected);
    }

    @DisplayName("주문 메뉴가 빈 문자열인 경우 예외 발생")
    @Test
    void createOrderByEmptyString() {
        createOrderAssertThatThrownBy("", 2);
    }

    @DisplayName("주문 메뉴가 없는 메뉴인 경우 예외 발생")
    @Test
    void createOrderByNotMenu() {
        createOrderAssertThatThrownBy("우테코식당에서가장맛있는메뉴", 2);
    }

    @DisplayName("주문 메뉴가 양송이수프인 경우 메뉴 확인")
    @Test
    void createOrderByMushroomSoup() {
        getMenuAssertThat("양송이수프", Menu.MUSHROOM_SOUP);
        getMenuNameAssertThat("양송이수프", "양송이수프");
    }

    @DisplayName("주문 메뉴가 바비큐립인 경우 메뉴 확인")
    @Test
    void createOrderByBBQRibs() {
        getMenuAssertThat("바비큐립", Menu.BBQ_RIBS);
        getMenuNameAssertThat("바비큐립", "바비큐립");
    }

    @DisplayName("주문 개수가 0인 경우 예외 발생")
    @Test
    void createOrderByCountZero() {
        createOrderAssertThatThrownBy("양송이수프", 0);
    }

    @DisplayName("주문 개수가 음수인 경우 예외 발생")
    @Test
    void createOrderByCountNegativeInteger() {
        createOrderAssertThatThrownBy("양송이수프", -1);
    }

    @DisplayName("주문 개수가 1인 경우 개수 확인")
    @Test
    void createOrderByCountOne() {
        getCountAssertThat(1, 1);
    }

    @DisplayName("주문 개수가 2인 경우 개수 확인")
    @Test
    void createOrderByCountTwo() {
        getCountAssertThat(2, 2);
    }

    @DisplayName("주문 메뉴가 타파스이고 개수가 2인 경우 가격 확인")
    @Test
    void getPriceSumByTwoTapas() {
        getPriceSumAssertThat("타파스", 2, 11_000);
    }

    @DisplayName("주문 메뉴가 해산물파스타이고 개수가 4인 경우 가격 확인")
    @Test
    void getPriceSumByFourSeafoodPasta() {
        getPriceSumAssertThat("해산물파스타", 4, 140_000);
    }
}
