package christmas.model;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

public class GiftsGenerator {
    private static final int MINIMUM_PRICE_FOR_CHAMPAGNE_GIFT = 120_000;
    private static final int CHAMPAGNE_GIFT_ADD_COUNT = 1;

    private final List<Order> gifts;

    public GiftsGenerator(Orders orders) {
        gifts = new ArrayList<>();
        addChampagneGift(orders);
    }

    private void addChampagneGift(Orders orders) {
        if (orders.getPriceSum() >= MINIMUM_PRICE_FOR_CHAMPAGNE_GIFT) {
            addGift(Menu.CHAMPAGNE, CHAMPAGNE_GIFT_ADD_COUNT);
        }
    }

    private void addGift(Menu menu, int count) {
        gifts.add(new Order(menu, count));
    }

    public List<Order> getGifts() {
        return gifts;
    }
}
