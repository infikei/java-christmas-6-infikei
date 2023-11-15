package christmas.model;

import christmas.constant.Menu;
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

    @Test
    void 주문_메뉴가_빈_문자열인_경우_예외_발생() {
        createOrderAssertThatThrownBy("", 2);
    }

    @Test
    void 주문_메뉴가_없는_메뉴인_경우_예외_발생() {
        createOrderAssertThatThrownBy("우테코식당에서가장맛있는메뉴", 2);
    }

    @Test
    void 주문_메뉴가_양송이수프인_경우_메뉴_확인() {
        getMenuAssertThat("양송이수프", Menu.MUSHROOM_SOUP);
        getMenuNameAssertThat("양송이수프", "양송이수프");
    }

    @Test
    void 주문_메뉴가_바비큐립인_경우_메뉴_확인() {
        getMenuAssertThat("바비큐립", Menu.BBQ_RIBS);
        getMenuNameAssertThat("바비큐립", "바비큐립");
    }

    @Test
    void 주문_개수가_0인_경우_예외_발생() {
        createOrderAssertThatThrownBy("양송이수프", 0);
    }

    @Test
    void 주문_개수가_음수인_경우_예외_발생() {
        createOrderAssertThatThrownBy("양송이수프", -1);
    }

    @Test
    void 주문_개수가_1인_경우_개수_확인() {
        getCountAssertThat(1, 1);
    }

    @Test
    void 주문_개수가_2인_경우_개수_확인() {
        getCountAssertThat(2, 2);
    }

    @Test
    void 주문_메뉴가_타파스이고_개수가_2인_경우_가격_확인() {
        getPriceSumAssertThat("타파스", 2, 11_000);
    }

    @Test
    void 주문_메뉴가_해산물파스타이고_개수가_4인_경우_가격_확인() {
        getPriceSumAssertThat("해산물파스타", 4, 140_000);
    }
}
