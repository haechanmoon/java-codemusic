package codemusic;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getRightHandCode() {
        System.out.println(Messages.ASK_RIGHT_HAND);
        StringBuilder rightCodeBoard = new StringBuilder();
        while (true) {
            String oneLine = scanner.nextLine();
            if (oneLine.equalsIgnoreCase(Messages.END_WORD)) {
                break;
            }
            rightCodeBoard.append(oneLine).append("\n");
        }
        return rightCodeBoard.toString();
    }

    public String getLeftHandCode() {
        System.out.println(Messages.ASK_LEFT_HAND);
        StringBuilder leftCodeBoard = new StringBuilder();
        while (true) {
            String oneLine = scanner.nextLine();
            if (oneLine.equalsIgnoreCase(Messages.END_WORD)) {
                break;
            }
            leftCodeBoard.append(oneLine).append("\n");
        }
        return leftCodeBoard.toString();
    }
}
