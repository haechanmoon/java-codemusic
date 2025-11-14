package codemusic;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getRightHandCode() {
        while (true) {
            try {
                System.out.println("\n오른손 코드를 입력하세요: ");
                String rightcode = scanner.nextLine();
                Validator.validateCodeNotEmpty(rightcode);

                return rightcode;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getLeftHandCode() {
        while (true) {
            try {
                System.out.println("\n왼손 코드를 입력하세요: ");
                String leftcode = scanner.nextLine();
                Validator.validateCodeNotEmpty(leftcode);

                return leftcode;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
