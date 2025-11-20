package codemusic;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getRightHandCode() {
        System.out.println("\n오른손 코드를 입력하세요 (빈 줄에 'done'만 입력하면 종료):");
        StringBuilder rightCodeBoard = new StringBuilder();
        while (true) {
            String oneLine = scanner.nextLine();
            if (oneLine.equalsIgnoreCase("done")) {
                break;
            }
            rightCodeBoard.append(oneLine).append("\n");
        }
        return rightCodeBoard.toString();
    }

    public String getLeftHandCode() {
        System.out.println("\n왼손 코드를 입력하세요(빈 줄에 'done'만 입력하면 종료):");
        StringBuilder leftCodeBoard = new StringBuilder();
        while (true) {
            String oneLine = scanner.nextLine();
            if (oneLine.equalsIgnoreCase("done")) {
                break;
            }
            leftCodeBoard.append(oneLine).append("\n");
        }
        return leftCodeBoard.toString();
    }
}
