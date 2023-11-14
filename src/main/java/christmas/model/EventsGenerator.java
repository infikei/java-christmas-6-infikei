package christmas.model;

import christmas.constant.DayOfWeek;
import christmas.constant.Event;
import christmas.constant.MenuCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsGenerator {
    private static final int MINIMUM_PRICE_SUM_FOR_EVENT = 10_000;
    private static final int CHRISTMAS_D_DAY_BEGIN = 1;
    private static final int CHRISTMAS_D_DAY_END = 25;
    private static final List<DayOfWeek> HOLIDAYS = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int WEEKDAY_SALE_PRICE_PER_MENU = 2_023;
    private static final int HOLIDAY_SALE_PRICE_PER_MENU = 2_023;
    private static final MenuCategory WEEKDAY_CATEGORY = MenuCategory.DESSERT;
    private static final MenuCategory HOLIDAY_CATEGORY = MenuCategory.MAIN;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_SALE_PRICE = 1_000;

    private final Map<Event, Integer> events;

    public EventsGenerator(Date date, Orders orders, List<Order> gifts) {
        events = new HashMap<>();

        if (orders.getPriceSum() >= MINIMUM_PRICE_SUM_FOR_EVENT) {
            addChristmasDDayEvent(date);
            addWeekdayEvent(date, orders);
            addHolidayEvent(date, orders);
            addSpecialEvent(date);
            addGiftEvent(gifts);
        }
    }

    private void addChristmasDDayEvent(Date date) {
        if (isInRangeForChristmasDDay(date)) {
            addEvent(Event.CHRISTMAS_D_DAY, getSalePriceForChristmasDDay(date));
        }
    }

    private boolean isInRangeForChristmasDDay(Date date) {
        return (date.getDate() >= CHRISTMAS_D_DAY_BEGIN) && (date.getDate() <= CHRISTMAS_D_DAY_END);
    }

    private int getSalePriceForChristmasDDay(Date date) {
        return 900 + date.getDate() * 100;
    }

    private void addWeekdayEvent(Date date, Orders orders) {
        if (!isHoliday(date)) {
            addEvent(Event.WEEKDAY, getSalePriceForWeekday(orders));
        }
    }

    private void addHolidayEvent(Date date, Orders orders) {
        if (isHoliday(date)) {
            addEvent(Event.HOLIDAY, getSalePriceForHoliday(orders));
        }
    }

    private int getSalePriceForWeekday(Orders orders) {
        return WEEKDAY_SALE_PRICE_PER_MENU * orders.getCountSumOfCategory(WEEKDAY_CATEGORY);
    }

    private int getSalePriceForHoliday(Orders orders) {
        return HOLIDAY_SALE_PRICE_PER_MENU * orders.getCountSumOfCategory(HOLIDAY_CATEGORY);
    }

    private boolean isHoliday(Date date) {
        for (DayOfWeek holiday : HOLIDAYS) {
            if (DayOfWeek.dateOf(date.getDate()) == holiday) {
                return true;
            }
        }
        return false;
    }

    private void addSpecialEvent(Date date) {
        if (isSpecialDay(date)) {
            addEvent(Event.SPECIAL, SPECIAL_SALE_PRICE);
        }
    }

    private boolean isSpecialDay(Date date) {
        long filterCount = SPECIAL_DAYS.stream()
                .filter(specialDay -> specialDay == date.getDate())
                .count();

        return filterCount != 0L;
    }

    private void addGiftEvent(List<Order> gifts) {
        if (!gifts.isEmpty()) {
            addEvent(Event.GIFT, getGiftsPriceSum(gifts));
        }
    }

    private int getGiftsPriceSum(List<Order> gifts) {
        return gifts.stream()
                .mapToInt(Order::getPriceSum)
                .sum();
    }

    private void addEvent(Event event, int salePrice) {
        events.put(event, salePrice);
    }

    public Map<Event, Integer> getEvents() {
        return events;
    }
}
