package codemusic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, String> chordMap = new HashMap<>();
        chordMap.put("public", "C4-E4-G4");
        chordMap.put("static", "G4-B4-D5");
        chordMap.put("void", "F4-A4-C5");

        List<String> rightNotes = new ArrayList<>();
        List<String> leftNotes = new ArrayList<>();

        int leftHandIndex = 0;

        while (!rightCode.isEmpty()) {
            char leftOneLetter = leftCode.charAt(leftHandIndex % leftCode.length());
            Note leftNoteEnum = Note.findByChar(leftOneLetter);
            leftNotes.add(leftNoteEnum.getLeftHandNote());

            String matchedKeyword = null;
            for (String keyword : chordMap.keySet()) {
                if (rightCode.startsWith(keyword)) {
                    matchedKeyword = keyword;
                    break;
                }
            }

            if (matchedKeyword != null) {
                rightNotes.add(chordMap.get(matchedKeyword));
                rightCode = rightCode.substring(matchedKeyword.length());
            } else {
                char rightOneLetter = rightCode.charAt(0);
                Note rightNoteEnum = Note.findByChar(rightOneLetter);
                rightNotes.add(rightNoteEnum.getRightHandNote());
                rightCode = rightCode.substring(1);
            }
            leftHandIndex++;
        }
        outputView.printNoteList("오른손", rightNotes);
        outputView.printNoteList("왼손", leftNotes);
    }
}