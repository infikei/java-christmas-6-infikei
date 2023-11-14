package christmas.domain;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.model.EventsGenerator;
import christmas.model.GiftsGenerator;

import java.util.List;
import java.util.Map;

public class PlannerResult {
    private final List<Gift> gifts;
    private final Map<Event, Integer> events;
    private final Badge badge;

    public PlannerResult(Date date, Orders orders) {
        this.gifts = new GiftsGenerator(orders).getGifts();
        this.events = new EventsGenerator(date, orders, getGiftsPriceSum()).getEvents();
        this.badge = Badge.getBadgeBySaleSum(getEventsSaleSum());
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public int getGiftsPriceSum() {
        return gifts.stream()
                .mapToInt(Gift::getPriceSum)
                .sum();
    }

    public Map<Event, Integer> getEvents() {
        return events;
    }

    public int getEventsSaleSum() {
        return events.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Badge getBadge() {
        return badge;
    }
}
