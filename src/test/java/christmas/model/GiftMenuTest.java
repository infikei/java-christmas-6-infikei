package christmas.model;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.Menu.CHAMPAGNE;
import static christmas.constant.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GiftMenuTest {
    private void createGiftAssertThatThrownBy(Menu menu, int count) {
        assertThatThrownBy(() -> new GiftMenu(menu, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getMenuAssertThat(Menu menu, Menu expected) {
        assertThat(new GiftMenu(menu, 1).getMenu()).isEqualTo(expected);
    }

    private void getCountAssertThat(int count, int expected) {
        assertThat(new GiftMenu(CHAMPAGNE, count).getCount()).isEqualTo(expected);
    }

    private void getAmountAssertThat(Menu menu, int count, int expected) {
        assertThat(new GiftMenu(menu, count).getAmount()).isEqualTo(expected);
    }

    private void toStringAssertThat(Menu menu, int count, String expected) {
        assertThat(new GiftMenu(menu, count).toString()).isEqualTo(expected);
    }

    @DisplayName("증정 메뉴가 샴페인 1개인 경우 결과 확인")
    @Test
    void createGiftMenuByOneChampagne() {
        getMenuAssertThat(CHAMPAGNE, CHAMPAGNE);
        getCountAssertThat(1, 1);
        getAmountAssertThat(CHAMPAGNE, 1, 25_000);
        toStringAssertThat(CHAMPAGNE, 1, "샴페인 1개");
    }

    @DisplayName("증정 메뉴가 샴페인 2개인 경우 결과 확인")
    @Test
    void createGiftMenuByTwoChampagne() {
        getMenuAssertThat(CHAMPAGNE, CHAMPAGNE);
        getCountAssertThat(2, 2);
        getAmountAssertThat(CHAMPAGNE, 2, 50_000);
        toStringAssertThat(CHAMPAGNE, 2, "샴페인 2개");
    }

    @DisplayName("증정 메뉴가 제로콜라 2개인 경우 결과 확인")
    @Test
    void createGiftMenuByTwoZeroCoke() {
        getMenuAssertThat(ZERO_COKE, ZERO_COKE);
        getCountAssertThat(2, 2);
        getAmountAssertThat(ZERO_COKE, 2, 6_000);
        toStringAssertThat(ZERO_COKE, 2, "제로콜라 2개");
    }

    @DisplayName("증정 개수가 0인 경우 예외 발생")
    @Test
    void createGiftMenuByZeroCount() {
        createGiftAssertThatThrownBy(CHAMPAGNE, 0);
    }

    @DisplayName("증정 개수가 음수인 경우 예외 발생")
    @Test
    void createGiftMenuByNegativeCount() {
        createGiftAssertThatThrownBy(CHAMPAGNE, -1);
    }
}
