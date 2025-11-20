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

    @DisplayName("키워드(public)가 입력되면 화음 1개로 변환되고 인덱스는 스킵된다.")
    @Test
    void keyword_Skip_Test() {
        // given
        CodeMusicGame game = new CodeMusicGame(null, null);
        String rightCode = "public"; // 6글자지만 화음 1개
        String leftCode = "a";

        // when
        CodeMusicGame.MusicScore score = game.createMusicScore(rightCode, leftCode);

        // then
        // 1. 생성된 음표는 딱 1개여야 함 (6개가 아님!)
        assertThat(score.rightNotes).hasSize(1);

        // 2. 단음이 아니라 화음 형태(하이픈 포함)여야 함
        assertThat(score.rightNotes.get(0)).contains("-");
    }

    @DisplayName("왼손 대소문자에 따라 다이나믹(p, f)이 결정된다.")
    @Test
    void dynamic_Test() {
        // given
        CodeMusicGame game = new CodeMusicGame(null, null);
        String rightCode = "ab";
        String leftCode = "aA"; // 소문자(p), 대문자(f)

        // when
        CodeMusicGame.MusicScore score = game.createMusicScore(rightCode, leftCode);

        // then
        assertThat(score.dynamics).containsExactly("p", "f");
    }
}