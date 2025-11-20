package codemusic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeMusicGameTest {

    @DisplayName("오른손 코드가 길면 왼손 코드는 반복(Loop)되어야 한다.")
    @Test
    void 반복_테스트() {
        //given
        CodeMusicGame game = new CodeMusicGame(null, null);
        String rightCode = "abcde";
        String leftCode = "fg";

        //when
        CodeMusicGame.MusicScore score = game.createMusicScore(rightCode, leftCode);

        //then
        assertThat(score.leftNotes).hasSize(5);
        assertThat(score.leftNotes).containsExactly("F2", "F#2", "F2", "F#2", "F2");
    }
}