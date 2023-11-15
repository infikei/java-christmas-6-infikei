package christmas.controller;

import christmas.model.Date;
import christmas.model.Orders;
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
        Orders orders = readOrders();
        generateAndPrintResult(date, orders);
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

    private Orders readOrders() {
        while (true) {
            try {
                return new Orders(inputView.readOrders());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private void generateAndPrintResult(Date date, Orders orders) {
        PlannerResult result = generateResult(date, orders);
        printResult(date, orders, result);
    }

    private PlannerResult generateResult(Date date, Orders orders) {
        return new PlannerResult(date, orders);
    }

    private void printResult(Date date, Orders orders, PlannerResult result) {
        printResultStart(date);
        printOrders(orders);
        printOrdersPriceSum(orders);
        printGifts(result);
        printEvents(result);
        printEventsSaleSum(result);
        printResultPriceSum(orders, result);
        printBadge(result);
    }

    private void printResultStart(Date date) {
        outputView.printPlannerResultStart(date.getDate());
        outputView.printEmptyLine();
    }

    private void printOrders(Orders orders) {
        outputView.printOrders(orders.getOrders());
        outputView.printEmptyLine();
    }

    private void printOrdersPriceSum(Orders orders) {
        outputView.printOrdersPriceSum(orders.getPriceSum());
        outputView.printEmptyLine();
    }

    private void printGifts(PlannerResult result) {
        outputView.printGifts(result.getGifts());
        outputView.printEmptyLine();
    }

    private void printEvents(PlannerResult result) {
        outputView.printEvents(result.getEvents());
        outputView.printEmptyLine();
    }

    private void printEventsSaleSum(PlannerResult result) {
        outputView.printEventsSaleSum(result.getEventsSaleSum());
        outputView.printEmptyLine();
    }

    private void printResultPriceSum(Orders orders, PlannerResult result) {
        int resultPriceSum = orders.getPriceSum() + result.getGiftsPriceSum() - result.getEventsSaleSum();
        outputView.printResultPriceSum(resultPriceSum);
        outputView.printEmptyLine();
    }

    private void printBadge(PlannerResult result) {
        outputView.printBadge(result.getBadge());
        outputView.printEmptyLine();
    }
}
