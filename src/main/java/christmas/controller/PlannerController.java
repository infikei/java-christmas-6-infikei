package christmas.controller;

import christmas.constant.Badge;
import christmas.constant.Event;
import christmas.domain.Date;
import christmas.domain.Gift;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.PlannerResult;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PlannerService plannerService;

    public PlannerController() {
        inputView = new InputView();
        outputView = new OutputView();
        plannerService = new PlannerService();
    }

    public void start() {
        outputView.printPlannerStart();
        Date date = readDate();
        Orders orders = readOrders();
        PlannerResult plannerResult = plannerService.generatePlannerResult(date, orders);
        printPlannerResult(date, orders, plannerResult);
    }

    private Date readDate() {
        while (true) {
            try {
                return new Date(inputView.readDate());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private Orders readOrders() {
        while (true) {
            try {
                return new Orders(inputView.readOrders());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void printPlannerResult(Date date, Orders orders, PlannerResult plannerResult) {
        printPlannerResultStart(date.getDate());
        printOrders(orders.getOrders());
        printOrdersPriceSum(orders.getPriceSum());
        printGifts(plannerResult.getGifts().getGifts());
        printEvents(plannerResult.getEvents());
        printEventsSaleSum(plannerResult.getEventsSaleSum());
        printResultPriceSum(orders.getPriceSum() + plannerResult.getGiftsPriceSum() - plannerResult.getEventsSaleSum());
        printBadge(plannerResult.getBadge());
    }

    private void printPlannerResultStart(int date) {
        outputView.printPlannerResultStart(date);
        outputView.printEmptyLine();
    }

    private void printOrders(List<Order> orders) {
        outputView.printOrders(orders);
        outputView.printEmptyLine();
    }

    private void printOrdersPriceSum(int ordersPriceSum) {
        outputView.printOrdersPriceSum(ordersPriceSum);
        outputView.printEmptyLine();
    }

    private void printGifts(List<Gift> gifts) {
        outputView.printGifts(gifts);
        outputView.printEmptyLine();
    }

    private void printEvents(Map<Event, Integer> events) {
        outputView.printEvents(events);
        outputView.printEmptyLine();
    }

    private void printEventsSaleSum(int eventsSaleSum) {
        outputView.printEventsSaleSum(eventsSaleSum);
        outputView.printEmptyLine();
    }

    private void printResultPriceSum(int resultPriceSum) {
        outputView.printResultPriceSum(resultPriceSum);
        outputView.printEmptyLine();
    }

    private void printBadge(Badge badge) {
        outputView.printBadge(badge);
        outputView.printEmptyLine();
    }
}
