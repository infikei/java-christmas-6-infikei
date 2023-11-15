package christmas.model;

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

    @Test
    void 날짜가_0인_경우_예외_발생() {
        createDateAssertThatThrownBy(0);
    }

    @Test
    void 날짜가_음수인_경우_예외_발생() {
        createDateAssertThatThrownBy(-1);
    }

    @Test
    void 날짜가_32인_경우_예외_발생() {
        createDateAssertThatThrownBy(32);
    }

    @Test
    void 날짜가_1인_경우_결과_확인() {
        getDateAssertThat(1, 1);
    }

    @Test
    void 날짜가_31인_경우_결과_확인() {
        getDateAssertThat(31, 31);
    }
}
