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

    @DisplayName("문자 'z'는 쉼표(REST)로 변환되어야 한다.")
    @Test
    void Z는_쉼표로_처리() {
        Note note = Note.findByChar('z');
        assertThat(note.getRightHandNote()).isEqualTo("REST");
    }

    @DisplayName("특수문자나 공백도 쉼표(REST)로 처리된다.")
    @Test
    void 특수문자나_공백도_쉼표로_처리() {
        assertThat(Note.findByChar('!').getRightHandNote()).isEqualTo("REST");
        assertThat(Note.findByChar(' ').getRightHandNote()).isEqualTo("REST");
    }

    @DisplayName("왼손 베이스 음은 오른손보다 2옥타브 낮아야 한다.")
    @Test
    void getLeftHandNote_Test() {
        Note note = Note.findByChar('a');

        assertThat(note.getLeftHandNote()).isEqualTo("C2");
        assertThat(note.getLeftHandMidiNumber()).isEqualTo(36);
    }
}