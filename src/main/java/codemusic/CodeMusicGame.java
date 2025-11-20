package codemusic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CodeMusicGame {
    private final InputView inputView;
    private final OutputView outputView;
    private final MidiPlayer midiPlayer;
    private final Random random;

    public CodeMusicGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.midiPlayer = new MidiPlayer();
        this.random = new Random();
    }

    public void start() {
        String rightCode = inputView.getRightHandCode();
        String leftCode = inputView.getLeftHandCode();

        if (!validateInputs(rightCode, leftCode)) {
            start();
            return;
        }
        outputView.printStartMessage();

        MusicScore score = createMusicScore(rightCode, leftCode);
        outputView.printScore(score.rightNotes, score.leftNotes, score.dynamics);
        midiPlayer.play(score.rightMidis, score.leftMidis, score.dynamics);
    }

    private boolean validateInputs(String right, String left) {
        try {
            Validator.validateCodeNotEmpty(right);
            Validator.validateCodeNotEmpty(left);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private MusicScore createMusicScore(String rightCode, String leftCode) {
        MusicScore score = new MusicScore();
        int leftIndex = 0;

        while (!rightCode.isEmpty()) {
            int skipLength = processNextSegment(score, rightCode, leftCode, leftIndex);
            rightCode = rightCode.substring(skipLength);
            leftIndex++;
        }
        return score;
    }

    private int processNextSegment(MusicScore score, String rightCode, String leftCode, int leftIndex) {
        char leftChar = leftCode.charAt(leftIndex % leftCode.length());
        Chord chord = Chord.findByKeyword(rightCode);

        if (chord != null) {
            return processChord(score, chord, leftChar);
        }
        return processSingleNote(score, rightCode.charAt(0), leftChar);
    }

    private int processChord(MusicScore score, Chord chord, char leftChar) {
        boolean isMajor = random.nextBoolean();
        int range = Chord.MAX_ROOT_NOTE - Chord.MIN_ROOT_NOTE + 1;
        int root = Chord.MIN_ROOT_NOTE + random.nextInt(range);

        int[] midis = chord.generateMidiNotes(root, isMajor);
        String noteName = Chord.midiArrayToNoteString(midis);

        addScoreIfValid(score, noteName, midis, leftChar);
        return chord.getKeyword().length();
    }

    private int processSingleNote(MusicScore score, char rightChar, char leftChar) {
        Note note = Note.findByChar(rightChar);
        int[] midis = {note.getMidiNumber()};

        addScoreIfValid(score, note.getRightHandNote(), midis, leftChar);
        return 1;
    }

    private void addScoreIfValid(MusicScore score, String rightName, int[] rightMidis, char leftChar) {
        Note leftNote = Note.findByChar(leftChar);

        if (rightName.equals("REST")) {
            if (leftNote.getLeftHandNote().equals("REST")) {
                return;
            }
        }
        addNoteToScore(score, rightName, rightMidis, leftNote, leftChar);
    }

    private void addNoteToScore(MusicScore score, String rightName, int[] midis, Note leftNote, char leftChar) {
        score.rightNotes.add(rightName);
        score.leftNotes.add(leftNote.getLeftHandNote());
        score.rightMidis.add(toList(midis));
        score.leftMidis.add(leftNote.getLeftHandMidiNumber());
        score.dynamics.add(calculateDynamic(leftChar));
    }

    private String calculateDynamic(char leftChar) {
        if (Character.isUpperCase(leftChar)) {
            return "f";
        }
        return "p";
    }

    private List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

    private static class MusicScore {
        List<String> rightNotes = new ArrayList<>();
        List<String> leftNotes = new ArrayList<>();
        List<String> dynamics = new ArrayList<>();
        List<List<Integer>> rightMidis = new ArrayList<>();
        List<Integer> leftMidis = new ArrayList<>();
    }
}