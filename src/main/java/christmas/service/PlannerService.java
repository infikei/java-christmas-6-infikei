package christmas.service;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.model.Date;
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
}
