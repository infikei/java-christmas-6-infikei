package christmas.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTest {
    @Test
    void 날짜가_0인_경우_예외_발생() {
        assertThatThrownBy(() -> new Date(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 날짜가_음수인_경우_예외_발생() {
        assertThatThrownBy(() -> new Date(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 날짜가_32인_경우_예외_발생() {
        assertThatThrownBy(() -> new Date(32))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 날짜가_1인_경우_결과_확인() {
        assertThat(new Date(1).getDate()).isEqualTo(1);
    }

    @Test
    void 날짜가_31인_경우_결과_확인() {
        assertThat(new Date(31).getDate()).isEqualTo(31);
    }
}
