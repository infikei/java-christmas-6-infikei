package christmas.constant;

public enum ExceptionType {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDERS("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String MESSAGE_PREFIX = "[ERROR] ";

    private final String message;

    private ExceptionType(String message) {
        this.message = MESSAGE_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
