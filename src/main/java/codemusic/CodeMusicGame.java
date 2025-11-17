package codemusic;

import java.util.ArrayList;
import java.util.List;

public class CodeMusicGame {
    private final InputView inputView;
    private final OutputView outputView;

    public CodeMusicGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        String rightCode = inputView.getRightHandCode();
        String leftCode = inputView.getLeftHandCode();
        outputView.printStartMessage();

        List<String> rightNotes = new ArrayList<>();
        List<String> leftNotes = new ArrayList<>();

        int leftHandIndex = 0;

        while (!rightCode.isEmpty()) {
            char leftOneLetter = leftCode.charAt(leftHandIndex % leftCode.length());
            Note leftNoteEnum = Note.findByChar(leftOneLetter);
            leftNotes.add(leftNoteEnum.getLeftHandNote());

            Chord matchedChord = Chord.findByKeyword(rightCode);

            if (matchedChord != null) {
                rightNotes.add(matchedChord.getChordNotes());
                rightCode = rightCode.substring(matchedChord.getKeyword().length());
            } else {
                char rightOneLetter = rightCode.charAt(0);
                Note rightNoteEnum = Note.findByChar(rightOneLetter);
                rightNotes.add(rightNoteEnum.getRightHandNote());
                rightCode = rightCode.substring(1);
            }
            leftHandIndex++;
        }
        outputView.printScore(rightNotes, leftNotes);
    }
}