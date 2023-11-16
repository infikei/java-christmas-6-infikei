package christmas.model;

import christmas.constant.Badge;
import christmas.constant.EventType;

import java.util.List;
import java.util.Map;

public class PlannerResult {
    private final List<GiftMenu> giftMenus;
    private final Map<EventType, Integer> events;
    private final Badge badge;

    public PlannerResult(Date date, OrderMenus orderMenus) {
        this.giftMenus = GiftsGenerator.generate(orderMenus.getAmount());
        this.events = EventsGenerator.generate(date, orderMenus, getGiftsAmount());
        this.badge = Badge.saleSumOf(getEventsDiscount());
    }

    public List<GiftMenu> getGiftMenus() {
        return giftMenus;
    }

    public int getGiftsAmount() {
        return giftMenus.stream()
                .mapToInt(GiftMenu::getAmount)
                .sum();
    }

    public Map<EventType, Integer> getEvents() {
        return events;
    }

    public int getEventsDiscount() {
        return events.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Badge getBadge() {
        return badge;
    }
}
