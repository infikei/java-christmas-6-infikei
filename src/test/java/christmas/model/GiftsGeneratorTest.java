package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constant.Menu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

class GiftsGeneratorTest {
    @DisplayName("주문 금액이 120,000원인 경우 샴페인 추가")
    @Test
    void generateGiftsBy120000() {
        List<GiftMenu> giftMenus = GiftsGenerator.generate(120_000);
        assertThat(giftMenus.size()).isEqualTo(1);
        assertThat(giftMenus.get(0).getMenu()).isEqualTo(CHAMPAGNE);
        assertThat(giftMenus.get(0).getCount()).isEqualTo(1);
    }

    @DisplayName("주문 금액이 200,000원인 경우 샴페인 추가")
    @Test
    void generateGiftsBy200000() {
        List<GiftMenu> giftMenus = GiftsGenerator.generate(200_000);
        assertThat(giftMenus.size()).isEqualTo(1);
        assertThat(giftMenus.get(0).getMenu()).isEqualTo(CHAMPAGNE);
        assertThat(giftMenus.get(0).getCount()).isEqualTo(1);
    }

    @DisplayName("주문 금액이 119,999원인 경우 증정 메뉴 없음")
    @Test
    void generateGiftsBy119999() {
        List<GiftMenu> giftMenus = GiftsGenerator.generate(119_999);
        assertThat(giftMenus.size()).isEqualTo(0);
    }

    @DisplayName("주문 금액이 3,000원인 경우 증정 메뉴 없음")
    @Test
    void generateGiftsBy3000() {
        List<GiftMenu> giftMenus = GiftsGenerator.generate(3_000);
        assertThat(giftMenus.size()).isEqualTo(0);
    }
}
