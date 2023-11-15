package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTest {
    private void createDateAssertThatThrownBy(int date) {
        assertThatThrownBy(() -> new Date(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void getDateAssertThat(int date, int expected) {
        assertThat(new Date(date).getDate()).isEqualTo(expected);
    }

    @DisplayName("날짜가 0인 경우 예외 발생")
    @Test
    void createDateByZero() {
        createDateAssertThatThrownBy(0);
    }

    @DisplayName("날짜가 음수인 경우 예외 발생")
    @Test
    void createDateByNegativeInteger() {
        createDateAssertThatThrownBy(-1);
    }

    @DisplayName("날짜가 32인 경우 예외 발생")
    @Test
    void createDateBy32() {
        createDateAssertThatThrownBy(32);
    }

    @DisplayName("날짜가 1인 경우 결과 확인")
    @Test
    void createDateBy1() {
        getDateAssertThat(1, 1);
    }

    @DisplayName("날짜가 2인 경우 결과 확인")
    @Test
    void createDateBy2() {
        getDateAssertThat(2, 2);
    }

    @DisplayName("날짜가 25인 경우 결과 확인")
    @Test
    void createDateBy25() {
        getDateAssertThat(25, 25);
    }

    @DisplayName("날짜가 30인 경우 결과 확인")
    @Test
    void createDateBy30() {
        getDateAssertThat(30, 30);
    }

    @DisplayName("날짜가 31인 경우 결과 확인")
    @Test
    void createDateBy31() {
        getDateAssertThat(31, 31);
    }
}
