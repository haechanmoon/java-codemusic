package codemusic;

import java.util.List;

public class OutputView {
    private static final int BEATS_PER_LINE = 6;
    private static final int MAX_NOTE_WIDTH = 10;
    private static final String MEASURE = " | ";

    public void printStartMessage() {
        System.out.println(Messages.START_MSG);
    }

    public void printScore(List<String> rightNotes, List<String> leftNotes, List<String> dynamics) {
        StringBuilder rightLine = new StringBuilder(Messages.OUTPUT_RIGHT);
        StringBuilder leftLine = new StringBuilder(Messages.OUTPUT_LEFT);
        StringBuilder dynamicLine = new StringBuilder(Messages.OUTPUT_DYNAMIC);

        for (int i = 0; i < rightNotes.size(); i++) {
            appendMeasure(i, rightNotes, leftNotes, dynamics, rightLine, leftLine, dynamicLine);
            printIfLineEnd(i, rightNotes.size(), rightLine, leftLine, dynamicLine);
        }
    }

    private void appendMeasure(int i, List<String> rNotes, List<String> lNotes, List<String> dyns,
                               StringBuilder rLine, StringBuilder lLine, StringBuilder dLine) {
        rLine.append(format(rNotes.get(i))).append(MEASURE);
        lLine.append(format(lNotes.get(i))).append(MEASURE);
        dLine.append(format(dyns.get(i))).append(MEASURE);
    }

    private void printIfLineEnd(int i, int size, StringBuilder rLine, StringBuilder lLine, StringBuilder dLine) {
        if ((i + 1) % BEATS_PER_LINE == 0 || (i + 1) == size) {
            System.out.println(rLine);
            System.out.println(lLine);
            System.out.println(dLine);
            System.out.println();

            resetBuffer(rLine, Messages.OUTPUT_RIGHT);
            resetBuffer(lLine, Messages.OUTPUT_LEFT);
            resetBuffer(dLine, Messages.OUTPUT_DYNAMIC);
        }
    }

    private String format(String text) {
        return String.format("%-" + MAX_NOTE_WIDTH + "s", text);
    }

    private void resetBuffer(StringBuilder sb, String prefix) {
        sb.setLength(0);
        sb.append(prefix);
    }
}