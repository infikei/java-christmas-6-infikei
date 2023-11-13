package christmas.controller;

import christmas.model.Date;
import christmas.model.Orders;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        generatePlanner();
        generatePlannerResult();
        printPlannerResult();
    }

    private void generatePlanner() {
        outputView.printPlannerStart();
        plannerService.saveDate(readDate());
        plannerService.saveOrders(readOrders());
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

    private void generatePlannerResult() {
        plannerService.generatePlannerResult();
    }

    private void printPlannerResult() {
        //
    }
}
