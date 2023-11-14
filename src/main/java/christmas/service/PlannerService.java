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
        badge = Badge.getBadgeByPrice(getEventsSalePriceSum());
    }

    public int getDate() {
        return date.getDate();
    }

    public List<Order> getOrders() {
        return orders.getOrders();
    }

    public List<Order> getGifts() {
        return gifts;
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
        return gifts.stream()
                .mapToInt(Order::getPriceSum)
                .sum();
    }

    public int getEventsSalePriceSum() {
        return events.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getResultPriceSum() {
        return getOrdersPriceSum() + getGiftsPriceSum() - getEventsSalePriceSum();
    }
}
