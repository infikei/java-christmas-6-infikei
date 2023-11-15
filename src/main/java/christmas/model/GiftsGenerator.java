package christmas.model;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

public class GiftsGenerator {
    private static final int MINIMUM_PRICE_SUM_FOR_EVENT = 10_000;
    private static final int MINIMUM_PRICE_SUM_FOR_CHAMPAGNE = 120_000;
    private static final int CHAMPAGNE_ADD_COUNT = 1;

    private final List<Gift> gifts;

    public GiftsGenerator(int priceSum) {
        gifts = new ArrayList<>();

        if (priceSum >= MINIMUM_PRICE_SUM_FOR_EVENT) {
            addChampagneGift(priceSum);
        }
    }

    private void addChampagneGift(int priceSum) {
        if (priceSum >= MINIMUM_PRICE_SUM_FOR_CHAMPAGNE) {
            addGift(Menu.CHAMPAGNE, CHAMPAGNE_ADD_COUNT);
        }
    }

    private void addGift(Menu menu, int count) {
        gifts.add(new Gift(menu, count));
    }

    public List<Gift> getGifts() {
        return gifts;
    }
}
