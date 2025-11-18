package codemusic;

import java.util.List;

public class OutputView {

    private static final int BEATS_PER_LINE = 6;
    private static final int MAX_NOTE_WIDTH = 10;

    /*public void printNoteList(String handName, List<String> notes) {
        String noteString = String.join("\t| ", notes);
        System.out.println(handName + ":\t" + noteString);
    }*/
    public void printScore(List<String> rightNotes, List<String> leftNotes, List<String> dynamics) {

        StringBuilder rightLine = new StringBuilder("오른손:\t");
        StringBuilder leftLine = new StringBuilder("왼손:\t");
        StringBuilder dynamicLine = new StringBuilder("셈여림:\t");
        for (int i = 0; i < rightNotes.size(); i++) {
            String rightNote = rightNotes.get(i);
            String leftNote = leftNotes.get(i);
            String dynamic = dynamics.get(i);

            String formattedRightNote = String.format("%-" + MAX_NOTE_WIDTH + "s", rightNote);
            String formattedLeftNote = String.format("%-" + MAX_NOTE_WIDTH + "s", leftNote);
            String formattedDynamic = String.format("%-" + MAX_NOTE_WIDTH + "s", dynamic);

            rightLine.append(formattedRightNote).append(" | ");
            leftLine.append(formattedLeftNote).append(" | ");
            dynamicLine.append(formattedDynamic).append(" | ");

            if ((i + 1) % BEATS_PER_LINE == 0 || (i + 1) == rightNotes.size()) {
                System.out.print(rightLine + "\n");
                System.out.print(leftLine + "\n");
                System.out.print(dynamicLine + "\n");
                System.out.println();

                rightLine = new StringBuilder("오른손:\t");
                leftLine = new StringBuilder("왼손:\t");
                dynamicLine = new StringBuilder("셈여림:\t");

            }
        }
    }

    public void printStartMessage() {
        System.out.println("--- 코드를 음악으로 변환합니다. ---");
    }
}
