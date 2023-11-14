package christmas.service;

import christmas.domain.Date;
import christmas.domain.Orders;
import christmas.domain.PlannerResult;

public class PlannerService {
    public PlannerResult generatePlannerResult(Date date, Orders orders) {
        return new PlannerResult(date, orders);
    }
}
