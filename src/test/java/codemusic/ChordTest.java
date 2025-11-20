package codemusic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChordTest {

    @DisplayName("긴 키워드가 짧은 키워드(Prefix)보다 먼저 인식되어야 한다.")
    @ParameterizedTest
    @CsvSource({
            "interface, INTERFACE", // interface가 int보다 먼저 잡혀야 함
            "int, INT",
            "throws, THROWS",       // throws가 throw보다 먼저 잡혀야 함
            "throw, THROW"
    })
    void findByKeyword_Priority_Test(String input, Chord expectedChord) {
        // when
        Chord result = Chord.findByKeyword(input);

        // then
        assertThat(result).isEqualTo(expectedChord);
    }

    @DisplayName("키워드가 없으면 null을 반환한다")
    @Test
    void findByKeyword_Null_Test() {
        assertThat(Chord.findByKeyword("hello")).isNull();
    }
}