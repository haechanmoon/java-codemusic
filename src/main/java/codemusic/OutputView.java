package codemusic;

import java.util.List;

public class OutputView {
    /* //처음에 이걸로 하려 했는데 악보상으로 가로로 쭉 나오는게 더 이쁠 거 바꿨습니다!
    public void printNotes(String rightNote, String leftNote) {

        System.out.printf(" 오른손 :[%s] | 왼손 : [%s]%n", rightNote, leftNote);
    }
    */

    public void printNoteList(String handName, List<String> notes) {
        String noteString = String.join("\t| ", notes);
        System.out.println(handName + ":\t" + noteString);
    }

    public void printStartMessage() {
        System.out.println("--- 코드를 음악으로 변환합니다. ---");
    }
}
