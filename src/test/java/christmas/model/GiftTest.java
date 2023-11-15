package christmas.model;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GiftTest {
    private void createGiftAssertThatThrownBy(Menu menu, int count) {
        assertThatThrownBy(() -> new Gift(menu, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getMenuAssertThat(Menu menu, Menu expected) {
        assertThat(new Gift(menu, 1).getMenu()).isEqualTo(expected);
    }

    private void getCountAssertThat(int count, int expected) {
        assertThat(new Gift(Menu.CHAMPAGNE, count).getCount()).isEqualTo(expected);
    }

    private void getPriceSumAssertThat(Menu menu, int count, int expected) {
        assertThat(new Gift(menu, count).getPriceSum()).isEqualTo(expected);
    }

    private void toStringAssertThat(Menu menu, int count, String expected) {
        assertThat(new Gift(menu, count).toString()).isEqualTo(expected);
    }

    @DisplayName("증정 메뉴가 샴페인 1개인 경우 결과 확인")
    @Test
    void createGiftByOneChampagne() {
        getMenuAssertThat(Menu.CHAMPAGNE, Menu.CHAMPAGNE);
        getCountAssertThat(1, 1);
        getPriceSumAssertThat(Menu.CHAMPAGNE, 1, 25_000);
        toStringAssertThat(Menu.CHAMPAGNE, 1, "샴페인 1개");
    }

    @DisplayName("증정 메뉴가 샴페인 2개인 경우 결과 확인")
    @Test
    void createGiftByTwoChampagne() {
        getMenuAssertThat(Menu.CHAMPAGNE, Menu.CHAMPAGNE);
        getCountAssertThat(2, 2);
        getPriceSumAssertThat(Menu.CHAMPAGNE, 2, 50_000);
        toStringAssertThat(Menu.CHAMPAGNE, 2, "샴페인 2개");
    }

    @DisplayName("증정 메뉴가 제로콜라 2개인 경우 결과 확인")
    @Test
    void createGiftByTwoZeroCoke() {
        getMenuAssertThat(Menu.ZERO_COKE, Menu.ZERO_COKE);
        getCountAssertThat(2, 2);
        getPriceSumAssertThat(Menu.ZERO_COKE, 2, 6_000);
        toStringAssertThat(Menu.ZERO_COKE, 2, "제로콜라 2개");
    }

    @DisplayName("증정 개수가 0인 경우 예외 발생")
    @Test
    void createGiftByZeroCount() {
        createGiftAssertThatThrownBy(Menu.CHAMPAGNE, 0);
    }

    @DisplayName("증정 개수가 음수인 경우 예외 발생")
    @Test
    void createGiftByNegativeCount() {
        createGiftAssertThatThrownBy(Menu.CHAMPAGNE, -1);
    }
}
