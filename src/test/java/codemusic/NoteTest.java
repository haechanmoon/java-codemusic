package codemusic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoteTest {

    @DisplayName("문자 'a'는 'C4'(MIDI 60)로 변환되어야 한다.")
    @Test
    void 문자_미디_매칭_성공() {
        // given
        char input = 'a';

        // when
        Note note = Note.findByChar(input);

        // then
        assertThat(note.getRightHandNote()).isEqualTo("C4");
        assertThat(note.getMidiNumber()).isEqualTo(60);
    }


}