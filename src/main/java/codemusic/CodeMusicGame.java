package codemusic;

import java.util.ArrayList;
import java.util.List;

public class CodeMusicGame {
    private final InputView inputView;
    private final OutputView outputView;
    private final NoteConverter noteConverter;

    public CodeMusicGame(InputView inputView, OutputView outputView, NoteConverter noteConverter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.noteConverter = noteConverter;
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

            String rightNote = noteConverter.convertRightHand(rightChar);
            String leftNote = noteConverter.convertLeftHand(leftChar);
    
            rightNotes.add(rightNote);
            leftNotes.add(leftNote);

            /*
            outputView.printNoteList("오른손", rightNotes);
            outputView.printNoteList("왼손", leftNotes);
            */
        }
        System.out.println("--- 바구니 확인 ---");
        System.out.println("오른손 바구니: " + rightNotes);
        System.out.println("왼손 바구니: " + leftNotes);
        System.out.println("-----------------");
    }
}
