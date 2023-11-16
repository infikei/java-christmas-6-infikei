package christmas.model;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.Menu.CHAMPAGNE;

public class GiftsGenerator {
    private static final int MINIMUM_AMOUNT = 10_000;
    private static final int MINIMUM_AMOUNT_FOR_CHAMPAGNE = 120_000;
    private static final int CHAMPAGNE_ADD_COUNT = 1;

    private final List<GiftMenu> giftMenus;

    public static List<GiftMenu> generate(int giftMenusAmount) {
        return new GiftsGenerator(giftMenusAmount).getGiftMenus();
    }

    private GiftsGenerator(int giftMenusAmount) {
        giftMenus = new ArrayList<>();

        if (giftMenusAmount >= MINIMUM_AMOUNT) {
            addChampagneGift(giftMenusAmount);
        }
    }

    private void addChampagneGift(int giftMenusAmount) {
        if (giftMenusAmount >= MINIMUM_AMOUNT_FOR_CHAMPAGNE) {
            addGiftMenu(CHAMPAGNE, CHAMPAGNE_ADD_COUNT);
        }
    }

    private void addGiftMenu(Menu menu, int count) {
        giftMenus.add(new GiftMenu(menu, count));
    }

    private List<GiftMenu> getGiftMenus() {
        return giftMenus;
    }
}
