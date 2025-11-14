package codemusic;

public class Validator {
    public static void validateCodeNotEmpty(String code) {
        if (code.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 코드는 1글자 이상 입력해야 합니다.");
        }
    }
}
