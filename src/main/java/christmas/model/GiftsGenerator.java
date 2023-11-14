package christmas.model;

import christmas.constant.Menu;
import christmas.domain.Gifts;
import christmas.domain.Orders;

public class GiftsGenerator {
    private static final int MINIMUM_PRICE_SUM_FOR_EVENT = 10_000;
    private static final int MINIMUM_PRICE_SUM_FOR_CHAMPAGNE = 120_000;
    private static final int CHAMPAGNE_ADD_COUNT = 1;

    private final Gifts gifts;

    public GiftsGenerator(Orders orders) {
        gifts = new Gifts();

        if (orders.getPriceSum() >= MINIMUM_PRICE_SUM_FOR_EVENT) {
            addChampagneGift(orders);
        }
    }

    private void addChampagneGift(Orders orders) {
        if (orders.getPriceSum() >= MINIMUM_PRICE_SUM_FOR_CHAMPAGNE) {
            gifts.add(Menu.CHAMPAGNE, CHAMPAGNE_ADD_COUNT);
        }
    }

    public Gifts getGifts() {
        return gifts;
    }
}
