package christmas.model;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

public class Gifts {
    private final List<Gift> gifts;

    public Gifts() {
        gifts = new ArrayList<>();
    }

    public void add(Menu menu, int count) {
        gifts.add(new Gift(menu, count));
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public int getPriceSum() {
        return gifts.stream()
                .mapToInt(Gift::getPriceSum)
                .sum();
    }

    public int getCountSum() {
        return gifts.stream()
                .mapToInt(Gift::getCount)
                .sum();
    }
}
