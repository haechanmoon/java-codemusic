package codemusic;

import java.util.ArrayList;
import java.util.List;

public class CodeMusicGame {
    private final InputView inputView;
    private final OutputView outputView;
    private final MidiPlayer midiPlayer;

    public CodeMusicGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.midiPlayer = new MidiPlayer();
    }

    public void start() {
        String rightCode = inputView.getRightHandCode();
        String leftCode = inputView.getLeftHandCode();

        try {
            Validator.validateCodeNotEmpty(rightCode);
            Validator.validateCodeNotEmpty(leftCode);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
            return;
        }
        outputView.printStartMessage();

        List<String> rightNotes = new ArrayList<>();
        List<String> leftNotes = new ArrayList<>();
        List<String> dynamics = new ArrayList<>();

        List<List<Integer>> rightMidiScores = new ArrayList<>();
        List<Integer> leftMidiScores = new ArrayList<>();

        int leftHandIndex = 0;

        while (!rightCode.isEmpty()) {
            char leftOneLetter = leftCode.charAt(leftHandIndex % leftCode.length());
            Note leftNoteEnum = Note.findByChar(leftOneLetter);
            String leftNote = leftNoteEnum.getLeftHandNote();
            int leftMidiNum = leftNoteEnum.getLeftHandMidiNumber();
            String dynamic = Character.isUpperCase(leftOneLetter) ? "f" : "p"; // (f: forte, p: piano)

            Chord matchedChord = Chord.findByKeyword(rightCode);
            String rightNote;
            List<Integer> currentRightMidiNums = new ArrayList<>();

            if (matchedChord != null) {
                int[] midiMaterials = matchedChord.generateMidiNotes();
                rightNote = Chord.midiArrayToNoteString(midiMaterials);
                for (int note : midiMaterials) {
                    currentRightMidiNums.add(note);
                }
                rightCode = rightCode.substring(matchedChord.getKeyword().length());
            } else {
                char rightOneLetter = rightCode.charAt(0);
                Note rightNoteEnum = Note.findByChar(rightOneLetter);
                rightNote = rightNoteEnum.getRightHandNote();
                currentRightMidiNums.add(rightNoteEnum.getMidiNumber());
                rightCode = rightCode.substring(1);
            }
            boolean isRightRest = rightNote.equals("REST");
            boolean isLeftRest = leftNote.equals("REST");

            if (!(isRightRest && isLeftRest)) {
                rightNotes.add(rightNote);
                leftNotes.add(leftNote);
                dynamics.add(dynamic);

                rightMidiScores.add(currentRightMidiNums);
                leftMidiScores.add(leftMidiNum);
            }

            leftHandIndex++;
        }
        outputView.printScore(rightNotes, leftNotes, dynamics);
        midiPlayer.play(rightMidiScores, leftMidiScores, dynamics);
    }
}