package christmas.validator;

import christmas.constant.ExceptionType;
import christmas.util.TypeConverter;

public class InputValidator {
    public int validateReadDate(String date) {
        try {
            return TypeConverter.toInteger(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionType.INVALID_DATE.getMessage());
        }
    }
}
