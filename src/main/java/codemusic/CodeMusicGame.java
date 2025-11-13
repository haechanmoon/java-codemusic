package codemusic;

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
        for (int i = 0; i < rightCode.length(); i++) {
            char rightChar = rightCode.charAt(i);
            char leftChar = leftCode.charAt(i);
            String rightNote = noteConverter.convertRightHand(rightChar);
            String leftNote = noteConverter.convertLeftHand(leftChar);

            outputView.printNotes(rightNote, leftNote);
        }
    }
}
