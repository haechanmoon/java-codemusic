package codemusic;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getRightHandCode() {
        System.out.println("오른손 코드를 입력하세요: ");
        return scanner.nextLine();
    }
}
