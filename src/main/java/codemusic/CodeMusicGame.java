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

        for (int i = 0; i < rightCode.length(); i++) {
            char rightChar = rightCode.charAt(i);
            char leftChar = leftCode.charAt(i % leftCode.length());

            Note rightNoteEnum = Note.findByChar(rightChar);
            Note leftNoteEnum = Note.findByChar(leftChar);

            String rightNote = rightNoteEnum.getRightHandNote();
            String leftNote = leftNoteEnum.getLeftHandNote();

            rightNotes.add(rightNote);
            leftNotes.add(leftNote);
        }
        outputView.printNoteList("오른손", rightNotes);
        outputView.printNoteList("왼손", leftNotes);
    }
}