package christmas.model;

import christmas.constant.DayOfWeek;
import christmas.constant.EventType;
import christmas.constant.MenuCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.DayOfWeek.FRIDAY;
import static christmas.constant.DayOfWeek.MONDAY;
import static christmas.constant.DayOfWeek.SATURDAY;
import static christmas.constant.DayOfWeek.SUNDAY;
import static christmas.constant.DayOfWeek.THURSDAY;
import static christmas.constant.DayOfWeek.TUESDAY;
import static christmas.constant.DayOfWeek.WEDNESDAY;
import static christmas.constant.EventType.CHRISTMAS_D_DAY_EVENT;
import static christmas.constant.EventType.GIFT_EVENT;
import static christmas.constant.EventType.HOLIDAY_EVENT;
import static christmas.constant.EventType.WEEKDAY_EVENT;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.MAIN;

public class EventsGenerator {
    private static final int MINIMUM_AMOUNT = 10_000;
    private static final int CHRISTMAS_D_DAY_BEGIN = 1;
    private static final int CHRISTMAS_D_DAY_END = 25;
    private static final List<DayOfWeek> WEEKDAYS = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final MenuCategory WEEKDAY_CATEGORY = DESSERT;
    private static final int WEEKDAY_DISCOUNT_PER_MENU = 2_023;
    private static final List<DayOfWeek> HOLIDAYS = List.of(FRIDAY, SATURDAY);
    private static final MenuCategory HOLIDAY_CATEGORY = MAIN;
    private static final int HOLIDAY_DISCOUNT_PER_MENU = 2_023;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DISCOUNT = 1_000;

    private final Map<EventType, Integer> events;

    public static Map<EventType, Integer> generate(Date date, OrderMenus orderMenus, int giftsAmount) {
        return new EventsGenerator(date, orderMenus, giftsAmount).getEvents();
    }

    private EventsGenerator(Date date, OrderMenus orderMenus, int giftsAmount) {
        events = new HashMap<>();

        if (orderMenus.getAmount() >= MINIMUM_AMOUNT) {
            addChristmasDDayEvent(date);
            addWeekdayEvent(date, orderMenus);
            addHolidayEvent(date, orderMenus);
            addSpecialEvent(date);
            addGiftEvent(giftsAmount);
        }
    }

    private void addChristmasDDayEvent(Date date) {
        if (isInRangeForChristmasDDay(date)) {
            addEvent(CHRISTMAS_D_DAY_EVENT, getDiscountForChristmasDDay(date));
        }
    }

    private boolean isInRangeForChristmasDDay(Date date) {
        return (date.getDate() >= CHRISTMAS_D_DAY_BEGIN) && (date.getDate() <= CHRISTMAS_D_DAY_END);
    }

    private int getDiscountForChristmasDDay(Date date) {
        return 900 + date.getDate() * 100;
    }

    private void addWeekdayEvent(Date date, OrderMenus orderMenus) {
        if (isWeekday(date)) {
            addEvent(WEEKDAY_EVENT, getSalePriceForWeekday(orderMenus));
        }
    }

    private boolean isWeekday(Date date) {
        for (DayOfWeek weekday : WEEKDAYS) {
            if (DayOfWeek.decemberDateOf(date.getDate()) == weekday) {
                return true;
            }
        }
        return false;
    }

    private int getSalePriceForWeekday(OrderMenus orderMenus) {
        return WEEKDAY_DISCOUNT_PER_MENU * orderMenus.getTotalCountOfCategory(WEEKDAY_CATEGORY);
    }

    private void addHolidayEvent(Date date, OrderMenus orderMenus) {
        if (isHoliday(date)) {
            addEvent(HOLIDAY_EVENT, getSalePriceForHoliday(orderMenus));
        }
    }

    private boolean isHoliday(Date date) {
        for (DayOfWeek holiday : HOLIDAYS) {
            if (DayOfWeek.decemberDateOf(date.getDate()) == holiday) {
                return true;
            }
        }
        return false;
    }

    private int getSalePriceForHoliday(OrderMenus orderMenus) {
        return HOLIDAY_DISCOUNT_PER_MENU * orderMenus.getTotalCountOfCategory(HOLIDAY_CATEGORY);
    }

    private void addSpecialEvent(Date date) {
        if (isSpecialDay(date)) {
            addEvent(EventType.SPECIAL_EVENT, SPECIAL_DISCOUNT);
        }
    }

    private boolean isSpecialDay(Date date) {
        long filterCount = SPECIAL_DAYS.stream()
                .filter(specialDay -> specialDay == date.getDate())
                .count();

        return filterCount != 0L;
    }

    private void addGiftEvent(int giftsAmount) {
        if (giftsAmount != 0) {
            addEvent(GIFT_EVENT, giftsAmount);
        }
    }

    private void addEvent(EventType eventType, int discount) {
        events.put(eventType, discount);
    }

    private Map<EventType, Integer> getEvents() {
        return events;
    }
}
