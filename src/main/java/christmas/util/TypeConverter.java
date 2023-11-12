package christmas.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeConverter {
    public static int toInteger(String number) throws NumberFormatException {
        return Integer.parseInt(number);
    }

    public static List<String> splitExactly(String values, char separator) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (values.charAt(values.length() - 1) == separator) {
            throw new IllegalArgumentException();
        }
        return new ArrayList<>(Arrays.asList(values.split(String.valueOf(separator))));
    }
}
