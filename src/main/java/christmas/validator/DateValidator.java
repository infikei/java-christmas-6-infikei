package christmas.validator;

import christmas.constant.ExceptionType;

public class DateValidator {
    private static final int DECEMBER_DATE_BEGIN = 1;
    private static final int DECEMBER_DATE_END = 31;

    public void validate(int date) {
        if (!isDecemberDate(date)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE.getMessage());
        }
    }

    private boolean isDecemberDate(int date) {
        return (date >= DECEMBER_DATE_BEGIN) && (date <= DECEMBER_DATE_END);
    }
}
