package christmas.domain;

import christmas.constant.ExceptionType;

public class Date {
    private static final int DECEMBER_DATE_BEGIN = 1;
    private static final int DECEMBER_DATE_END = 31;

    private final int date;

    public Date(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (!isDecemberDate(date)) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE.getMessage());
        }
    }

    private boolean isDecemberDate(int date) {
        return (date >= DECEMBER_DATE_BEGIN) && (date <= DECEMBER_DATE_END);
    }

    public int getDate() {
        return date;
    }
}
