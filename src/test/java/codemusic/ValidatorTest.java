package codemusic;

import static org.assertj.core.api.Assertions.assertThatCode;
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

    @DisplayName("정상적인 문자가 들어오면 통과한다.")
    @Test
    void 정상적인_문자가_들어오면_통과() {
        assertThatCode(() -> Validator.validateCodeNotEmpty("abc"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("템포가 1~9 범위를 벗어나면 예외가 발생한다.")
    void 템포범위_1부터_9까지_벗어나면_예외_발생() {
        assertThatThrownBy(() -> Validator.validateTempo("10"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateTempo("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }


}