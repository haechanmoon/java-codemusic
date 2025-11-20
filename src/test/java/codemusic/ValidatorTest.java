package codemusic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @DisplayName("빈 문자열이 들어오면 예외를 던진다.")
    @Test
    void 빈_문자열_들어오면_예외_던짐() {
        assertThatThrownBy(() -> Validator.validateCodeNotEmpty(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("정상적인 무자가 들어오면 통과한다.")
    @Test
    void 정상적인_문자가_들어오면_예외_던짐() {
        assertThatThrownBy(() -> Validator.validateCodeNotEmpty("abc"))
                .doesNotThrowAnyException();
    }
}