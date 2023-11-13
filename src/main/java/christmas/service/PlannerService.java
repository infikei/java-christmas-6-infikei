package christmas.service;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.model.Date;
import christmas.model.EventsGenerator;
import christmas.model.GiftsGenerator;
import christmas.model.Order;
import christmas.model.Orders;

import java.util.List;
import java.util.Map;

public class PlannerService {
    private Date date;
    private Orders orders;
    private List<Order> gifts;
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
        int salePriceSum = events.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        badge = Badge.getBadgeByPrice(salePriceSum);
    }
}
