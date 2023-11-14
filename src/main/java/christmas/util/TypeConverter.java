package christmas.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeConverter {
    public static int toInteger(String number) throws NumberFormatException {
        return Integer.parseInt(number);
    }

    public static List<String> splitExactly(String values, char separator) {
        validateIsNotEmpty(values);
        validateIsNotLastCharacterSeparator(values, separator);
        return new ArrayList<>(Arrays.asList(values.split(String.valueOf(separator))));
    }

    private static void validateIsNotEmpty(String values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsNotLastCharacterSeparator(String values, char separator) {
        if (values.charAt(values.length() - 1) == separator) {
            throw new IllegalArgumentException();
        }
    }

    public static List<List<String>> splitExactlyTwice(String values, char separator, char innerSeparator) {
        List<List<String>> valuesConverted = new ArrayList<>();

        for (String value : splitExactly(values, separator)) {
            valuesConverted.add(splitExactly(value, innerSeparator));
        }
        return valuesConverted;
    }

    public static Map<String, Integer> toMapOfIntegerValue(String values, char separator, char innerSeparator) {
        return toMapOfIntegerValue(toMap(values, separator, innerSeparator));
    }

    public static Map<String, Integer> toMapOfIntegerValue(Map<String, String> pairs) {
        Map<String, Integer> pairsConverted = new HashMap<>();

        for (Map.Entry<String, String> pair : pairs.entrySet()) {
            pairsConverted.put(pair.getKey(), toInteger(pair.getValue()));
        }
        return pairsConverted;
    }

    public static Map<String, String> toMap(String pairs, char separator, char innerSeparator) {
        return toMap(splitExactlyTwice(pairs, separator, innerSeparator));
    }

    public static Map<String, String> toMap(List<List<String>> pairs) {
        Map<String, String> pairsConverted = new HashMap<>();

        for (List<String> pair : pairs) {
            validateIsSizeTwo(pair);
            pairsConverted.put(pair.get(0), pair.get(1));
        }
        validateIsSizeSame(pairs, pairsConverted);
        return pairsConverted;
    }

    private static void validateIsSizeTwo(List<String> values) {
        if (values.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsSizeSame(List<List<String>> value, Map<String, String> other) {
        if (value.size() != other.size()) {
            throw new IllegalArgumentException();
        }
    }
}
