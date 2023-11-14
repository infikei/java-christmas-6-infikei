package christmas.service;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.domain.Date;
import christmas.domain.Gift;
import christmas.domain.Gifts;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.model.EventsGenerator;
import christmas.model.GiftsGenerator;

import java.util.List;
import java.util.Map;

public class PlannerService {
    private Date date;
    private Orders orders;
    private Gifts gifts;
    private Map<Event, Integer> events;
    private Badge badge;

    public void saveDate(Date date) {
        this.date = date;
    }

    public void saveOrders(Orders orders) {
        this.orders = orders;
    }

    public void generatePlannerResult() {
        generateGifts();
        generateEvents();
        generateBadge();
    }

    private void generateGifts() {
        gifts = new GiftsGenerator(orders).getGifts();
    }

    private void generateEvents() {
        events = new EventsGenerator(date, orders, gifts).getEvents();
    }

    private void generateBadge() {
        badge = Badge.getBadgeBySaleSum(getEventsSaleSum());
    }

    public int getDate() {
        return date.getDate();
    }

    public List<Order> getOrders() {
        return orders.getOrders();
    }

    public List<Gift> getGifts() {
        return gifts.getGifts();
    }

    public Map<Event, Integer> getEvents() {
        return events;
    }

    public Badge getBadge() {
        return badge;
    }

    public int getOrdersPriceSum() {
        return orders.getPriceSum();
    }

    public int getGiftsPriceSum() {
        return gifts.getPriceSum();
    }

    public int getEventsSaleSum() {
        return events.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getResultPriceSum() {
        return getOrdersPriceSum() + getGiftsPriceSum() - getEventsSaleSum();
    }
}
