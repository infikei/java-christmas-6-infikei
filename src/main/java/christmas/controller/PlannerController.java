package christmas.controller;

import christmas.model.Date;
import christmas.model.OrderMenus;
import christmas.model.PlannerResult;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        printStart();
        Date date = readDate();
        OrderMenus orderMenus = readOrderMenus();
        generateAndPrintResult(date, orderMenus);
    }

    private void printStart() {
        outputView.printPlannerStart();
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

    private OrderMenus readOrderMenus() {
        while (true) {
            try {
                return new OrderMenus(inputView.readOrderMenus());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void generateAndPrintResult(Date date, OrderMenus orderMenus) {
        PlannerResult result = generateResult(date, orderMenus);
        printResult(date, orderMenus, result);
    }

    private PlannerResult generateResult(Date date, OrderMenus orderMenus) {
        return new PlannerResult(date, orderMenus);
    }

    private void printResult(Date date, OrderMenus orderMenus, PlannerResult result) {
        printResultStart(date);
        printOrderMenus(orderMenus);
        printOrderMenusAmount(orderMenus);
        printGiftMenus(result);
        printEvents(result);
        printEventsDiscount(result);
        printResultAmount(orderMenus, result);
        printBadge(result);
    }

    private void printResultStart(Date date) {
        outputView.printPlannerResultStart(date.getDate());
        outputView.printEmptyLine();
    }

    private void printOrderMenus(OrderMenus orderMenus) {
        outputView.printOrderMenus(orderMenus.getOrderMenus());
        outputView.printEmptyLine();
    }

    private void printOrderMenusAmount(OrderMenus orderMenus) {
        outputView.printOrderMenusAmount(orderMenus.getAmount());
        outputView.printEmptyLine();
    }

    private void printGiftMenus(PlannerResult result) {
        outputView.printGiftMenus(result.getGiftMenus());
        outputView.printEmptyLine();
    }

    private void printEvents(PlannerResult result) {
        outputView.printEvents(result.getEvents());
        outputView.printEmptyLine();
    }

    private void printEventsDiscount(PlannerResult result) {
        outputView.printTotalDiscount(result.getEventsDiscount());
        outputView.printEmptyLine();
    }

    private void printResultAmount(OrderMenus orderMenus, PlannerResult result) {
        int resultAmount = orderMenus.getAmount() + result.getGiftsAmount() - result.getEventsDiscount();
        outputView.printResultAmount(resultAmount);
        outputView.printEmptyLine();
    }

    private void printBadge(PlannerResult result) {
        outputView.printBadge(result.getBadge());
        outputView.printEmptyLine();
    }
}
