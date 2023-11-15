package christmas.model;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GiftsGeneratorTest {
    @DisplayName("주문 금액이 120,000원인 경우 샴페인 추가")
    @Test
    void generateGiftsBy120000() {
        List<Gift> gifts = new GiftsGenerator(120_000).getGifts();
        assertThat(gifts.size()).isEqualTo(1);
        assertThat(gifts.get(0).getMenu()).isEqualTo(Menu.CHAMPAGNE);
        assertThat(gifts.get(0).getCount()).isEqualTo(1);
    }

    @DisplayName("주문 금액이 200,000원인 경우 샴페인 추가")
    @Test
    void generateGiftsBy200000() {
        List<Gift> gifts = new GiftsGenerator(200_000).getGifts();
        assertThat(gifts.size()).isEqualTo(1);
        assertThat(gifts.get(0).getMenu()).isEqualTo(Menu.CHAMPAGNE);
        assertThat(gifts.get(0).getCount()).isEqualTo(1);
    }

    @DisplayName("주문 금액이 119,999원인 경우 증정 메뉴 없음")
    @Test
    void generateGiftsBy119999() {
        List<Gift> gifts = new GiftsGenerator(119_999).getGifts();
        assertThat(gifts.size()).isEqualTo(0);
    }

    @DisplayName("주문 금액이 3,000원인 경우 증정 메뉴 없음")
    @Test
    void generateGiftsBy3000() {
        List<Gift> gifts = new GiftsGenerator(3_000).getGifts();
        assertThat(gifts.size()).isEqualTo(0);
    }
}
