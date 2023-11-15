package christmas.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TypeConverterTest {
    @Test
    void toIntegerTest() {
        toIntegerAssertThat("1", 1);
        toIntegerAssertThat("0001", 1);
        toIntegerAssertThat("-1", -1);
        toIntegerAssertThat("+1", 1);
        toIntegerAssertThat("0", 0);
        toIntegerAssertThat("-0", 0);
        toIntegerAssertThat("+0", 0);
    }

    private void toIntegerAssertThat(String number, int expected) {
        assertThat(TypeConverter.toInteger(number)).isEqualTo(expected);
    }

    @Test
    void toIntegerThrowExceptionTest() {
        toIntegerAssertThatThrownBy("");
        toIntegerAssertThatThrownBy(" ");
        toIntegerAssertThatThrownBy("abc");
        toIntegerAssertThatThrownBy("1.0");
        toIntegerAssertThatThrownBy("1.1");
        toIntegerAssertThatThrownBy(" 1");
        toIntegerAssertThatThrownBy("     1");
        toIntegerAssertThatThrownBy("1 ");
        toIntegerAssertThatThrownBy("1     ");
        toIntegerAssertThatThrownBy("  1  ");
        toIntegerAssertThatThrownBy("1 2");
    }

    private void toIntegerAssertThatThrownBy(String number) {
        assertThatThrownBy(() -> TypeConverter.toInteger(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void splitExactlyTest() {
        assertThat(TypeConverter.splitExactly("a,b,c", ','))
                .containsExactly("a", "b", "c");
        assertThat(TypeConverter.splitExactly(",a,b,c", ','))
                .containsExactly("", "a", "b", "c");
        assertThat(TypeConverter.splitExactly("010-123-456", '-'))
                .containsExactly("010", "123", "456");
    }

    @Test
    void splitExactlyThrowExceptionTest() {
        assertThatThrownBy(() -> TypeConverter.splitExactly("", ','))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TypeConverter.splitExactly("a,b,c,", ','))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TypeConverter.splitExactly(",a,b,c,", ','))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toMapOfIntegerValueTest() {
        assertThat(TypeConverter.toMapOfIntegerValue("abc-1,def-3,ghi-2", ',', '-'))
                .containsOnlyKeys("abc", "def", "ghi")
                .containsValues(1, 3, 2);
        assertThat(TypeConverter.toMapOfIntegerValue("abc-2", ',', '-'))
                .containsOnlyKeys("abc")
                .containsValues(2);
        assertThat(TypeConverter.toMapOfIntegerValue("abc-0", ',', '-'))
                .containsOnlyKeys("abc")
                .containsValues(0);
    }

    @Test
    void toMapOfIntegerValueThrowExceptionTest() {
        toMapOfIntegerValueAssertThatThrownBy("abc-1,def-3,", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy(",abc-1,def-3", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy(",abc-1,def-3,", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("abc-0.1", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("abc-k", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("abc-def-1", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("abc-2-1", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("abc--1", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("a-", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("a,b", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("1,2", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("a", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("1", ',', '-');
        toMapOfIntegerValueAssertThatThrownBy("0", ',', '-');
    }

    private void toMapOfIntegerValueAssertThatThrownBy(String values, char separator, char innerSeparator) {
        assertThatThrownBy(() -> TypeConverter.toMapOfIntegerValue(values, separator, innerSeparator))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
