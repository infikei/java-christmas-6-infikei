package christmas.constant;

public enum DayOfWeek {
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(0),
    FRIDAY(1),
    SATURDAY(2);

    private final int remainderOfDecemberDate;

    private DayOfWeek(int remainderOfDecemberDate) {
        this.remainderOfDecemberDate = remainderOfDecemberDate;
    }

    public static DayOfWeek decemberDateOf(int decemberDate) {
        for (DayOfWeek dayOfWeek : values()) {
            if (dayOfWeek.remainderOfDecemberDate == decemberDate % 7) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException();
    }
}
