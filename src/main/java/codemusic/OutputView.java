package codemusic;

public class OutputView {
    public void printNotes(String rightNote, String leftNote) {
        System.out.printf(" 오른손 :[%s] | 왼손 : [%s]%n", rightNote, leftNote);
    }

    public void printStartMessage() {
        System.out.println("--- 코드를 음악으로 변환합니다. ---");
    }
}
