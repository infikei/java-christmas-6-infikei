package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypeConverterTest {
    private void toIntegerAssertThatThrownBy(String number) {
        assertThatThrownBy(() -> TypeConverter.toInteger(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void toIntegerAssertThat(String number, int expected) {
        assertThat(TypeConverter.toInteger(number)).isEqualTo(expected);
    }

    @DisplayName("toInteger 기능 결과 확인")
    @Test
    void toIntegerTest() {
        toIntegerAssertThat("1", 1);
        toIntegerAssertThat("0001", 1);
        toIntegerAssertThat("-1", -1);
        toIntegerAssertThat("+1", 1);
        toIntegerAssertThat("0", 0);
        toIntegerAssertThat("-0", 0);
        toIntegerAssertThat("+0", 0);
        toIntegerAssertThat("27", 27);
    }

    @DisplayName("toInteger 기능 예외 발생")
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
        toIntegerAssertThatThrownBy("+");
        toIntegerAssertThatThrownBy("-");
        toIntegerAssertThatThrownBy("*");
        toIntegerAssertThatThrownBy("/");
        toIntegerAssertThatThrownBy("*1");
        toIntegerAssertThatThrownBy("1+1");
        toIntegerAssertThatThrownBy("1-1");
        toIntegerAssertThatThrownBy("1*1");
        toIntegerAssertThatThrownBy("1/1");
        toIntegerAssertThatThrownBy("++1");
        toIntegerAssertThatThrownBy("+-1");
        toIntegerAssertThatThrownBy("-+1");
        toIntegerAssertThatThrownBy("--1");
    }

    @DisplayName("splitExactly 기능 결과 확인")
    @Test
    void splitExactlyTest() {
        assertThat(TypeConverter.splitExactly("a,b,c", ','))
                .containsExactly("a", "b", "c");
        assertThat(TypeConverter.splitExactly(",a,b,c", ','))
                .containsExactly("", "a", "b", "c");
        assertThat(TypeConverter.splitExactly("010-123-456", '-'))
                .containsExactly("010", "123", "456");
    }

    @DisplayName("splitExactly 기능 예외 발생")
    @Test
    void splitExactlyThrowExceptionTest() {
        assertThatThrownBy(() -> TypeConverter.splitExactly("", ','))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TypeConverter.splitExactly("a,b,c,", ','))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TypeConverter.splitExactly(",a,b,c,", ','))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void toMapOfIntegerValueAssertThatThrownBy(String values, char separator, char innerSeparator) {
        assertThatThrownBy(() -> TypeConverter.toMapOfIntegerValue(values, separator, innerSeparator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("toMapOfIntegerValue 기능 결과 확인")
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

    @DisplayName("toMapOfIntegerValue 기능 예외 발생")
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
}
