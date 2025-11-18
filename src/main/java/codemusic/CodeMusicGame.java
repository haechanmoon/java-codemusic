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
        List<String> dynamics = new ArrayList<>();

        int leftHandIndex = 0;

        while (!rightCode.isEmpty()) {
            char leftOneLetter = leftCode.charAt(leftHandIndex % leftCode.length());
            Note leftNoteEnum = Note.findByChar(leftOneLetter);
            String leftNote = leftNoteEnum.getLeftHandNote();
            String dynamic = Character.isUpperCase(leftOneLetter) ? "f" : "p"; // (f: forte, p: piano)

            Chord matchedChord = Chord.findByKeyword(rightCode);
            String rightNote;

            if (matchedChord != null) {
                int[] midiMaterials = matchedChord.generateMidiNotes();
                rightNote = Chord.midiArrayToNoteString(midiMaterials);
                rightCode = rightCode.substring(matchedChord.getKeyword().length());
            } else {
                char rightOneLetter = rightCode.charAt(0);
                Note rightNoteEnum = Note.findByChar(rightOneLetter);
                rightNote = rightNoteEnum.getRightHandNote(); //
                rightCode = rightCode.substring(1);
            }
            boolean isRightRest = rightNote.equals("REST");
            boolean isLeftRest = leftNote.equals("REST");

            if (!(isRightRest && isLeftRest)) {
                rightNotes.add(rightNote);
                leftNotes.add(leftNote);
                dynamics.add(dynamic);
            }

            leftHandIndex++;
        }
        outputView.printScore(rightNotes, leftNotes, dynamics);
    }
}